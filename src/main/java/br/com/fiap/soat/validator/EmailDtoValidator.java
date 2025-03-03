package br.com.fiap.soat.validator;

import br.com.fiap.soat.dto.EmailDto;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;

public class EmailDtoValidator {

  public EmailDtoValidator() {}

  public static void validar(EmailDto dadosEmail) throws BadRequestException {

    EmailValidator.validar(dadosEmail.getEmailDestino());

    if (isNullOrBlank(dadosEmail.getAssunto())) {
      throw new BadRequestException(BadRequestMessage.ASSUNTO_NULO);
    }

    if (isNullOrBlank(dadosEmail.getTexto())) {
      throw new BadRequestException(BadRequestMessage.TEXTO_NULO);
    }
  }

  private static boolean isNullOrBlank(String text) {
    if (text == null || text.isBlank()) {
      return true;
    } else {
      return false;
    }
  }
}
