package br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itemCardapio")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemCardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal preco;

    private Boolean disponivelApenasNoRestaurante;

    private String foto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRestaurante")
    private RestauranteEntity restaurante;
}
