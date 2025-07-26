package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class TipoUsuarioMesmoNomeExistenteException extends SystemBaseException {

    private final String code = "tipoUsuario.tipoUsuarioMesmoNomeExistente";
    private final String message = "Já existe um tipo de usuário com este nome!";
    private final Integer httpStatus = 400;
}
