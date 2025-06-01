package br.com.fiap.postech.gestao_restaurantes.controller.json;

import br.com.fiap.postech.gestao_restaurantes.domain.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Endereço do usuário")
public class EnderecoJson {

    @Schema(description = "ID do endereço", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Logradouro", example = "Rua das Flores", required = true)
    private String logradouro;

    @NotBlank
    @Schema(description = "Número", example = "123", required = true)
    private String numero;

    @Schema(description = "Complemento", example = "Apto 202")
    private String complemento;

    @NotBlank
    @Schema(description = "Bairro", example = "Jardim Primavera", required = true)
    private String bairro;

    @NotBlank
    @Schema(description = "Cidade", example = "São Paulo", required = true)
    private String cidade;

    @NotBlank
    @Schema(description = "Estado", example = "SP", required = true)
    private String estado;

    @NotBlank
    @Schema(description = "CEP", example = "01001-000", required = true)
    private String cep;


    public Endereco mapToDomain(){
        return new Endereco(
          id,
          logradouro,
          numero,
          complemento,
          bairro,
          cidade,
          estado,
          cep
        );
    }
}
