package br.com.fiap.postech.gestao_restaurantes.core.gateway;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.ITipoUsuarioDataSource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TipoUsuarioGatewayTest {
    @Mock
    private ITipoUsuarioDataSource datasource;
    private TipoUsuarioGateway tipoUsuarioGateway;
    private AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        tipoUsuarioGateway = TipoUsuarioGateway.create(datasource);
    }
    
    @AfterEach
    void tearDown() throws Exception {
    	mock.close();
    }

    @Test
    void deveCriarTipoUsuarioComSucesso() {
        TipoUsuario tipoUsuario = mock(TipoUsuario.class);
        when(datasource.criar(any(NovoTipoUsuarioDTO.class))).thenReturn(1L);
        Long id = tipoUsuarioGateway.criar(tipoUsuario);
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void deveDeletarTipoUsuarioComSucesso() {
        TipoUsuarioDTO dto = mock(TipoUsuarioDTO.class);
        when(datasource.buscarPorId(anyLong())).thenReturn(Optional.of(dto));
        doNothing().when(datasource).deletar(anyLong());
        tipoUsuarioGateway.deletar(1L);
        verify(datasource).deletar(1L);
    }

    @Test
    void deveLancarExceptionQuandoDeletarTipoUsuarioNaoEncontrado() {
        when(datasource.buscarPorId(anyLong())).thenReturn(Optional.empty());
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> tipoUsuarioGateway.deletar(1L));
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        TipoUsuarioDTO dto = mock(TipoUsuarioDTO.class);
        when(datasource.buscarPorId(anyLong())).thenReturn(Optional.of(dto));
        Optional<TipoUsuario> resultado = tipoUsuarioGateway.buscarPorId(1L);
        assertThat(resultado).isPresent();
    }

    @Test
    void deveRetornarEmptyQuandoBuscarPorIdNaoEncontrado() {
        when(datasource.buscarPorId(anyLong())).thenReturn(Optional.empty());
        Optional<TipoUsuario> resultado = tipoUsuarioGateway.buscarPorId(1L);
        assertThat(resultado).isEmpty();
    }

    @Test
    void deveAtualizarTipoUsuarioComSucesso() {
        TipoUsuarioDTO dto = mock(TipoUsuarioDTO.class);
        TipoUsuario tipoUsuario = mock(TipoUsuario.class);
        when(datasource.buscarPorId(anyLong())).thenReturn(Optional.of(dto));
        doNothing().when(datasource).atualizar(anyLong(), any(TipoUsuarioDTO.class));
        tipoUsuarioGateway.atualizar(1L, tipoUsuario);
        verify(datasource).atualizar(anyLong(), any(TipoUsuarioDTO.class));
    }

    @Test
    void deveBuscarPorNomeComSucesso() {
        TipoUsuarioDTO dto = mock(TipoUsuarioDTO.class);
        when(datasource.buscarPorNome(any())).thenReturn(Optional.of(dto));
        Optional<TipoUsuario> resultado = tipoUsuarioGateway.buscarPorNome("ADMIN");
        assertThat(resultado).isPresent();
    }

    @Test
    void deveRetornarEmptyQuandoBuscarPorNomeNaoEncontrado() {
        when(datasource.buscarPorNome(any())).thenReturn(Optional.empty());
        Optional<TipoUsuario> resultado = tipoUsuarioGateway.buscarPorNome("ADMIN");
        assertThat(resultado).isEmpty();
    }
}
