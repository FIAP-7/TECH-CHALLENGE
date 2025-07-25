package br.com.fiap.postech.gestao_restaurantes.exception;

import lombok.Getter;

@Getter
public class TipoUsuarioNaoEncontradoException extends SystemBaseException {

    private final String code = "tipoUsuario.tipoUsuarioNaoEncontrado";
    private final String message = "Tipo de Usuário não encontrado!";
    private final Integer httpStatus = 404;
}
