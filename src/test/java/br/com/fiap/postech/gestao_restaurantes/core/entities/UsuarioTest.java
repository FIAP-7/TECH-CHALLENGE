package br.com.fiap.postech.gestao_restaurantes.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.postech.gestao_restaurantes.core.exception.CpfUsuarioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EmailUsuarioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.NomeUsuarioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.SenhaFormatoInvalidoException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

class UsuarioTest {
	private static final Long ID = 1L;
	private static final String CPF = "541.456.564-54";
	private static final String NOME = "Nome";
	private static final String EMAIL = "email@teste.com";
	private static final String LOGIN = "login";
	private static final String SENHA = "Senha$123";
	private static final String CPF_VALIDO = "541.456.564-54";
	private static final String CPF_INVALIDO = "12345678900";
	private static final String EMAIL_INVALIDO = "email_invalido";
	private static final String NOME_INVALIDO = "N0m3!";
	private static final String SENHA_INVALIDA = "senha";

	@Mock
	TipoUsuario tipoUsuario;

	@Mock
	Endereco endereco;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveCriarUsuarioComTodosOsCampos() {
		Usuario usuario = Usuario.create(ID, CPF, NOME, EMAIL, LOGIN, SENHA, LocalDateTime.now(), tipoUsuario,
				endereco);
		assertThat(usuario).isNotNull();
		assertThat(usuario.getCpf()).isEqualTo(CPF);
		assertThat(usuario.getNome()).isEqualTo(NOME);
		assertThat(usuario.getEmail()).isEqualTo(EMAIL);
		assertThat(usuario.getLogin()).isEqualTo(LOGIN);
		assertThat(usuario.getSenha()).isEqualTo(SENHA);
		assertThat(usuario.getTipoUsuario()).isEqualTo(tipoUsuario);
		assertThat(usuario.getEndereco()).isEqualTo(endereco);
	}
	
	@Test
	void deveCriarUsuarioSemID() {
		Usuario usuario = Usuario.create(CPF, NOME, EMAIL, LOGIN, SENHA, LocalDateTime.now(), tipoUsuario,
				endereco);
		assertThat(usuario).isNotNull();
		assertThat(usuario.getCpf()).isEqualTo(CPF);
		assertThat(usuario.getNome()).isEqualTo(NOME);
		assertThat(usuario.getEmail()).isEqualTo(EMAIL);
		assertThat(usuario.getLogin()).isEqualTo(LOGIN);
		assertThat(usuario.getSenha()).isEqualTo(SENHA);
		assertThat(usuario.getTipoUsuario()).isEqualTo(tipoUsuario);
		assertThat(usuario.getEndereco()).isEqualTo(endereco);
	}


	@Test
	void deveLancarIllegalArgumentExceptionQuandoCpfNull() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> Usuario.create(null, NOME, EMAIL, LOGIN, SENHA, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getMessage()).contains("Faltam dados");
	}

	@Test
	void deveLancarIllegalArgumentExceptionQuandoNomeNull() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> Usuario.create(CPF, null, EMAIL, LOGIN, SENHA, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getMessage()).contains("Faltam dados");
	}

	@Test
	void deveLancarIllegalArgumentExceptionQuandoEmailNull() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> Usuario.create(CPF, NOME, null, LOGIN, SENHA, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getMessage()).contains("Faltam dados");
	}

	@Test
	void deveLancarIllegalArgumentExceptionQuandoLoginNull() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> Usuario.create(CPF, NOME, EMAIL, null, SENHA, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getMessage()).contains("Faltam dados");
	}

	@Test
	void deveLancarIllegalArgumentExceptionQuandoSenhaNull() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> Usuario.create(CPF, NOME, EMAIL, LOGIN, null, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getMessage()).contains("Faltam dados");
	}

	@Test
	void deveLancarIllegalArgumentExceptionQuandoEnderecoNull() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
			() -> Usuario.create(CPF, NOME, EMAIL, LOGIN, SENHA, LocalDateTime.now(), tipoUsuario, null));
		assertThat(exception.getMessage()).contains("Faltam dados");
	}

	@Test
	void deveLancarCpfUsuarioInvalidoException() {
		CpfUsuarioInvalidoException exception = assertThrows(CpfUsuarioInvalidoException.class, () -> Usuario
				.create(CPF_INVALIDO, NOME, EMAIL, LOGIN, SENHA_INVALIDA, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getCode()).isEqualTo("usuario.cpfInvalido");
		assertThat(exception.getMessage()).isEqualTo("CPF necessita de 14 caracteres no formato (123.456.789-00)");
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
	}

	@Test
	void deveLancarEmailUsuarioInvalidoException() {
		EmailUsuarioInvalidoException exception = assertThrows(EmailUsuarioInvalidoException.class, () -> Usuario
				.create(CPF_VALIDO, NOME, EMAIL_INVALIDO, LOGIN, SENHA, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getCode()).isEqualTo("usuario.emailInvalido");
		assertThat(exception.getMessage()).isEqualTo("Endereco de email invalido");
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
	}

	@Test
	void deveLancarNomeUsuarioInvalidoException() {
		NomeUsuarioInvalidoException exception = assertThrows(NomeUsuarioInvalidoException.class, () -> Usuario
				.create(CPF_VALIDO, NOME_INVALIDO, EMAIL, LOGIN, SENHA_INVALIDA, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getCode()).isEqualTo("usuario.nomeInvalido");
		assertThat(exception.getMessage()).isEqualTo("Nome não permite números e caracteres especiais!");
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
	}

	@Test
	void deveLancarSenhaFormatoInvalidoException() {
		SenhaFormatoInvalidoException exception = assertThrows(SenhaFormatoInvalidoException.class, () -> Usuario
				.create(CPF_VALIDO, NOME, EMAIL, LOGIN, SENHA_INVALIDA, LocalDateTime.now(), tipoUsuario, endereco));
		assertThat(exception.getCode()).isEqualTo("usuario.senhaFormatoInvalido");
		assertThat(exception.getMessage()).isEqualTo("Senha precisa ter mais de 8 caracteres e possuir pelo menos uma letra maiúscula, uma letra minúscula, um número e um caracter especial.");
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
	}
}
