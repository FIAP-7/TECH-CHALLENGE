package br.com.fiap.postech.gestao_restaurantes.exception;

import lombok.Getter;

@Getter
public class DadoInvalidoUsuarioException extends SystemBaseException {

    private final String code = "usuario.dadoInvalido";
    private final String message;
    private final Integer httpStatus = 400;
    
    public DadoInvalidoUsuarioException(String message) {
        this.message = message;
    }

    public DadoInvalidoUsuarioException() {
        this.message = "Dados inválidos para o usuário!";
    }
}
