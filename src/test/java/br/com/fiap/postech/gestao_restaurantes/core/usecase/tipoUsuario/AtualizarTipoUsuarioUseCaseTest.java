package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler.TipoUsuarioExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler.TipoUsuarioHandler;

class AtualizarTipoUsuarioUseCaseTest {
	
	@Mock
    private ITipoUsuarioGateway tipoUsuarioGateway;
    private AtualizarTipoUsuarioUseCase useCase;
    private AutoCloseable mock;
    private TipoUsuarioExistenteHandler handler;
    @Mock
    private TipoUsuarioHandler nextHandler;

    
    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = AtualizarTipoUsuarioUseCase.create(tipoUsuarioGateway);
    }
    
    @AfterEach
    void tearDown() throws Exception {
    	mock.close();
    }

    @Test
    void deveAtualizarTipoUsuarioComSucesso() {
        handler = new TipoUsuarioExistenteHandler(tipoUsuarioGateway);
        handler.setNext(nextHandler);

        TipoUsuarioDTO dto = new TipoUsuarioDTO(1L, "teste");
        doNothing().when(tipoUsuarioGateway).atualizar(anyLong(), any(TipoUsuario.class));
        when(tipoUsuarioGateway.buscarPorId(anyLong())).thenReturn(Optional.of(new TipoUsuario()));
        when(nextHandler.handle(any(TipoUsuario.class))).thenReturn(false);
        useCase.executar(1L, dto);
        verify(tipoUsuarioGateway, times(1)).atualizar(anyLong(), any(TipoUsuario.class));
    }
    
    @Test
    void deveLancarExceptionAoNaoEncrotrarTipoUsuario() {
    TipoUsuarioDTO dto = new TipoUsuarioDTO(1L, "teste");
    when(tipoUsuarioGateway.buscarPorId(anyLong())).thenReturn(Optional.empty());

    TipoUsuarioNaoEncontradoException exception = assertThrows(
        TipoUsuarioNaoEncontradoException.class,
        () -> useCase.executar(1L, dto)
    );

    Assertions.assertThat(exception.getCode())
        .isEqualTo("tipoUsuario.tipoUsuarioNaoEncontrado");
    Assertions.assertThat(exception.getMessage())
        .isEqualTo("Tipo de Usuário não encontrado!");
    Assertions.assertThat(exception.getHttpStatus())
        .isEqualTo(404);
    }

}
