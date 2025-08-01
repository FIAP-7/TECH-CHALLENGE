package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardaptio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class NomeItemCardapioInvalidoException extends SystemBaseException {
    private final String code = "ItemCardapio.nomeInvalido";
    private final String message = "Nome do item não pode ser vazio.";
    private final Integer httpStatus = 400;
}
