package br.com.fiap.postech.gestao_restaurantes.core.dto;

public record EnderecoDTO(Long id, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
}
