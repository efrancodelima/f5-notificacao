package br.com.fiap.soat.controller.contract;

import br.com.fiap.soat.dto.EmailDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "E-mail")
public interface Email {

  @Operation(
      summary = "Enviar e-mail",
      description = """
          Envia um e-mail informando que o sistema terminou de processar o vídeo.
          <br>Se houve sucesso, informa o link para baixar o arquivo.
          <br>Se houve falha, informa o motivo da falha.
          """)
  
  @PostMapping(value = "/enviar")
  
  ResponseEntity<Object> enviarEmail(@RequestBody EmailDto dadosEmail);

}
