package br.com.fiap.postech.gestao_restaurantes.core.entities;

import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.DescricaoItemCardapioInvalidaException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.FotoItemCardapioInvalidaException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.NomeItemCardapioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.PrecoItemCardapioInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class ItemCardapio {

    public static final double PRECO_MINIMO = 1.99;

    private static final int TAMANHO_MAXIMO_DESCRICAO = 255;
    private static final int TAMANHO_MAXIMO_NOME = 50;
    @Setter
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    @Setter
    private boolean disponivelApenasNoRestaurante;
    private String foto;
    @Setter
    private Restaurante restaurante;

    private static void validarNome(String nome) {
        if (nome.length() > TAMANHO_MAXIMO_NOME) {
            throw new NomeItemCardapioInvalidoException();
        }
    }

    private static void validarDescricao(String descricao) {
        if (descricao.length() > TAMANHO_MAXIMO_DESCRICAO) {
            throw new DescricaoItemCardapioInvalidaException();
        }
    }

    private static void validarPreco(BigDecimal preco) {
        if (preco.compareTo(BigDecimal.valueOf(PRECO_MINIMO)) < 0) {
            throw new PrecoItemCardapioInvalidoException();
        }
    }

    private static void validarFoto(String foto) {
        Pattern fotoValidaPattern = Pattern.compile("^(https?|ftp)://[a-zA-Z0-9\\-._~:/?#\\[\\]@!$&'()*+,;=]+\\.(jpg|jpeg|png|gif|webp)$");

        Matcher matcher = fotoValidaPattern.matcher(foto);

        if (!matcher.matches()) {
            throw new FotoItemCardapioInvalidaException();
        }
    }

    public static ItemCardapio create(String nome, String descricao, BigDecimal preco, boolean disponivelApenasNoRestaurante, String foto, Restaurante restaurante) {
        validarNome(nome);
        validarDescricao(descricao);
        validarPreco(preco);
        validarFoto(foto);

        ItemCardapio item = new ItemCardapio();
        item.setNome(nome);
        item.setDescricao(descricao);
        item.setPreco(preco);
        item.setDisponivelApenasNoRestaurante(disponivelApenasNoRestaurante);
        item.setFoto(foto);
        item.setRestaurante(restaurante);

        return item;
    }

    public static ItemCardapio create(Long id, String nome, String descricao, BigDecimal preco, boolean disponivelApenasNoRestaurante, String foto, Restaurante restaurante) {
        validarNome(nome);
        validarDescricao(descricao);
        validarPreco(preco);
        validarFoto(foto);

        ItemCardapio item = new ItemCardapio();
        item.setId(id);
        item.setNome(nome);
        item.setDescricao(descricao);
        item.setPreco(preco);
        item.setDisponivelApenasNoRestaurante(disponivelApenasNoRestaurante);
        item.setFoto(foto);
        item.setRestaurante(restaurante);

        return item;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        validarDescricao(descricao);
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        validarPreco(preco);
        this.preco = preco;
    }

    public void setFoto(String foto) {
        validarFoto(foto);
        this.foto = foto;
    }
}
