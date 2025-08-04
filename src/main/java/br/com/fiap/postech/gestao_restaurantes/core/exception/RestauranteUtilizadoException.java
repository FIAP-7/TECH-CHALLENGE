package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class RestauranteUtilizadoException extends SystemBaseException {

    private final String code = "usuario.usuarioUtilizado";
    private final String message = "Este restaurante está vinculado a um ou mais itens de cardápio!";
    private final Integer httpStatus = 400;
}
