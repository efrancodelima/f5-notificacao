package br.com.fiap.soat.exception;

public class ApplicationException extends Exception {

  // Só tem uma mensagem para esse tipo de exceção
  public ApplicationException() {
    super("Ocorreu um erro ao enviar o e-mail.");
  }

  public ApplicationException(String message) {
    super(message);
  }
}
