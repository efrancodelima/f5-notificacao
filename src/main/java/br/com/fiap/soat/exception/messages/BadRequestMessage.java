package br.com.fiap.soat.exception.messages;

public enum BadRequestMessage {
    
  EMAIL_NULO("Informe o e-mail do destinatário."),
  EMAIL_TAMANHO("O tamanho máximo permitido para o e-mail é de 100 caracteres."),
  EMAIL_INVALIDO("O e-mail informado é inválido."),
  ASSUNTO_NULO("Informe o assunto do e-mail."),
  TEXTO_NULO("Informe o texto do e-mail.");

  private String mensagem;

  BadRequestMessage(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getMessage() {
    return mensagem;
  }
}
