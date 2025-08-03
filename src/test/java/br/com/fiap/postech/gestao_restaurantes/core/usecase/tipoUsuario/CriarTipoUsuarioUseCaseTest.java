package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioMesmoNomeExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler.TipoUsuarioExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler.TipoUsuarioHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler.TipoUsuarioNaoExistenteHandlerTest;

class CriarTipoUsuarioUseCaseTest {
	
	private static final String ADMIN = "ADMIN";
	@Mock
	private ITipoUsuarioGateway tipoUsuarioGateway;
	private CriarTipoUsuarioUseCase useCase;
	private AutoCloseable mock;

	@BeforeEach
	void setUp() {
		mock = MockitoAnnotations.openMocks(this);
		useCase = CriarTipoUsuarioUseCase.create(tipoUsuarioGateway);
	}

	@AfterEach
	void tearDown() throws Exception {
		mock.close();
	}

	@Test
	void deveCriarTipoUsuarioComSucesso() {
		NovoTipoUsuarioDTO dto = mock(NovoTipoUsuarioDTO.class);
		when(dto.nome()).thenReturn("ADMIN");
		when(tipoUsuarioGateway.criar(any(TipoUsuario.class))).thenReturn(1L);

		Long id = useCase.executar(dto);
		Assertions.assertThat(id).isEqualTo(1L);
		verify(tipoUsuarioGateway).criar(any(TipoUsuario.class));
	}

	@Test
	void naoDeveCriarTipoUsuarioQuandoHandlerRetornaFalse() {
		NovoTipoUsuarioDTO dto = new NovoTipoUsuarioDTO(ADMIN);
		when(tipoUsuarioGateway.buscarPorNome(ADMIN)).thenReturn(Optional.of(new TipoUsuario()));
		TipoUsuarioMesmoNomeExistenteException exception = assertThrows(TipoUsuarioMesmoNomeExistenteException.class,
				() -> useCase.executar(dto));

		Assertions.assertThat(exception.getCode()).isEqualTo("tipoUsuario.tipoUsuarioMesmoNomeExistente");
		Assertions.assertThat(exception.getMessage()).isEqualTo("Já existe um tipo de usuário com este nome!");
		Assertions.assertThat(exception.getHttpStatus()).isEqualTo(400);
	}
}
