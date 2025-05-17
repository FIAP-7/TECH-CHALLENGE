package br.com.fiap.postech.gestao_restaurantes.exception;

import lombok.Getter;

@Getter
public class CampoVazioException extends SystemBaseException {

    private final String code = "";
    private final String message = "Request body inválido! Verifique os campos e tente novamente.";
    private final Integer httpStatus = 400;
}
