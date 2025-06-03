package br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="usuario")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String nome;

    private String email;

    private String login;

    private String senha;

    private Integer tipoUsuario;

    private LocalDateTime dataUltimaAlteracao;

    @ManyToOne
    @JoinColumn(name = "idEndereco")
    private EnderecoEntity endereco;

}
