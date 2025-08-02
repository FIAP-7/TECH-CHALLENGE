package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class RestauranteNaoEncontradoException extends SystemBaseException {

    private final String code = "restaurante.restauranteNaoEncontrado";
    private final String message = "Restaurante não encontrado!";
    private final Integer httpStatus = 404;
}
