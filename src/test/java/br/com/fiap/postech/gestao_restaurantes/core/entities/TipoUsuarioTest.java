package br.com.fiap.postech.gestao_restaurantes.core.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class TipoUsuarioTest {
    private static final String ADMIN = "ADMIN";
    private static final Long ID = 1L;
    @Test
    void deveCriarTipoUsuarioComNome() {
        TipoUsuario tipoUsuario = TipoUsuario.create(ADMIN);
        assertThat(tipoUsuario).isNotNull();
        assertThat(tipoUsuario.getNome()).isEqualTo(ADMIN);
    }

    @Test
    void deveCriarTipoUsuarioComIdENome() {
        TipoUsuario tipoUsuario = TipoUsuario.create(ID, ADMIN);
        assertThat(tipoUsuario).isNotNull();
        assertThat(tipoUsuario.getId()).isEqualTo(ID);
        assertThat(tipoUsuario.getNome()).isEqualTo(ADMIN);
    }

    @Test
    void deveLancarExceptionQuandoNomeNull() {
        assertThatThrownBy(() -> TipoUsuario.create(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveValidarEqualsAndHashCode() {
        TipoUsuario tipoUsuario1 = TipoUsuario.create(ID, ADMIN);
        TipoUsuario tipoUsuario2 = TipoUsuario.create(ID, ADMIN);
        TipoUsuario tipoUsuario3 = TipoUsuario.create(2L, "USER");

        assertThat(tipoUsuario1).isEqualTo(tipoUsuario2);
        assertThat(tipoUsuario1.hashCode()).isEqualTo(tipoUsuario2.hashCode());
        assertThat(tipoUsuario1).isNotEqualTo(tipoUsuario3);
        assertThat(tipoUsuario1.hashCode()).isNotEqualTo(tipoUsuario3.hashCode());
    }
}
