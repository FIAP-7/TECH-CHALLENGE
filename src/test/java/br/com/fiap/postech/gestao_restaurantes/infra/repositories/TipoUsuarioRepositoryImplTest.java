package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.springframework.http.HttpStatus;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioUtilizadoException;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.TipoUsuarioJPARepository;

public class TipoUsuarioRepositoryImplTest {
	
    @Mock
    private TipoUsuarioJPARepository tipoUsuarioRepository;
    
    @Mock
    private NovoTipoUsuarioDTO novoTipoUsuarioDTO;
    
    @Mock
    TipoUsuarioEntity entity;
    
    @Mock
    private TipoUsuarioDTO tipoUsuarioDTO;
    private TipoUsuarioRepositoryImpl tipoUsuarioRepositoryImpl;
    private AutoCloseable mock;
    
    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        tipoUsuarioRepositoryImpl = new TipoUsuarioRepositoryImpl(tipoUsuarioRepository);
    }
    
    @AfterEach
    void tearDown() throws Exception {
    	mock.close();
    }
    
    @Test
    void deveCriarTipoUsuarioComSucesso() {
        TipoUsuarioEntity entity = mock(TipoUsuarioEntity.class);
        when(tipoUsuarioRepository.save(any(TipoUsuarioEntity.class))).thenReturn(entity);
        when(entity.getId()).thenReturn(ID);
        Long id = tipoUsuarioRepositoryImpl.criar(novoTipoUsuarioDTO);
        assertThat(id).isEqualTo(ID);
    }

    @Test
    void deveLancarExceptionAoCriarTipoUsuario() {
        when(tipoUsuarioRepository.save(any(TipoUsuarioEntity.class))).thenThrow(new RuntimeException());
        assertThrows(ErroAoAcessarRepositorioException.class, () -> tipoUsuarioRepositoryImpl.criar(novoTipoUsuarioDTO));
    }

    @Test
    void deveDeletarTipoUsuarioComSucesso() {
        TipoUsuarioEntity entity = mock(TipoUsuarioEntity.class);
        when(tipoUsuarioRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(tipoUsuarioRepository.isTipoUsuarioInUse(anyLong())).thenReturn(false);
        doNothing().when(tipoUsuarioRepository).deleteById(anyLong());
        tipoUsuarioRepositoryImpl.deletar(ID);
        verify(tipoUsuarioRepository).deleteById(ID);
    }

    @Test
    void deveLancarExceptionQuandoDeletarTipoUsuarioNaoEncontrado() {
        when(tipoUsuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> tipoUsuarioRepositoryImpl.deletar(ID));
    }

    @Test
    void deveLancarExceptionQuandoTipoUsuarioUtilizadoAoDeletar() {
        when(tipoUsuarioRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(tipoUsuarioRepository.isTipoUsuarioInUse(anyLong())).thenReturn(true);
        TipoUsuarioUtilizadoException exception = assertThrows(TipoUsuarioUtilizadoException.class, () -> tipoUsuarioRepositoryImpl.deletar(ID));
        
		assertThat(exception.getCode()).isEqualTo("tipoUsuario.tipoUsuarioUtilizado");
		assertThat(exception.getMessage()).isEqualTo("Este tipo de usuário está vinculado a um ou mais usuários!");
		assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deveAtualizarTipoUsuarioComSucesso() {
        when(tipoUsuarioRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(tipoUsuarioRepository.save(any(TipoUsuarioEntity.class))).thenReturn(entity);
        tipoUsuarioRepositoryImpl.atualizar(ID, tipoUsuarioDTO);
        verify(tipoUsuarioRepository).save(any(TipoUsuarioEntity.class));
    }

    @Test
    void deveLancarExceptionAoAtualizarTipoUsuarioErroRepositorio() {
        when(tipoUsuarioRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(tipoUsuarioRepository.save(any(TipoUsuarioEntity.class))).thenThrow(new RuntimeException());
        assertThrows(ErroAoAcessarRepositorioException.class, () -> tipoUsuarioRepositoryImpl.atualizar(ID, tipoUsuarioDTO));
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        when(tipoUsuarioRepository.findById(anyLong())).thenReturn(Optional.of(entity));
        Optional<TipoUsuarioDTO> resultado = tipoUsuarioRepositoryImpl.buscarPorId(ID);
        assertThat(resultado).isPresent();
    }

    @Test
    void deveLancarExceptionQuandoBuscarPorIdNaoEncontrado() {
        when(tipoUsuarioRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(TipoUsuarioNaoEncontradoException.class, () -> tipoUsuarioRepositoryImpl.buscarPorId(ID));
    }

    @Test
    void deveBuscarPorNomeComSucesso() {
        when(tipoUsuarioRepository.findByNome(anyString())).thenReturn(Optional.of(entity));
        Optional<TipoUsuarioDTO> resultado = tipoUsuarioRepositoryImpl.buscarPorNome("ADMIN");
        assertThat(resultado).isPresent();
    }

    @Test
    void deveRetornarEmptyQuandoBuscarPorNomeNaoEncontrado() {
        when(tipoUsuarioRepository.findByNome(anyString())).thenReturn(Optional.empty());
        Optional<TipoUsuarioDTO> resultado = tipoUsuarioRepositoryImpl.buscarPorNome("ADMIN");
        assertThat(resultado).isEmpty();
    }
}
