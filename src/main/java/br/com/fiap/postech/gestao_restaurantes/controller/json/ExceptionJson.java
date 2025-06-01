package br.com.fiap.postech.gestao_restaurantes.controller.json;

import br.com.fiap.postech.gestao_restaurantes.exception.SystemBaseException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Objeto de erro retornado em exceções")
public class ExceptionJson {

    @Schema(description = "Código do erro", example = "USR-404")
    private final String code;
    @Schema(description = "Mensagem descritiva do erro", example = "Usuário não encontrado")
    private final String message;

    public ExceptionJson(final SystemBaseException baseException){
        this.code = baseException.getCode();
        this.message = baseException.getMessage();
    }

}
