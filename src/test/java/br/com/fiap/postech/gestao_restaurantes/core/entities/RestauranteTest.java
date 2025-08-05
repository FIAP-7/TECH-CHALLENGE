package br.com.fiap.postech.gestao_restaurantes.core.entities;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RestauranteTest {
    private static final Long ID = 1L;
    private static final String NOME = "Restaurante Teste";
    private static final String TIPO_COZINHA = "Italiana";
    private static final String HORARIO = "08:00-18:00";
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

    private Usuario buildUsuario() {
        return Usuario.create(
            CPF,
            NOME_USUARIO,
            EMAIL,
            LOGIN,
            SENHA,
            LocalDateTime.now(),
            TipoUsuario.create(TIPO_USUARIO_ID, TIPO_USUARIO_NOME),
            buildEndereco()
        );
    }

    private Endereco buildEndereco() {
        return Endereco.create(
            LOGRADOURO,
            NUMERO,
            COMPLEMENTO,
            BAIRRO,
            CIDADE,
            ESTADO,
            CEP
        );
    }

    @Test
    void deveCriarRestauranteComSucesso() {
        Usuario usuario = buildUsuario();
        Endereco endereco = buildEndereco();
        Restaurante restaurante = Restaurante.create(NOME, TIPO_COZINHA, HORARIO, usuario, endereco);
        Assertions.assertThat(restaurante.getNome()).isEqualTo(NOME);
        Assertions.assertThat(restaurante.getTipoCozinha()).isEqualTo(TIPO_COZINHA);
        Assertions.assertThat(restaurante.getHorarioFuncionamento()).isEqualTo(HORARIO);
        Assertions.assertThat(restaurante.getUsuario()).isEqualTo(usuario);
        Assertions.assertThat(restaurante.getEndereco()).isEqualTo(endereco);
    }

    @Test
    void deveCriarRestauranteComIdComSucesso() {
        Usuario usuario = buildUsuario();
        Endereco endereco = buildEndereco();
        Restaurante restaurante = Restaurante.create(ID, NOME, TIPO_COZINHA, HORARIO, usuario, endereco);
        Assertions.assertThat(restaurante.getId()).isEqualTo(ID);
        Assertions.assertThat(restaurante.getNome()).isEqualTo(NOME);
        Assertions.assertThat(restaurante.getTipoCozinha()).isEqualTo(TIPO_COZINHA);
        Assertions.assertThat(restaurante.getHorarioFuncionamento()).isEqualTo(HORARIO);
        Assertions.assertThat(restaurante.getUsuario()).isEqualTo(usuario);
        Assertions.assertThat(restaurante.getEndereco()).isEqualTo(endereco);
    }

    @Test
    void deveLancarExceptionQuandoDadosSaoNulos() {
        Usuario usuario = buildUsuario();
        Endereco endereco = buildEndereco();
        validacaoException(null, TIPO_COZINHA, HORARIO, usuario, endereco);
        validacaoException(NOME, null, HORARIO, usuario, endereco);
        validacaoException(NOME, TIPO_COZINHA, null, usuario, endereco);
        validacaoException(NOME, TIPO_COZINHA, HORARIO, null, endereco);
        validacaoException(NOME, TIPO_COZINHA, HORARIO, usuario, null);
    }

	private void validacaoException(String nome, String tipoCozinha, String horarioFuncionamento, Usuario usuario, Endereco endereco) {
		Assertions.assertThatThrownBy(() -> Restaurante.create(nome, tipoCozinha, horarioFuncionamento, usuario, endereco))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
	}

    @Test
    void deveLancarExceptionQuandoIdEhNuloNoCreateComId() {
        Usuario usuario = buildUsuario();
        Endereco endereco = buildEndereco();
        Assertions.assertThatThrownBy(() -> Restaurante.create(null, NOME, TIPO_COZINHA, HORARIO, usuario, endereco))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveTestarEqualsAndHashCode() {
        Usuario usuario = buildUsuario();
        Endereco endereco = buildEndereco();
        Restaurante r1 = Restaurante.create(ID, NOME, TIPO_COZINHA, HORARIO, usuario, endereco);
        Restaurante r2 = Restaurante.create(ID, NOME, TIPO_COZINHA, HORARIO, usuario, endereco);
        Restaurante r3 = Restaurante.create("Outro", "Outro", "Outro", usuario, endereco);
        Assertions.assertThat(r1).isEqualTo(r2);
        Assertions.assertThat(r1.hashCode()).isEqualTo(r2.hashCode());
        Assertions.assertThat(r1).isNotEqualTo(r3);
    }
}
