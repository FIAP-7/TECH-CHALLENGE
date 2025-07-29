package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class EnderecoNaoEncontradoException extends SystemBaseException {

    private final String code = "endereco.naoEncontrado";
    private final String message = "Endereço não encontrado!";
    private final Integer httpStatus = 404;
}
