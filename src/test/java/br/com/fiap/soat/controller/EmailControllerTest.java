package br.com.fiap.soat.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

import br.com.fiap.soat.dto.EmailDto;
import br.com.fiap.soat.exception.handler.CustomErrorResponse;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class EmailControllerTest {

  AutoCloseable closeable;
  String endpoint = "/email/enviar";

  @LocalServerPort
  private int port;

  @MockitoBean
  private SendGrid sendGrid;

  @BeforeEach
  void setup() throws IOException {
    RestAssured.port = port;

    var response = new com.sendgrid.Response(200, "", null);
    doReturn(response).when(sendGrid).api(Mockito.any(Request.class));
  }

  @Test
  void deveRetornarNoContent() throws Exception {
    String requisicao = toJson(getRequisicao());

    given()
        .contentType(ContentType.JSON)
        .body(requisicao)
        .when()
          .post(endpoint)
        .then()
          .statusCode(HttpStatus.NO_CONTENT.value());
  }

  @Test
  void deveRetornarErroDeBadRequest() throws Exception {
    // Arrange
    EmailDto emailDto = getRequisicao();
    emailDto.setEmailDestino(null);
    String requisicao = toJson(emailDto);

    // Act
    Response response = given()
        .contentType(ContentType.JSON)
        .body(requisicao)
        .when()
          .post(endpoint)
        .then()
          .extract().response();

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());

    CustomErrorResponse responseBody = 
        response.getBody().as(CustomErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST.value(), responseBody.getStatusCode());
    assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), responseBody.getError());
    assertEquals(BadRequestMessage.EMAIL_NULO.getMessage(), responseBody.getMessage());
    assertEquals(responseBody.getTimestamp().isEmpty(), false);
    assertEquals(endpoint, responseBody.getPath());
  }

  // Método auxiliar
  private EmailDto getRequisicao() {
    return new EmailDto(
        "email@email.com.br",
        "Um assunto qualquer",
        "Um texto qualquer"
    );
  }

  private String toJson(EmailDto emailDto) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(emailDto);
  }
}