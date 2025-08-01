package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardaptio;

import lombok.Getter;

@Getter
public class DescricaoItemCardapioInvalidoException {

    private final String code = "ItemCardapio.descricaoInvalida";
    private final String message = "Descrição do item não pode ser vazia.";
    private final Integer httpStatus = 400;
}
