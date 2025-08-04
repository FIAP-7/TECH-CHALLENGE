package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class ItemCardapioNaoEncontradoException extends SystemBaseException {

    private final String code = "itemCardapio.naoEncontrado";
    private final String message = "Item de cardápio não encontrado!";
    private final Integer httpStatus = 404;
}
