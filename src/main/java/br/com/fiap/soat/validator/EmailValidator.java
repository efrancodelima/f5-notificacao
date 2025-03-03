package br.com.fiap.soat.validator;

import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.exception.messages.BadRequestMessage;
import java.util.regex.Pattern;

public class EmailValidator {

  private EmailValidator() {}

  public static void validar(String email) throws BadRequestException {

    if (email == null || email.isBlank()) {
      throw new BadRequestException(BadRequestMessage.EMAIL_NULO);

    } else if (email.length() > 100) {
      throw new BadRequestException(BadRequestMessage.EMAIL_TAMANHO);
    
    }

    String emailRegexRfc5322 = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    Pattern pattern = Pattern.compile(emailRegexRfc5322);
    
    if (!pattern.matcher(email).matches()) {
      throw new BadRequestException(BadRequestMessage.EMAIL_INVALIDO);
    }
  } 
}
