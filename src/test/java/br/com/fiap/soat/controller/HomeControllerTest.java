package br.com.fiap.soat.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

  AutoCloseable closeable;
  String endpoint = "/";

  @LocalServerPort
  private int port;

  @InjectMocks
  private HomeController homeController;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    RestAssured.port = port;
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
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
