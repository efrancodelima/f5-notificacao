package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.dto.EmailDto;
import br.com.fiap.soat.exception.ApplicationException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Value("${sendgrid.api-key}")
  private String apiKey;

  @Value("${sendgrid.from-email}")
  private String fromEmail;

  @Value("${sendgrid.from-name}")
  private String fromName;

  public void enviarEmail(EmailDto dadosEmail) throws ApplicationException {

    Email remetente = new Email(fromEmail, fromName);
    Email destinatario = new Email(dadosEmail.getEmailDestino());
    Content conteudo = new Content("text/html", dadosEmail.getTexto());
    Mail email = new Mail(remetente, dadosEmail.getAssunto(), destinatario, conteudo);

    Request request = new Request();
    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");

    SendGrid sg = new SendGrid(apiKey);
    Response response;

    try {
      request.setBody(email.build());
    } catch (IOException ex) {
      throw new ApplicationException();
    }

    try {
      response = sg.api(request);
    } catch (Exception e) {
      throw new ApplicationException();
    }

    if (!responseOk(response)) {
      throw new ApplicationException();
    }
  }

  private boolean responseOk(Response response) {
    if (response.getStatusCode() >= 200 && response.getStatusCode() <= 299) {
      return true;
    } else {
      return false;
    }
  }
}
