package br.com.fiap.postech.gestao_restaurantes.core.exception;

public abstract class SystemBaseException extends RuntimeException {
    private static final long serialVersionUID = -4080258494923877140L;


    public abstract String getCode();
    public abstract Integer getHttpStatus();
    @Override
    public abstract String getMessage();
}
