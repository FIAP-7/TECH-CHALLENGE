package br.com.fiap.postech.gestao_restaurantes.core.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import br.com.fiap.postech.gestao_restaurantes.core.exception.CepInvalidoException;

class EnderecoTest {
    private static final String LOGRADOURO = "Rua X";
    private static final String NUMERO = "123";
    private static final String COMPLEMENTO = "Apto 1";
    private static final String BAIRRO = "Bairro Y";
    private static final String CIDADE = "Cidade Z";
    private static final String ESTADO = "UF";
    private static final String CEP_VALIDO = "12345-678";
    private static final String CEP_INVALIDO = "12567";
    private static final String LOGRADOURO_DIFF = "Rua Y";
    private static final String NUMERO_DIFF = "456";
    private static final String COMPLEMENTO_DIFF = "Casa";
    private static final String BAIRRO_DIFF = "Bairro Z";
    private static final String CIDADE_DIFF = "Cidade W";
    private static final String ESTADO_DIFF = "SP";
    private static final String CEP_DIFF = "98765-432";

    @Test
    void deveCriarEnderecoComTodosOsCampos() {
        Endereco endereco = Endereco.create(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP_VALIDO);
        assertThat(endereco).isNotNull();
        assertThat(endereco.getLogradouro()).isEqualTo(LOGRADOURO);
        assertThat(endereco.getNumero()).isEqualTo(NUMERO);
        assertThat(endereco.getComplemento()).isEqualTo(COMPLEMENTO);
        assertThat(endereco.getBairro()).isEqualTo(BAIRRO);
        assertThat(endereco.getCidade()).isEqualTo(CIDADE);
        assertThat(endereco.getEstado()).isEqualTo(ESTADO);
        assertThat(endereco.getCep()).isEqualTo(CEP_VALIDO);
    }

    @Test
    void deveLancarIllegalArgumentExceptionQuandoLogradouroNull() {
        assertThatThrownBy(() -> Endereco.create(null, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP_VALIDO))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveLancarIllegalArgumentExceptionQuandoNumeroNull() {
        assertThatThrownBy(() -> Endereco.create(LOGRADOURO, null, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP_VALIDO))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveLancarIllegalArgumentExceptionQuandoComplementoNull() {
        assertThatThrownBy(() -> Endereco.create(LOGRADOURO, NUMERO, null, BAIRRO, CIDADE, ESTADO, CEP_VALIDO))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveLancarIllegalArgumentExceptionQuandoBairroNull() {
        assertThatThrownBy(() -> Endereco.create(LOGRADOURO, NUMERO, COMPLEMENTO, null, CIDADE, ESTADO, CEP_VALIDO))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveLancarIllegalArgumentExceptionQuandoCidadeNull() {
        assertThatThrownBy(() -> Endereco.create(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, null, ESTADO, CEP_VALIDO))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveLancarIllegalArgumentExceptionQuandoEstadoNull() {
        assertThatThrownBy(() -> Endereco.create(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, null, CEP_VALIDO))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveLancarIllegalArgumentExceptionQuandoCepNull() {
        assertThatThrownBy(() -> Endereco.create(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Faltam dados");
    }

    @Test
    void deveLancarCepInvalidoExceptionQuandoCepInvalido() {
        CepInvalidoException exception = assertThrows(CepInvalidoException.class, 
                () -> Endereco.create(1L, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP_INVALIDO));
        assertThat(exception.getCode()).isEqualTo("usuario.cepInvalido");
        assertThat(exception.getMessage()).isEqualTo("Cep com o formato incorreto");
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void deveValidarEqualsAndHashCode() {
        Endereco endereco1 = Endereco.create(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP_VALIDO);
        Endereco endereco2 = Endereco.create(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP_VALIDO);
        Endereco endereco3 = Endereco.create(LOGRADOURO_DIFF, NUMERO_DIFF, COMPLEMENTO_DIFF, BAIRRO_DIFF, CIDADE_DIFF, ESTADO_DIFF, CEP_DIFF);

        assertThat(endereco1).isEqualTo(endereco2);
        assertThat(endereco1.hashCode()).isEqualTo(endereco2.hashCode());
        assertThat(endereco1).isNotEqualTo(endereco3);
        assertThat(endereco1.hashCode()).isNotEqualTo(endereco3.hashCode());
    }
}
