package br.com.fiap.soat.controller;

import br.com.fiap.soat.dto.EmailDto;
import br.com.fiap.soat.exception.ApplicationException;
import br.com.fiap.soat.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "E-mail")
public interface EmailContract {

  @Operation(
      summary = "Enviar e-mail",
      description = """
          Envia um e-mail informando que o sistema terminou de processar o vídeo.
          <br>Se houve sucesso, informa o link para baixar o arquivo.
          <br>Se houve falha, informa o motivo da falha.
          """)

  @ApiResponses(value = {
    @ApiResponse(
      responseCode = "204",
      description = "No Content")
  })
  
  ResponseEntity<Object> enviarEmail(@RequestBody EmailDto dadosEmail)
      throws ApplicationException, BadRequestException;

}
