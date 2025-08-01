package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardaptio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class DescricaoItemCardapioInvalidoException extends SystemBaseException {
    private final String code = "ItemCardapio.descricaoInvalida";
    private final String message = "Descrição do item não pode ser vazia.";
    private final Integer httpStatus = 400;
}
