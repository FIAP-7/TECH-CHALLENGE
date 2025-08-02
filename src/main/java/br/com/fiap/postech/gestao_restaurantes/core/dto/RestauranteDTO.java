package br.com.fiap.postech.gestao_restaurantes.core.dto;

public record RestauranteDTO(Long id, String nome, String tipoCozinha, String horarioFuncionamento, UsuarioRestauranteDTO usuario, EnderecoDTO endereco) {
}
