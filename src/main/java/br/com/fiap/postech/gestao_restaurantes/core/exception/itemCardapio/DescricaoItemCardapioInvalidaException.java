package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class DescricaoItemCardapioInvalidaException extends SystemBaseException {
    private final String code = "itemCardapio.descricaoInvalida";
    private final String message = "Descrição do item não pode conter mais de 255 caracteres.";
    private final Integer httpStatus = 400;
}
