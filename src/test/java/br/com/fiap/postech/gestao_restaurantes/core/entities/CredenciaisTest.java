package br.com.fiap.postech.gestao_restaurantes.core.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CredenciaisTest {
    private static final String LOGIN = "user";
    private static final String SENHA = "pass";
    @Test
    void deveCriarCredenciaisComLoginESenha() {
        Credenciais credenciais = Credenciais.create(LOGIN, SENHA);
        assertThat(credenciais).isNotNull();
        assertThat(credenciais.getLogin()).isEqualTo(LOGIN);
        assertThat(credenciais.getSenha()).isEqualTo(SENHA);
    }

    @Test
    void deveLancarExceptionQuandoLoginNull() {
        assertThatThrownBy(() -> Credenciais.create(null, SENHA))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deveLancarExceptionQuandoLoginVazio() {
        assertThatThrownBy(() -> Credenciais.create("", SENHA))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deveLancarExceptionQuandoSenhaNull() {
        assertThatThrownBy(() -> Credenciais.create(LOGIN, null))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deveLancarExceptionQuandoSenhaVazia() {
        assertThatThrownBy(() -> Credenciais.create(LOGIN, ""))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deveValidarEqualsAndHashCode() {
        Credenciais credenciais1 = Credenciais.create(LOGIN, SENHA);
        Credenciais credenciais2 = Credenciais.create(LOGIN, SENHA);
        Credenciais credenciais3 = Credenciais.create("outro", "diferente");

        assertThat(credenciais1).isEqualTo(credenciais2);
        assertThat(credenciais1.hashCode()).isEqualTo(credenciais2.hashCode());
        assertThat(credenciais1).isNotEqualTo(credenciais3);
        assertThat(credenciais1.hashCode()).isNotEqualTo(credenciais3.hashCode());
    }
}
