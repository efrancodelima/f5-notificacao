package br.com.fiap.soat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

  @Schema(description = "E-mail do destinatário.", example = "email@email.com.br")
  private String emailDestino;

  @Schema(description = "O assunto do e-mail.", example = "Reunião na sexta-feira às 17h30")
  private String assunto;

  @Schema(description = "O texto do email.", example = "<!DOCTYPE html><html><body><p>"
          + "Conforme conversamos, ficou agendada a reunião com a equipe para esta sexta-feira"
          + "pré-carnaval às 17h30 para tratar sobre aquele assunto que podia ser um e-mail."
          + "</p></body></html>")
  private String texto;

  
}
