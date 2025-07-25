package br.com.fiap.postech.gestao_restaurantes.controller.json;

import br.com.fiap.postech.gestao_restaurantes.domain.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representação do tipo de usuário")
public class TipoUsuarioJson {
	@Schema(description = "ID do tipo de usuário", example = "1")
	private Integer id;
	
	@NotBlank
    @Schema(description = "Nome do tipo", example = "Proprietário")
	private String nome;
	
	public TipoUsuario mapToDomain(){
        return new TipoUsuario(
                id,
                nome
        );
    }
}
