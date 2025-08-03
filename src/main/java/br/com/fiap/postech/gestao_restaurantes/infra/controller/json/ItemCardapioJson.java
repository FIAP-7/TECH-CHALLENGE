package br.com.fiap.postech.gestao_restaurantes.infra.controller.json;

import br.com.fiap.postech.gestao_restaurantes.core.dto.ItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representação de um item de cardápio")
public class ItemCardapioJson {

    @Schema(description = "ID do item de cardápio", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nome do item de cardápio", example = "Pizza Margherita", required = true)
    private String nome;

    @NotBlank
    @Schema(description = "Descrição do item de cardápio", example = "Pizza com molho de tomate, mussarela e manjericão", required = true)
    private String descricao;

    @NotNull
    @Positive
    @Schema(description = "Preço do item de cardápio", example = "29.99", required = true)
    private BigDecimal preco;

    @Schema(description = "Indica se o item está disponível para consumo apenas no restaurante", example = "true")
    private Boolean disponivelApenasNoRestaurante;

    @Schema(description = "URL da foto do item de cardápio", example = "https://example.com/foto-pizza.jpg")
    private String foto;

    @Schema(description = "Restaurante ao qual o item de cardápio pertence")
    private RestauranteJson restaurante;

    public ItemCardapioDTO mapToDTO() {
        return new ItemCardapioDTO(
                id,
                nome,
                descricao,
                preco,
                disponivelApenasNoRestaurante,
                foto,
                restaurante.mapToDTO()
        );
    }

    public NovoItemCardapioDTO mapToNovoDTO() {
        return new NovoItemCardapioDTO(
                nome,
                descricao,
                preco,
                disponivelApenasNoRestaurante,
                foto,
                restaurante.mapToDTO()
        );
    }
}