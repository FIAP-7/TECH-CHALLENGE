package br.com.fiap.postech.gestao_restaurantes.domain;

import br.com.fiap.postech.gestao_restaurantes.domain.enumeration.TipoUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Usuario {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAlteracao;
    private TipoUsuarioEnum tipoUsuario;
    private Endereco endereco;


}
