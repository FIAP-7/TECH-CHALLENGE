package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class ItemCardapioPorRestauranteNaoEncontradoException extends SystemBaseException {

    private final String code = "itemCardapio.naoEncontrado";
    private final String message = "Nenhum item de cardápio encontrado para o restaurante informado!";
    private final Integer httpStatus = 404;
}
