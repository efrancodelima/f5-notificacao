package br.com.fiap.soat.service.provider;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

import br.com.fiap.soat.dto.EmailDto;

class EmailServiceTest {

  AutoCloseable closeable;
  
  @Mock
  private SendGrid sendGrid;

  @InjectMocks
  private EmailService emailService;

  @BeforeEach
  void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  void deveEnviarEmailComSucesso() throws Exception {
    // Arrange
    EmailDto dadosEmail = new EmailDto("email@example.com", "Assunto", "Texto");

    Response response = Mockito.mock(Response.class);
    doReturn(HttpStatus.OK.value()).when(response).getStatusCode();
    doReturn(response).when(sendGrid).api(Mockito.any(Request.class));

    // Act
    CompletableFuture<Void> future = emailService.enviarEmail(dadosEmail);
    future.join();

    // Assert
    verify(sendGrid, times(1)).api(Mockito.any());
    verify(response, times(2)).getStatusCode();
  }
}
