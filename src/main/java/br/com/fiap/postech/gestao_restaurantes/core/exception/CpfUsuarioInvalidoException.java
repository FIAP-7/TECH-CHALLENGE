package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class CpfUsuarioInvalidoException extends SystemBaseException {

    private final String code = "usuario.cpfInvalido";
    private final String message = "CPF necessita de 14 caracteres no formato (123.456.789-00)";
    private final Integer httpStatus = 401;
}
