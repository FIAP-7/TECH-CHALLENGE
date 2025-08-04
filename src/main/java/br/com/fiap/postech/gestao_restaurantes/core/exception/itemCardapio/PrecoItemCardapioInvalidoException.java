package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

import static br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio.PRECO_MINIMO;

@Getter
public class PrecoItemCardapioInvalidoException extends SystemBaseException {
    private final String code = "itemCardapio.precoInvalido";
    private final String message = "Preço deve ser igual ou maior que " + PRECO_MINIMO + ".";
    private final Integer httpStatus = 400;
}
