package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class SenhaFormatoInvalidoException extends SystemBaseException {

    private final String code = "usuario.senhaFormatoInvalido";
    private final String message = "Senha precisa ter mais de 8 caracteres e possuir pelo menos uma letra maiúscula, uma letra minúscula, um número e um caracter especial.";
    private final Integer httpStatus = 401;
}
