package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class FotoInvalidaException extends SystemBaseException {

    private final String code = "CardapioItem.fotoInvalida";
    private final String message = "URL da foto inválida.";
    private final Integer httpStatus = 401;
}
