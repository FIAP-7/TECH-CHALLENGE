package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class RestauranteExistenteException extends SystemBaseException {

    private final String code = "restaurante.restauranteJaExiste";
    private final String message = "Restaurante já existe";
    private final Integer httpStatus = 422;
}
