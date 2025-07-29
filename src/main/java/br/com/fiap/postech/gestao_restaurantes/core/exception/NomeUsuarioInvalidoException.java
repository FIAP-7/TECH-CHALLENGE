package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class NomeUsuarioInvalidoException extends SystemBaseException {

    private final String code = "usuario.nomeInvalido";
    private final String message = "Nome não permite números e caracteres especiais!";
    private final Integer httpStatus = 401;
}
