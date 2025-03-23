package br.com.fiap.soat.controller;

import br.com.fiap.soat.dto.EmailDto;
import br.com.fiap.soat.exception.ApplicationException;
import br.com.fiap.soat.exception.BadRequestException;
import br.com.fiap.soat.service.provider.EmailService;
import br.com.fiap.soat.validator.EmailDtoValidator;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController implements EmailContract {

  private final EmailService service;

  @Autowired
  public EmailController(EmailService service) {
    this.service = service;
  }

  @Override
  @PostMapping(value = "/enviar")
  public ResponseEntity<Object> enviarEmail(EmailDto dadosEmail)
      throws ApplicationException, BadRequestException {

    System.out.println(
        LocalDateTime.now().toString() 
        + " - Requisição recebida: " 
        + dadosEmail.toString());
    
    EmailDtoValidator.validar(dadosEmail);
    service.enviarEmail(dadosEmail);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}