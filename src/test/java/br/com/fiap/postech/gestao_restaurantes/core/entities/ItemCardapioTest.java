package br.com.fiap.postech.gestao_restaurantes.core.entities;

import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.DescricaoItemCardapioInvalidaException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.FotoItemCardapioInvalidaException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.NomeItemCardapioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.PrecoItemCardapioInvalidoException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class ItemCardapioTest {
	private static final Long ID = 1L;
	private static final String NOME = "Pizza";
	private static final String NOME_INVALIDO = "P".repeat(51);
	private static final String DESCRICAO = "Pizza de queijo com tomate";
	private static final String DESCRICAO_INVALIDA = "D".repeat(256);
	private static final BigDecimal PRECO_VALIDO = BigDecimal.valueOf(20.00);
	private static final BigDecimal PRECO_INVALIDO = BigDecimal.valueOf(1.00);
	private static final boolean DISPONIVEL = true;
	private static final String FOTO_VALIDA = "https://site.com/imagem.jpg";
	private static final String FOTO_INVALIDA = "imagem.txt";
	private static final String CPF = "123.456.789-00";
	private static final String NOME_USUARIO = "Nome Usuário";
	private static final String EMAIL = "email@teste.com";
	private static final String LOGIN = "user";
	private static final String SENHA = "Senha@1234";
	private static final Long TIPO_USUARIO_ID = 1L;
	private static final String TIPO_USUARIO_NOME = "teste";
	private static final String LOGRADOURO = "Rua Teste";
	private static final String NUMERO = "100";
	private static final String COMPLEMENTO = "Apto 10";
	private static final String BAIRRO = "Bairro Teste";
	private static final String CIDADE = "Cidade Teste";
	private static final String ESTADO = "SP";
	private static final String CEP = "01234-567";

	private Restaurante buildRestaurante() {
		return Restaurante.create(1L, "Restaurante Teste", "Italiana", "08:00-18:00", buildUsuario(), buildEndereco());
	}

	private Usuario buildUsuario() {
		return Usuario.create(CPF, NOME_USUARIO, EMAIL, LOGIN, SENHA, LocalDateTime.now(),
				TipoUsuario.create(TIPO_USUARIO_ID, TIPO_USUARIO_NOME), buildEndereco());
	}

	private Endereco buildEndereco() {
		return Endereco.create(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP);
	}

	@Test
	void deveCriarItemCardapioComSucesso() {
		Restaurante restaurante = buildRestaurante();
		ItemCardapio item = ItemCardapio.create(NOME, DESCRICAO, PRECO_VALIDO, DISPONIVEL, FOTO_VALIDA, restaurante);
		Assertions.assertThat(item.getNome()).isEqualTo(NOME);
		Assertions.assertThat(item.getDescricao()).isEqualTo(DESCRICAO);
		Assertions.assertThat(item.getPreco()).isEqualTo(PRECO_VALIDO);
		Assertions.assertThat(item.isDisponivelApenasNoRestaurante()).isEqualTo(DISPONIVEL);
		Assertions.assertThat(item.getFoto()).isEqualTo(FOTO_VALIDA);
		Assertions.assertThat(item.getRestaurante()).isEqualTo(restaurante);
	}

	@Test
	void deveCriarItemCardapioComIdComSucesso() {
		Restaurante restaurante = buildRestaurante();
		ItemCardapio item = ItemCardapio.create(ID, NOME, DESCRICAO, PRECO_VALIDO, DISPONIVEL, FOTO_VALIDA,
				restaurante);
		Assertions.assertThat(item.getId()).isEqualTo(ID);
		Assertions.assertThat(item.getNome()).isEqualTo(NOME);
		Assertions.assertThat(item.getDescricao()).isEqualTo(DESCRICAO);
		Assertions.assertThat(item.getPreco()).isEqualTo(PRECO_VALIDO);
		Assertions.assertThat(item.isDisponivelApenasNoRestaurante()).isEqualTo(DISPONIVEL);
		Assertions.assertThat(item.getFoto()).isEqualTo(FOTO_VALIDA);
		Assertions.assertThat(item.getRestaurante()).isEqualTo(restaurante);
	}

	@Test
	void deveLancarExceptionParaNomeInvalido() {
		Restaurante restaurante = buildRestaurante();
		NomeItemCardapioInvalidoException exception = org.junit.jupiter.api.Assertions.assertThrows(
			NomeItemCardapioInvalidoException.class,
			() -> ItemCardapio.create(NOME_INVALIDO, DESCRICAO, PRECO_VALIDO, DISPONIVEL, FOTO_VALIDA, restaurante)
		);
		Assertions.assertThat(exception.getCode()).isEqualTo("itemCardapio.nomeInvalido");
		Assertions.assertThat(exception.getMessage()).isEqualTo("Nome do item do cardápio inválido!");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(400);
	}

	@Test
	void deveLancarExceptionParaDescricaoInvalida() {
		Restaurante restaurante = buildRestaurante();
		DescricaoItemCardapioInvalidaException exception = org.junit.jupiter.api.Assertions.assertThrows(
			DescricaoItemCardapioInvalidaException.class,
			() -> ItemCardapio.create(NOME, DESCRICAO_INVALIDA, PRECO_VALIDO, DISPONIVEL, FOTO_VALIDA, restaurante)
		);
		Assertions.assertThat(exception.getCode()).isEqualTo("itemCardapio.descricaoInvalida");
		Assertions.assertThat(exception.getMessage()).isEqualTo("Descrição do item do cardápio inválida!");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(400);
	}

	@Test
	void deveLancarExceptionParaPrecoInvalido() {
		Restaurante restaurante = buildRestaurante();
		PrecoItemCardapioInvalidoException exception = org.junit.jupiter.api.Assertions.assertThrows(
			PrecoItemCardapioInvalidoException.class,
			() -> ItemCardapio.create(NOME, DESCRICAO, PRECO_INVALIDO, DISPONIVEL, FOTO_VALIDA, restaurante)
		);
		Assertions.assertThat(exception.getCode()).isEqualTo("itemCardapio.precoInvalido");
		Assertions.assertThat(exception.getMessage()).isEqualTo("Preço do item do cardápio inválido!");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(400);
	}

	@Test
	void deveLancarExceptionParaFotoInvalida() {
		Restaurante restaurante = buildRestaurante();
		FotoItemCardapioInvalidaException exception = org.junit.jupiter.api.Assertions.assertThrows(
			FotoItemCardapioInvalidaException.class,
			() -> ItemCardapio.create(NOME, DESCRICAO, PRECO_VALIDO, DISPONIVEL, FOTO_INVALIDA, restaurante)
		);
		Assertions.assertThat(exception.getCode()).isEqualTo("itemCardapio.fotoInvalida");
		Assertions.assertThat(exception.getMessage()).isEqualTo("Foto do item do cardápio inválida!");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(400);
	}

	@Test
	void deveTestarSettersComValidacao() {
		Restaurante restaurante = buildRestaurante();
		ItemCardapio item = ItemCardapio.create(NOME, DESCRICAO, PRECO_VALIDO, DISPONIVEL, FOTO_VALIDA, restaurante);
		item.setNome(NOME);
		item.setDescricao(DESCRICAO);
		item.setPreco(PRECO_VALIDO);
		item.setFoto(FOTO_VALIDA);
		Assertions.assertThat(item.getNome()).isEqualTo(NOME);
		Assertions.assertThat(item.getDescricao()).isEqualTo(DESCRICAO);
		Assertions.assertThat(item.getPreco()).isEqualTo(PRECO_VALIDO);
		Assertions.assertThat(item.getFoto()).isEqualTo(FOTO_VALIDA);
	}

	@Test
	void deveTestarEqualsAndHashCode() {
		Restaurante restaurante = buildRestaurante();
		ItemCardapio i1 = ItemCardapio.create(ID, NOME, DESCRICAO, PRECO_VALIDO, DISPONIVEL, FOTO_VALIDA, restaurante);
		ItemCardapio i2 = ItemCardapio.create(ID, NOME, DESCRICAO, PRECO_VALIDO, DISPONIVEL, FOTO_VALIDA, restaurante);
		ItemCardapio i3 = ItemCardapio.create("Outro", "Outro", PRECO_VALIDO, DISPONIVEL, FOTO_VALIDA, restaurante);
		Assertions.assertThat(i1).isEqualTo(i2);
		Assertions.assertThat(i1.hashCode()).isEqualTo(i2.hashCode());
		Assertions.assertThat(i1).isNotEqualTo(i3);
	}
}
