package br.com.fiap.soat.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

class EmailValidatorTest {

  @Test
  void deveLancarExcecaoParaEmailNulo() {
    String email = null;

    var excecao = assertThrows(BadRequestException.class, () -> {
      EmailValidator.validar(email);
    });

    assertEquals(BadRequestMessage.EMAIL_NULO.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaEmailBlank() {
    String email = "  ";

    var excecao = assertThrows(BadRequestException.class, () -> {
      EmailValidator.validar(email);
    });

    assertEquals(BadRequestMessage.EMAIL_NULO.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaEmailAcima100Chars() {
    String email = "abc".repeat(100);

    var excecao = assertThrows(BadRequestException.class, () -> {
      EmailValidator.validar(email);
    });

    assertEquals(BadRequestMessage.EMAIL_TAMANHO.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaEmailInvalido() {
    String email = "umEmailEnvalido.com";

    var excecao = assertThrows(BadRequestException.class, () -> {
      EmailValidator.validar(email);
    });

    assertEquals(BadRequestMessage.EMAIL_INVALIDO.getMessage(), excecao.getMessage());
  }
}
