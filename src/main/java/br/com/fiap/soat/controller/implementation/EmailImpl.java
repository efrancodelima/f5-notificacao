package br.com.fiap.soat.controller.implementation;

import br.com.fiap.soat.controller.contract.Email;
import br.com.fiap.soat.dto.EmailDto;
import br.com.fiap.soat.exception.ApplicationException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.EmailService;
import br.com.fiap.soat.validator.EmailDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailImpl implements Email {

  private final EmailService service;

  @Autowired
  public EmailImpl(EmailService service) {
    this.service = service;
  }

  @Override
  public ResponseEntity<Object> enviarEmail(EmailDto dadosEmail)
      throws ApplicationException, BadRequestException {
    
    EmailDtoValidator.validar(dadosEmail);
    service.enviarEmail(dadosEmail);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}