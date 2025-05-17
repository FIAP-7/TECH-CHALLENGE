package br.com.fiap.postech.gestao_restaurantes.exception;

import lombok.Getter;

@Getter
public class SenhaInvalidaException extends SystemBaseException {

    private final String code = "usuario.senhaIncorreta";
    private final String message = "Usuário informada está incorreta;";
    private final Integer httpStatus = 401;
}
