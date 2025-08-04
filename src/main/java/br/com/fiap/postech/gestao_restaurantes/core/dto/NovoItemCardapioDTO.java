package br.com.fiap.postech.gestao_restaurantes.core.dto;

import java.math.BigDecimal;

public record NovoItemCardapioDTO(String nome, String descricao, BigDecimal preco, boolean disponivelApenasNoRestaurante, String foto, Long restauranteId) {
}
