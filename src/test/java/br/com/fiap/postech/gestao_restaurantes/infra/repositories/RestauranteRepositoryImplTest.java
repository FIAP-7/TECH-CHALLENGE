package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.EnderecoEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.RestauranteEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.UsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.RestauranteJPARepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RestauranteRepositoryImplTest {

    @Mock
    private RestauranteJPARepository restauranteRepository;

    private RestauranteRepositoryImpl restauranteRepositoryImpl;
    private AutoCloseable mock;

    private static final Long ID = 1L;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        restauranteRepositoryImpl = new RestauranteRepositoryImpl(restauranteRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveCriarRestauranteComSucesso() {
        when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(getRestauranteEntity());

        Long id = restauranteRepositoryImpl.criar(getNovoRestauranteDTO());
        
        assertThat(id).isEqualTo(ID);
    }

    @Test
    void deveDeletarRestauranteComSucesso() {
        when(restauranteRepository.findById(anyLong())).thenReturn(Optional.of(getRestauranteEntity()));
        doNothing().when(restauranteRepository).deleteById(anyLong());
        restauranteRepositoryImpl.deletar(ID);
        verify(restauranteRepository).deleteById(ID);
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {
        when(restauranteRepository.findById(anyLong())).thenReturn(Optional.of(getRestauranteEntity()));
        when(restauranteRepository.save(any(RestauranteEntity.class))).thenReturn(getRestauranteEntity());
        restauranteRepositoryImpl.atualizar(ID, getRestauranteDTO());
        verify(restauranteRepository).save(any(RestauranteEntity.class));
    }

    @Test
    void deveLancarExceptionAoAtualizarRestauranteErroRepositorio() {
        when(restauranteRepository.findById(anyLong())).thenReturn(Optional.of(getRestauranteEntity()));
        when(restauranteRepository.save(any(RestauranteEntity.class))).thenThrow(new RuntimeException());
        assertThrows(ErroAoAcessarRepositorioException.class, () -> restauranteRepositoryImpl.atualizar(ID, getRestauranteDTO()));
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        when(restauranteRepository.findById(anyLong())).thenReturn(Optional.of(getRestauranteEntity()));
        RestauranteDTO resultado = restauranteRepositoryImpl.buscarPorId(ID);
        assertNotNull(resultado);
    }

    @Test
    void deveLancarExceptionQuandoBuscarPorIdNaoEncontrado() {
        when(restauranteRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RestauranteNaoEncontradoException.class, () -> restauranteRepositoryImpl.buscarPorId(ID));
    }

    public EnderecoEntity getEnderecoEntity() {
        return EnderecoEntity.builder()
                .id(ID)
                .logradouro("Rua Exemplo")
                .numero("123")
                .complemento("Apto 101")
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .cep("12345-678")
                .build();
    }
    
    public UsuarioEntity getUsuarioEntity() {
        return UsuarioEntity.builder()
                .id(ID)
                .cpf("123.456.789-00")
                .nome("João Silva")
                .email("joao.silva@example.com")
                .login("joaosilva")
                .senha("senha123")
                .tipoUsuario(getTipoUsuarioEntity())
                .dataUltimaAlteracao(LocalDateTime.now())
                .endereco(getEnderecoEntity())
                .build();
    }

    public RestauranteEntity getRestauranteEntity() {
        return RestauranteEntity.builder()
                .id(ID)
                .nome("Restaurante Exemplo")
                .tipoCozinha("Italiana")
                .horarioFuncionamento("10:00 - 22:00")
                .usuario(getUsuarioEntity())
                .endereco(getEnderecoEntity())
                .build();
    }
    
    public TipoUsuarioEntity getTipoUsuarioEntity() {
        return TipoUsuarioEntity.builder()
                .id(ID)
                .nome("Administrador")
                .build();
    }
    
    public UsuarioRestauranteDTO getUsuarioRestauranteDTO() {
        return new UsuarioRestauranteDTO(1L, "João Silva");
    }
    
    public EnderecoDTO getEnderecoDTO() {
        return new EnderecoDTO(
                1L,
                "Rua Exemplo",
                "123",
                "Apto 101",
                "Centro",
                "São Paulo",
                "SP",
                "12345-678"
        );
    }
    
    public NovoRestauranteDTO getNovoRestauranteDTO() {
        return new NovoRestauranteDTO(
                "Restaurante Exemplo",
                "Italiana",
                "10:00 - 22:00",
                getUsuarioRestauranteDTO(),
                getEnderecoDTO()
        );
    }

    private RestauranteDTO getRestauranteDTO() {
        return new RestauranteDTO(
                1L,
                "Restaurante Teste",
                "Comida Brasileira",
                "08:00 - 22:00",
                getUsuarioRestauranteDTO(),
                getEnderecoDTO()
        );
    }
}