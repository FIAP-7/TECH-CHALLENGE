package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class EmailUsuarioInvalidoException extends SystemBaseException {

    private final String code = "usuario.emailInvalido";
    private final String message = "Endereco de email invalido";
    private final Integer httpStatus = 401;
}
