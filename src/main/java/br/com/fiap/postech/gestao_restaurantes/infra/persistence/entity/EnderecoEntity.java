package br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="endereco")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;

}
