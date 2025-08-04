package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class NomeItemCardapioInvalidoException extends SystemBaseException {
    private final String code = "itemCardapio.nomeInvalido";
    private final String message = "Nome do item não pode conter mais de 50 caracteres.";
    private final Integer httpStatus = 400;
}
