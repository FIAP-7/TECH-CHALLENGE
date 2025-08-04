package br.com.fiap.postech.gestao_restaurantes.core.dto;

public record NovoRestauranteDTO(String nome, String tipoCozinha, String horarioFuncionamento, UsuarioRestauranteDTO usuario, EnderecoDTO endereco) {
}
