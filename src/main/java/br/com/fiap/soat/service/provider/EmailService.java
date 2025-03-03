package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.dto.EmailDto;
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

  public void enviarEmail(EmailDto dadosEmail) throws Exception {

    Email remetente = new Email(fromEmail, fromName);
    Email destinatario = new Email(dadosEmail.getDestinatario());
    Content conteudo = new Content("text/html", dadosEmail.getMensagem());
    Mail email = new Mail(remetente, dadosEmail.getAssunto(), destinatario, conteudo);

    SendGrid sg = new SendGrid(apiKey);
    Request request = new Request();

    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(email.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
      throw new Exception("Erro ao enviar o e-mail.", ex);
    }
  }
}
