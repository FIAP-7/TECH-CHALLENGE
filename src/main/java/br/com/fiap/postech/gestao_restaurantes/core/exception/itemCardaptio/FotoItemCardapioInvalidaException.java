package br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardaptio;

import br.com.fiap.postech.gestao_restaurantes.core.exception.SystemBaseException;
import lombok.Getter;

@Getter
public class FotoItemCardapioInvalidaException extends SystemBaseException {
    private final String code = "CardapioItem.fotoInvalida";
    private final String message = "URL da foto inválida.";
    private final Integer httpStatus = 401;
}
