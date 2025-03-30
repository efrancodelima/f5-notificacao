package br.com.fiap.soat.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.fiap.soat.dto.EmailDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

class EmailDtoValidatorTest {

  @Test
  void deveValidarEmailComSucesso() {
    assertDoesNotThrow(() -> {
      EmailDtoValidator.validar(getEmailDto());
    });
  }

  @Test
  void deveLancarExcecaoParaAssuntoNulo() {
    var emailDto = getEmailDto();
    emailDto.setAssunto(null);

    var excecao = assertThrows(BadRequestException.class, () -> {
      EmailDtoValidator.validar(emailDto);
    });

    assertEquals(BadRequestMessage.ASSUNTO_NULO.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaAssuntoBlank() {
    var emailDto = getEmailDto();
    emailDto.setAssunto("   ");

    var excecao = assertThrows(BadRequestException.class, () -> {
      EmailDtoValidator.validar(emailDto);
    });

    assertEquals(BadRequestMessage.ASSUNTO_NULO.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaTextoNulo() {
    var emailDto = getEmailDto();
    emailDto.setTexto(null);

    var excecao = assertThrows(BadRequestException.class, () -> {
      EmailDtoValidator.validar(emailDto);
    });

    assertEquals(BadRequestMessage.TEXTO_NULO.getMessage(), excecao.getMessage());
  }

  @Test
  void deveLancarExcecaoParaTextoBlank() {
    var emailDto = getEmailDto();
    emailDto.setTexto("   ");

    var excecao = assertThrows(BadRequestException.class, () -> {
      EmailDtoValidator.validar(emailDto);
    });

    assertEquals(BadRequestMessage.TEXTO_NULO.getMessage(), excecao.getMessage());
  }

  // Método auxiliar
  EmailDto getEmailDto() {
    return new EmailDto(
      "email@email.com.br",
      "Um assunto qualquer",
      "Um texto qualquer"
    );
  }
}
