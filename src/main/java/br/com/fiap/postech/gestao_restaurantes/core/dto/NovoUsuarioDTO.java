package br.com.fiap.postech.gestao_restaurantes.core.dto;

import java.time.LocalDateTime;

public record NovoUsuarioDTO(String cpf, String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao, TipoUsuarioDTO tipoUsuario, EnderecoDTO endereco) {
}
