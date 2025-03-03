package br.com.fiap.soat.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

  @Schema(description = "E-mail do destinatário.", example = "efrancodelima@gmail.com.br")
  private String destinatario;

  @Schema(description = "Assunto do e-mail.", example = "Seu vídeo está pronto!")
  private String assunto;

  @Schema(description = "Texto do email em html.", example = """
      <p>Olá!</p>
      <p>Seu vídeo terminou de processar e você já pode fazer o download das 
      imagens capturadas em: htttp://um-link-qualquer</p>
      """)
  private String mensagem;

}
