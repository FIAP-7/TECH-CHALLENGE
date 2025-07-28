package br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "idTipoUsuario")
    private TipoUsuarioEntity tipoUsuario;

    private LocalDateTime dataUltimaAlteracao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEndereco")
    private EnderecoEntity endereco;

}
