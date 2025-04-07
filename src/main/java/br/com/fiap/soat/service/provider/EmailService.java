package br.com.fiap.soat.service.provider;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import br.com.fiap.soat.dto.EmailDto;
import br.com.fiap.soat.util.LoggerAplicacao;

@Service
public class EmailService {

  @Value("${sendgrid.from-email}")
  private String fromEmail;

  @Value("${sendgrid.from-name}")
  private String fromName;

  private final SendGrid sendGrid;

  @Autowired
  public EmailService(SendGrid sendGrid) {
    this.sendGrid = sendGrid;
  }

  @Async
  public CompletableFuture<Void> enviarEmail(EmailDto dadosEmail) {

    Email remetente = new Email(fromEmail, fromName);
    Email destinatario = new Email(dadosEmail.getEmailDestino());
    Content conteudo = new Content("text/html", dadosEmail.getTexto());
    Mail email = new Mail(remetente, dadosEmail.getAssunto(), destinatario, conteudo);

    Request request = new Request();
    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");

    try {
      request.setBody(email.build());
    } catch (IOException ex) {
      LoggerAplicacao.error("Erro ao construir a requisição do SendGrid.");
      return CompletableFuture.completedFuture(null);
    }

    Response response;
    try {
      response = sendGrid.api(request);
    } catch (Exception e) {
      LoggerAplicacao.error("Erro na comunicação com o SendGrid.");
      return CompletableFuture.completedFuture(null);
    }

    if (!responseOk(response)) {
      LoggerAplicacao.error("Erro ao enviar o e-mail (SendGrid falhou).");
      LoggerAplicacao.error("StatusCode: " + response.getStatusCode());
      LoggerAplicacao.error(response.getBody());
    }

    return CompletableFuture.completedFuture(null);
  }

  private boolean responseOk(Response response) {
    return response.getStatusCode() >= 200 && response.getStatusCode() <= 299;
  }
}
