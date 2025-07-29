package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class CepInvalidoException extends SystemBaseException {

    private final String code = "usuario.cepInvalido";
    private final String message = "Cep com o formato incorreto";
    private final Integer httpStatus = 401;
}
