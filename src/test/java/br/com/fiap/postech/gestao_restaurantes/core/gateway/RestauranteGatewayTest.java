package br.com.fiap.postech.gestao_restaurantes.core.gateway;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EnderecoNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IRestauranteDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RestauranteGatewayTest {

    @Mock
    private IRestauranteDataSource dataSource;

    private RestauranteGateway restauranteGateway;

    private AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        restauranteGateway = (RestauranteGateway) RestauranteGateway.create(dataSource);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveBuscarRestaurantePorIdComSucesso() {
        RestauranteDTO restauranteDTO = getRestauranteDTO();
        when(dataSource.buscarPorId(anyLong())).thenReturn(restauranteDTO);

        Restaurante restaurante = restauranteGateway.buscarPorId(1L);

        assertThat(restaurante).isNotNull();
    }

    @Test
    void deveRetornarNullQuandoRestauranteNaoEncontrado() {
        when(dataSource.buscarPorId(anyLong())).thenReturn(null);
        Restaurante restaurante = restauranteGateway.buscarPorId(1L);
        assertThat(restaurante).isNull();
    }

    @Test
    void deveLancarUsuarioNaoEncontradoExceptionQuandoUsuarioNull() {
        RestauranteDTO restauranteDTO = mock(RestauranteDTO.class);
        when(dataSource.buscarPorId(anyLong())).thenReturn(restauranteDTO);
        when(restauranteDTO.usuario()).thenReturn(null);
        assertThrows(UsuarioNaoEncontradoException.class, () -> restauranteGateway.buscarPorId(1L));
    }

    @Test
    void deveLancarEnderecoNaoEncontradoExceptionQuandoEnderecoNull() {
        RestauranteDTO restauranteDTO = new RestauranteDTO(
                1L,
                "Restaurante Teste",
                "Comida Brasileira",
                "08:00 - 22:00",
                getUsuarioRestauranteDTO(),
                null
        );
        when(dataSource.buscarPorId(anyLong())).thenReturn(restauranteDTO);
        assertThrows(EnderecoNaoEncontradoException.class, () -> restauranteGateway.buscarPorId(1L));
    }

    @Test
    void deveBuscarPorNomeComSucesso() {
        RestauranteDTO restauranteDTO = getRestauranteDTO();
        when(dataSource.buscarPorNome(any())).thenReturn(Optional.of(restauranteDTO));
        Optional<Restaurante> restaurante = restauranteGateway.buscarPorNome("Restaurante Teste");
        assertThat(restaurante).isPresent();
    }

    @Test
    void deveRetornarEmptyQuandoBuscarPorNomeNaoEncontrado() {
        when(dataSource.buscarPorNome(any())).thenReturn(Optional.empty());
        Optional<Restaurante> restaurante = restauranteGateway.buscarPorNome("Restaurante Teste");
        assertThat(restaurante).isEmpty();
    }

    @Test
    void deveCriarRestauranteComSucesso() {
        Restaurante restaurante = getRestauranteInstance();
        when(dataSource.criar(any(NovoRestauranteDTO.class))).thenReturn(1L);
        Long id = restauranteGateway.criar(restaurante);
        assertThat(id).isEqualTo(1L);
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {
        Restaurante restaurante = getRestauranteInstance();
        RestauranteDTO restauranteDTO = getRestauranteDTO();
        when(dataSource.buscarPorId(anyLong())).thenReturn(restauranteDTO);
        doNothing().when(dataSource).atualizar(anyLong(), any(RestauranteDTO.class));
        restauranteGateway.atualizar(1L, restaurante);
        verify(dataSource).atualizar(anyLong(), any(RestauranteDTO.class));
    }

    @Test
    void deveDeletarRestauranteComSucesso() {
        RestauranteDTO restauranteDTO = mock(RestauranteDTO.class);
        when(dataSource.buscarPorId(anyLong())).thenReturn(restauranteDTO);
        doNothing().when(dataSource).deletar(anyLong());
        restauranteGateway.deletar(1L);
        verify(dataSource).deletar(anyLong());
    }

    private EnderecoDTO getEnderecoDTO() {
        return new EnderecoDTO(
                1L,
                "Rua Teste",
                "123",
                "Complemento Teste",
                "Bairro Teste",
                "Cidade Teste",
                "Estado Teste",
                "92500-000"
        );
    }

    private UsuarioRestauranteDTO getUsuarioRestauranteDTO() {
        return new UsuarioRestauranteDTO(
                1L,
                "João"
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

    private Restaurante getRestauranteInstance() {
        Usuario usuario = getUsuarioInstance();
        Endereco endereco = getEnderecoInstance();
        return Restaurante.create(
                1L,
                "Restaurante Teste",
                "Comida Brasileira",
                "08:00 - 22:00",
                usuario,
                endereco
        );
    }

    private Usuario getUsuarioInstance() {
        Endereco endereco = getEnderecoInstance();
        return Usuario.create(
                1L,
                "123.456.789-00",
                "João Silva",
                "joao.silva@email.com",
                "joaosilva",
                "Senha@123",
                LocalDateTime.now(),
                TipoUsuario.create("Administrador"),
                endereco
        );
    }

    private Endereco getEnderecoInstance() {
        return Endereco.create(
                1L,
                "Rua Teste",
                "123",
                "Complemento Teste",
                "Bairro Teste",
                "Cidade Teste",
                "Estado Teste",
                "92500-000"
        );
    }
}