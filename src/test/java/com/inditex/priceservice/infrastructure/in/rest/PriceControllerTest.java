package com.inditex.priceservice.infrastructure.in.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerE2ETest {

    private static final long PRODUCT_ID = 35455L;
    private static final int BRAND_ID = 1;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Test 1")
    void test1_june14At10h_shouldReturnPriceList1() {
        given()
            .param("applicationDate", "2020-06-14T10:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
        .when()
            .get("/prices")
        .then()
            .statusCode(200)
            .body("priceList", equalTo(1))
            .body("price", equalTo(35.50f))
            .body("productId", equalTo(35455))
            .body("brandId", equalTo(1));
    }

    @Test
    @DisplayName("Test 2")
    void test2_june14At16h_shouldReturnPriceList2() {
        given()
            .param("applicationDate", "2020-06-14T16:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
        .when()
            .get("/prices")
        .then()
            .statusCode(200)
            .body("priceList", equalTo(2))
            .body("price", equalTo(25.45f));
    }

    @Test
    @DisplayName("Test 3")
    void test3_june14At21h_shouldReturnPriceList1() {
        given()
            .param("applicationDate", "2020-06-14T21:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
        .when()
            .get("/prices")
        .then()
            .statusCode(200)
            .body("priceList", equalTo(1))
            .body("price", equalTo(35.50f));
    }

    @Test
    @DisplayName("Test 4")
    void test4_june15At10h_shouldReturnPriceList3() {
        given()
            .param("applicationDate", "2020-06-15T10:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
        .when()
            .get("/prices")
        .then()
            .statusCode(200)
            .body("priceList", equalTo(3))
            .body("price", equalTo(30.50f));
    }

    @Test
    @DisplayName("Test 5")
    void test5_june16At21h_shouldReturnPriceList4() {
        given()
            .param("applicationDate", "2020-06-16T21:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
        .when()
            .get("/prices")
        .then()
            .statusCode(200)
            .body("priceList", equalTo(4))
            .body("price", equalTo(38.95f));
    }

    @Test
    @DisplayName("Should return 404 when no price found")
    void shouldReturn404WhenNoPriceFound() {
        given()
            .param("applicationDate", "2019-01-01T10:00:00")
            .param("productId", 99999L)
            .param("brandId", BRAND_ID)
        .when()
            .get("/prices")
        .then()
            .statusCode(404)
            .body("status", equalTo(404));
    }

    @Test
    @DisplayName("Should return 400 when missing required parameter")
    void shouldReturn400WhenMissingParameter() {
        given()
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
        .when()
            .get("/prices")
        .then()
            .statusCode(400);
    }

    @Test
    @DisplayName("Should return 400 when applicationDate has invalid format")
    void shouldReturn400WhenDateFormatIsInvalid() {
        given()
            .param("applicationDate", "not-a-date")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
        .when()
            .get("/prices")
        .then()
            .statusCode(400)
            .body("status", equalTo(400));
    }
}