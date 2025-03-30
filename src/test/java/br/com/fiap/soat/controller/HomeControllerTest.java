package br.com.fiap.soat.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

  String endpoint = "/";

  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssured.port = port;
  }

  @Test
  void testEndpoint() {
    given()
      .when()
        .get(endpoint)
      .then()
        .statusCode(200)
        .body(containsString("/swagger-ui/index.html"));
  }
}
