package br.com.fiap.postech.gestao_restaurantes.infra.controller.json;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representação de um usuário")
public class RestauranteJson {
	
    @Schema(description = "ID do restaurante", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nome do restaurante", example = "Marmitas da Joana", required = true)
    private String nome;

    @NotBlank
    @Schema(description = "Tipo de cozinha", example = "Oriental", required = true)
    private String tipoCozinha;

    @NotBlank
    @Schema(description = "Horário de funcionamento", example = "Segunda - Sexta, das 11h às 22h", required = true)
    private String horarioFuncionamento;

    @NotNull
    @Schema(description = "Usuário proprietário do restaurante")
    private UsuarioJson usuario;
    
    @Valid
    @Schema(description = "Endereço do restaurante")
    private EnderecoJson endereco;
    
    public NovoRestauranteDTO mapToNovoRestauranteDTO(){
        return new NovoRestauranteDTO (
                nome,
                tipoCozinha,
                horarioFuncionamento,
                usuario.mapToUsuarioRestauranteDTO(),
                endereco.mapToDTO()
        );
    }

    public RestauranteDTO mapToDTO(){
        return new RestauranteDTO (
                id,
                nome,
                tipoCozinha,
                horarioFuncionamento,
                usuario.mapToUsuarioRestauranteDTO(),
                endereco.mapToDTO()
        );
    }

}
