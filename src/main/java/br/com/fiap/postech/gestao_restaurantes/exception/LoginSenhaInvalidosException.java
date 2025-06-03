package br.com.fiap.postech.gestao_restaurantes.exception;

import lombok.Getter;

@Getter
public class LoginSenhaInvalidosException extends SystemBaseException {

    private final String code = "usuario.loginSenhaInvalidos";
    private final String message = "Login ou senha invalidos";
    private final Integer httpStatus = 401;
}
