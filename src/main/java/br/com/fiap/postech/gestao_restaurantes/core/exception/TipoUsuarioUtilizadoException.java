package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class TipoUsuarioUtilizadoException extends SystemBaseException {

    private final String code = "tipoUsuario.tipoUsuarioUtilizado";
    private final String message = "Este tipo de usuário está vinculado a um ou mais usuários!";
    private final Integer httpStatus = 400;
}
