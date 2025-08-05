package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.AtualizarTipoUsuarioUseCase;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AtualizarTipoUsuarioUseCaseTest {
    private static final Long TIPO_USUARIO_ID = 1L;
    private static final String TIPO_USUARIO_NAO_ENCONTRADO_CODE = "tipoUsuario.tipoUsuarioNaoEncontrado";
    private static final String TIPO_USUARIO_NAO_ENCONTRADO_MSG = "Tipo de Usuário não encontrado!";
    private static final int HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND.value();

    @Mock
    private ITipoUsuarioGateway tipoUsuarioGateway;
	@Mock
	TipoUsuarioDTO tipoUsuarioDTO;
    private AtualizarTipoUsuarioUseCase useCase;
    private AutoCloseable mock;

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
        when(tipoUsuarioGateway.buscarPorId(anyLong())).thenReturn(Optional.of(new TipoUsuario()));
        doNothing().when(tipoUsuarioGateway).atualizar(anyLong(), any(TipoUsuario.class));
        useCase.executar(TIPO_USUARIO_ID, tipoUsuarioDTO);
        verify(tipoUsuarioGateway, times(1)).atualizar(anyLong(), any(TipoUsuario.class));
    }

    @Test
    void deveLancarExceptionAoNaoEncontrarTipoUsuario() {
        when(tipoUsuarioGateway.buscarPorId(TIPO_USUARIO_ID)).thenReturn(Optional.empty());
        TipoUsuarioNaoEncontradoException exception = assertThrows(
            TipoUsuarioNaoEncontradoException.class,
            () -> useCase.executar(TIPO_USUARIO_ID, tipoUsuarioDTO)
        );
        Assertions.assertThat(exception.getCode()).isEqualTo(TIPO_USUARIO_NAO_ENCONTRADO_CODE);
        Assertions.assertThat(exception.getMessage()).isEqualTo(TIPO_USUARIO_NAO_ENCONTRADO_MSG);
        Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HTTP_STATUS_NOT_FOUND);
    }

    @Test
    void naoDeveAtualizarQuandoTipoUsuarioNaoExiste() {
        when(tipoUsuarioGateway.buscarPorId(TIPO_USUARIO_ID)).thenReturn(Optional.empty());
        assertThrows(TipoUsuarioNaoEncontradoException.class,
            () -> useCase.executar(TIPO_USUARIO_ID, tipoUsuarioDTO));
        verify(tipoUsuarioGateway, never()).atualizar(anyLong(), any(TipoUsuario.class));
    }

    @Test
    void deveValidarParametrosDaException() {
        when(tipoUsuarioGateway.buscarPorId(TIPO_USUARIO_ID)).thenReturn(Optional.empty());
        try {
            useCase.executar(TIPO_USUARIO_ID, tipoUsuarioDTO);
        } catch (TipoUsuarioNaoEncontradoException ex) {
            Assertions.assertThat(ex.getCode()).isEqualTo(TIPO_USUARIO_NAO_ENCONTRADO_CODE);
            Assertions.assertThat(ex.getMessage()).isEqualTo(TIPO_USUARIO_NAO_ENCONTRADO_MSG);
            Assertions.assertThat(ex.getHttpStatus()).isEqualTo(HTTP_STATUS_NOT_FOUND);
        }
    }
}
