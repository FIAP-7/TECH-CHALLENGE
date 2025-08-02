package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class UsuarioUtilizadoException extends SystemBaseException {

    private final String code = "usuario.usuarioUtilizado";
    private final String message = "Este usuário está vinculado a um ou mais restaurantes!";
    private final Integer httpStatus = 400;
}
