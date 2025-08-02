package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardaptio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class PrecoItemCardapioInvalidoException extends SystemBaseException {
    private final String code = "itemCardapio.precoInvalido";
    private final String message = "Preço deve ser maior que zero.";
    private final Integer httpStatus = 400;
}
