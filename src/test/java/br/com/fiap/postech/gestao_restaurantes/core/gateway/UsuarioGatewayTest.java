package br.com.fiap.postech.gestao_restaurantes.core.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EnderecoNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IUsuarioDataSource;

class UsuarioGatewayTest {
    @Mock
    private IUsuarioDataSource dataSource;
    @Mock
    private Usuario usuario;
    @Mock
    private UsuarioDTO usuarioDTO;
    private UsuarioGateway usuarioGateway;
    private AutoCloseable mock;
    private static final String LOGIN = "login";
    private static final String SENHA = "Senha@1234";

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        usuarioGateway = (UsuarioGateway) UsuarioGateway.create(dataSource);
    }

    @AfterEach
    void tearDown() throws Exception {
    	mock.close();
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
    	UsuarioDTO usuarioDto = getUsuarioDTO();
        when(dataSource.buscarPorId(anyLong())).thenReturn(usuarioDto);
        Usuario usuario = usuarioGateway.buscarPorId(1L);
        assertThat(usuario).isNotNull();
    }

    @Test
    void deveRetornarNullQuandoUsuarioNaoEncontrado() {
        when(dataSource.buscarPorId(anyLong())).thenReturn(null);
        Usuario usuario = usuarioGateway.buscarPorId(1L);
        assertThat(usuario).isNull();
    }

    @Test
    void deveBuscarPorLoginComSucesso() {
    	UsuarioDTO usuarioDto = getUsuarioDTO();
        when(dataSource.buscarPorLogin(any())).thenReturn(usuarioDto);
        Optional<Usuario> usuario = usuarioGateway.buscarPorLogin(LOGIN);
        assertThat(usuario).isPresent();
    }

    @Test
    void deveRetornarEmptyQuandoBuscarPorLoginNaoEncontrado() {
        when(dataSource.buscarPorLogin(any())).thenReturn(null);
        Optional<Usuario> usuario = usuarioGateway.buscarPorLogin("login");
        assertThat(usuario).isEmpty();
    }

    @Test
    void deveCriarUsuarioComSucesso() {
    	preencheUsuario();
        when(dataSource.criar(any())).thenReturn(1L);
        Long id = usuarioGateway.criar(usuario);
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
    	preencheUsuarioDTO();
    	preencheUsuario();
        when(dataSource.buscarPorId(anyLong())).thenReturn(usuarioDTO);
        doNothing().when(dataSource).atualizar(anyLong(), any());
        usuarioGateway.atualizar(1L, usuario);
        verify(dataSource).atualizar(anyLong(), any());
    }

    @Test
    void deveAtualizarSenhaComSucesso() {
    	UsuarioDTO usuarioDto = getUsuarioDTO();
        when(dataSource.buscarPorId(anyLong())).thenReturn(usuarioDto);
        doNothing().when(dataSource).atualizarSenha(anyLong(), any());
        usuarioGateway.atualizarSenha(1L, SENHA);
        verify(dataSource).atualizarSenha(anyLong(), any());
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        when(dataSource.buscarPorId(anyLong())).thenReturn(usuarioDTO);
        doNothing().when(dataSource).deletar(anyLong());
        usuarioGateway.deletar(1L);
        verify(dataSource).deletar(anyLong());
    }

    @Test
    void deveLancarTipoUsuarioNaoEncontradoExceptionQuandoTipoUsuarioNull() {
        when(dataSource.buscarPorId(anyLong())).thenReturn(usuarioDTO);
        when(usuarioDTO.tipoUsuario()).thenReturn(null);
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> usuarioGateway.buscarPorId(1L));
    }

    @Test
    void deveLancarEnderecoNaoEncontradoExceptionQuandoEnderecoNull() {
        when(dataSource.buscarPorId(anyLong())).thenReturn(usuarioDTO);
        when(usuarioDTO.tipoUsuario()).thenReturn(mock(TipoUsuarioDTO.class));
        when(usuarioDTO.endereco()).thenReturn(null);
        assertThrows(EnderecoNaoEncontradoException.class, () -> usuarioGateway.buscarPorId(1L));
    }
    
	private void preencheUsuario() {
		Endereco endereco = mock(Endereco.class);
		TipoUsuario tipoUsuario = mock(TipoUsuario.class);
		when(usuario.getEndereco()).thenReturn(endereco);
		when(usuario.getTipoUsuario()).thenReturn(tipoUsuario);
	}
	
	private void preencheUsuarioDTO() {
		EnderecoDTO enderecoDTO = mock(EnderecoDTO.class);
		TipoUsuarioDTO tipoUsuarioDTO = mock(TipoUsuarioDTO.class);
		when(usuarioDTO.endereco()).thenReturn(enderecoDTO);
		when(usuarioDTO.tipoUsuario()).thenReturn(tipoUsuarioDTO);
	}
	
	private UsuarioDTO getUsuarioDTO() {
		EnderecoDTO enderecoDTO = new EnderecoDTO(2L, "Rua", "157", "n/a", "Centro","SV", "SP", "11504-450");
		TipoUsuarioDTO tipoUsuarioDTO = mock(TipoUsuarioDTO.class);
		UsuarioDTO usuarioDTO = new UsuarioDTO(3L, "451.154.548-81", "Tester", "teste@teste.com", LOGIN, SENHA, null, tipoUsuarioDTO, enderecoDTO);
		return usuarioDTO;
	}

}
