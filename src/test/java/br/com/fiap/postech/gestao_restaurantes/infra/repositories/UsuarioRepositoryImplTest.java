package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.EnderecoEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.UsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.UsuarioJPARepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UsuarioRepositoryImplTest {
	@Mock
	private UsuarioJPARepository usuarioRepository;
	@Mock
	private UsuarioEntity usuarioEntity;
	@Mock
	private UsuarioDTO usuarioDTO; 
	@Mock
	NovoUsuarioDTO novoUsuarioDTO;
	private UsuarioRepositoryImpl usuarioRepositoryImpl;
	private AutoCloseable mock;
	
	private static final Long ID = 1L;
	
	@BeforeEach
	void setUp() {
		mock = MockitoAnnotations.openMocks(this);
		usuarioRepositoryImpl = new UsuarioRepositoryImpl(usuarioRepository);
	}
	
    @AfterEach
    void tearDown() throws Exception {
    	mock.close();
    }

	@Test
	void deveBuscarUsuarioPorIdComSucesso() {
		preencheUsuarioEntity();
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
		UsuarioDTO usuarioDTO = usuarioRepositoryImpl.buscarPorId(ID);
		assertThat(usuarioDTO).isNotNull();
	}

	@Test
	void deveLancarExceptionQuandoUsuarioNaoEncontrado() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
		UsuarioNaoEncontradoException exception = assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioRepositoryImpl.buscarPorId(ID));
		
		assertThat(exception.getCode()).isEqualTo("usuario.naoEncontrado");
		assertThat(exception.getMessage()).isEqualTo("Usuário não encontrado!");
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

	}

	@Test
	void deveAtualizarSenhaComSucesso() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
		when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);
		usuarioRepositoryImpl.atualizarSenha(ID, "novaSenha");
		verify(usuarioRepository).save(usuarioEntity);
	}

	@Test
	void deveLancarExceptionAoAtualizarSenhaErroRepositorio() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
		when(usuarioRepository.save(any(UsuarioEntity.class))).thenThrow(new RuntimeException());
		assertThrows(ErroAoAcessarRepositorioException.class,
				() -> usuarioRepositoryImpl.atualizarSenha(ID, "novaSenha"));
	}

	@Test
	void deveAtualizarUsuarioComSucesso() {
		preencheUsuarioDTO();
		preencheUsuarioEntity();
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
		when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);
		usuarioRepositoryImpl.atualizar(ID, usuarioDTO);
		verify(usuarioRepository).save(any(UsuarioEntity.class));
	}

	@Test
	void deveLancarExceptionAoAtualizarUsuarioErroRepositorio() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
		when(usuarioRepository.save(any(UsuarioEntity.class))).thenThrow(new RuntimeException());
		assertThrows(ErroAoAcessarRepositorioException.class, () -> usuarioRepositoryImpl.atualizar(ID, usuarioDTO));
	}

	@Test
	void deveDeletarUsuarioComSucesso() {
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
		doNothing().when(usuarioRepository).deleteById(anyLong());
		usuarioRepositoryImpl.deletar(ID);
		verify(usuarioRepository).deleteById(ID);
	}

	@Test
	void deveBuscarPorLoginComSucesso() {
		preencheUsuarioEntity();
		when(usuarioRepository.findByLogin(any())).thenReturn(Optional.of(usuarioEntity));
		UsuarioDTO usuarioDTO = usuarioRepositoryImpl.buscarPorLogin("login");
		assertThat(usuarioDTO).isNotNull();
	}

	@Test
	void deveRetornarNullQuandoBuscarPorLoginNaoEncontrado() {
		when(usuarioRepository.findByLogin(any())).thenReturn(Optional.empty());
		UsuarioDTO usuarioDTO = usuarioRepositoryImpl.buscarPorLogin("login");
		assertThat(usuarioDTO).isNull();
	}

	@Test
	void deveCriarUsuarioComSucesso() {
		getNovoUsuarioDTO();
		when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);
		when(usuarioEntity.getId()).thenReturn(ID);
		Long id = usuarioRepositoryImpl.criar(novoUsuarioDTO);
		assertThat(id).isEqualTo(ID);
	}

	@Test
	void deveLancarExceptionAoCriarUsuarioErroRepositorio() {
		getNovoUsuarioDTO();
		when(usuarioRepository.save(any(UsuarioEntity.class))).thenThrow(new RuntimeException());
		ErroAoAcessarRepositorioException exception = assertThrows(ErroAoAcessarRepositorioException.class,
				() -> usuarioRepositoryImpl.criar(novoUsuarioDTO));

		assertThat(exception.getCode()).isEqualTo("usuario.erroAcessarRepositorio");
		assertThat(exception.getMessage()).isEqualTo("Erro ao acessar repositório de dados.");
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	private void getNovoUsuarioDTO() {
		EnderecoDTO enderecoDTO = mock(EnderecoDTO.class);
		TipoUsuarioDTO tipoUsuarioDTO = mock(TipoUsuarioDTO.class);
		when(novoUsuarioDTO.endereco()).thenReturn(enderecoDTO);
		when(novoUsuarioDTO.tipoUsuario()).thenReturn(tipoUsuarioDTO);
	}
	
	private void preencheUsuarioDTO() {
		EnderecoDTO enderecoDTO = mock(EnderecoDTO.class);
		TipoUsuarioDTO tipoUsuarioDTO = mock(TipoUsuarioDTO.class);
		when(usuarioDTO.endereco()).thenReturn(enderecoDTO);
		when(usuarioDTO.tipoUsuario()).thenReturn(tipoUsuarioDTO);
	}
	
	private void preencheUsuarioEntity() {
		TipoUsuarioEntity tipoUsuario = mock(TipoUsuarioEntity.class);
		EnderecoEntity endereco = mock(EnderecoEntity.class);
		when(usuarioEntity.getEndereco()).thenReturn(endereco);
		when(usuarioEntity.getTipoUsuario()).thenReturn(tipoUsuario);
	}

}
