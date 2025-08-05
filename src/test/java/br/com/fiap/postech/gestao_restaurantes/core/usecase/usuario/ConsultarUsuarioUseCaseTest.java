package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

class ConsultarUsuarioUseCaseTest {
	@Mock
	private IUsuarioGateway usuarioGateway;
	private ConsultarUsuarioUseCase useCase;
	private AutoCloseable mock;

	@BeforeEach
	void setUp() {
		mock = MockitoAnnotations.openMocks(this);
		useCase = ConsultarUsuarioUseCase.create(usuarioGateway);
	}

	@AfterEach
	void tearDown() throws Exception {
		mock.close();
	}

	@Test
	void deveRetornarUsuarioQuandoEncontrado() {
		Usuario usuario = new Usuario();
		when(usuarioGateway.buscarPorId(anyLong())).thenReturn(usuario);
		Usuario resultado = useCase.executar(1L);
		assertThat(resultado).isNotNull();
		assertThat(resultado).isEqualTo(usuario);
	}

	@Test
	void deveLancarExceptionQuandoUsuarioNaoEncontrado() {
		when(usuarioGateway.buscarPorId(anyLong())).thenReturn(null);
		UsuarioNaoEncontradoException exception = assertThrows(UsuarioNaoEncontradoException.class,
				() -> useCase.executar(1L));
		assertThat(exception).isNotNull();
		assertThat(exception.getCode()).isEqualTo("usuario.naoEncontrado");
		assertThat(exception.getMessage()).isEqualTo("Usuário não encontrado!");
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}
}
