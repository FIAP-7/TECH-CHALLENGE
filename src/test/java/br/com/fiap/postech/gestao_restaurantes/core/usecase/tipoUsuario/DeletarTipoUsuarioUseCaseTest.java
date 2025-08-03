package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeletarTipoUsuarioUseCaseTest {
	
	@Mock
    private ITipoUsuarioGateway tipoUsuarioGateway;
    private DeletarTipoUsuarioUseCase useCase;
	private AutoCloseable mock;

	@BeforeEach
    void setUp() {
		mock = MockitoAnnotations.openMocks(this);
        useCase = DeletarTipoUsuarioUseCase.create(tipoUsuarioGateway);
    }

	@AfterEach
	void tearDown() throws Exception {
		mock.close();
	}

    @Test
    void deveDeletarTipoUsuarioComSucesso() {
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(new TipoUsuario()));
        useCase.executar(1L);
        verify(tipoUsuarioGateway).deletar(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        when(tipoUsuarioGateway.buscarPorId(2L)).thenReturn(Optional.empty());
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> useCase.executar(2L));
    }
}
