package br.com.fiap.postech.gestao_restaurantes.core.gateway;

import br.com.fiap.postech.gestao_restaurantes.core.dto.*;
import br.com.fiap.postech.gestao_restaurantes.core.entities.*;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IItemCardapioDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ItemCardapioGatewayTest {

    @Mock
    private IItemCardapioDataSource datasource;

    private ItemCardapioGateway itemCardapioGateway;

    private AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        itemCardapioGateway = ItemCardapioGateway.create(datasource);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveCriarItemCardapioComSucesso() {
        ItemCardapio itemCardapio = getItemCardapioInstance();
        when(datasource.criar(any(NovoItemCardapioDTO.class))).thenReturn(1L);

        Long id = itemCardapioGateway.criar(itemCardapio);

        assertThat(id).isEqualTo(1L);
    }

    @Test
    void deveDeletarItemCardapioComSucesso() {
        doNothing().when(datasource).deletar(anyLong());

        itemCardapioGateway.deletar(1L);

        verify(datasource).deletar(anyLong());
    }

    @Test
    void deveAtualizarItemCardapioComSucesso() {
        ItemCardapio itemCardapio = getItemCardapioInstance();
        doNothing().when(datasource).atualizar(anyLong(), any(ItemCardapioDTO.class));

        itemCardapioGateway.atualizar(1L, itemCardapio);

        verify(datasource).atualizar(anyLong(), any(ItemCardapioDTO.class));
    }

    @Test
    void deveBuscarItemCardapioPorIdComSucesso() {
        ItemCardapioDTO itemCardapioDTO = getItemCardapioDTO();
        when(datasource.buscarPorId(anyLong())).thenReturn(Optional.of(itemCardapioDTO));

        Optional<ItemCardapio> itemCardapio = itemCardapioGateway.buscarPorId(1L);

        assertThat(itemCardapio).isPresent();
    }

    @Test
    void deveRetornarEmptyQuandoBuscarItemCardapioPorIdNaoEncontrado() {
        when(datasource.buscarPorId(anyLong())).thenReturn(Optional.empty());

        Optional<ItemCardapio> itemCardapio = itemCardapioGateway.buscarPorId(1L);

        assertThat(itemCardapio).isEmpty();
    }

    @Test
    void deveBuscarItensPorIdRestauranteComSucesso() {
        List<ItemCardapioDTO> itemCardapioDTOList = List.of(getItemCardapioDTO());
        when(datasource.buscarPorIdRestaurante(anyLong())).thenReturn(Optional.of(itemCardapioDTOList));

        Optional<List<ItemCardapio>> itens = itemCardapioGateway.buscarPorIdRestaurante(1L);

        assertThat(itens).isPresent();
        assertThat(itens.get()).hasSize(1);
    }

    @Test
    void deveRetornarEmptyQuandoBuscarItensPorIdRestauranteNaoEncontrado() {
        when(datasource.buscarPorIdRestaurante(anyLong())).thenReturn(Optional.empty());

        Optional<List<ItemCardapio>> itens = itemCardapioGateway.buscarPorIdRestaurante(1L);

        assertThat(itens).isEmpty();
    }

    private ItemCardapioDTO getItemCardapioDTO() {
        return new ItemCardapioDTO(
                1L,
                "Item Teste",
                "Descrição Teste",
                BigDecimal.valueOf(10.99),
                true,
                "https://example.com/item.jpg",
                getRestauranteDTO()
        );
    }

    private ItemCardapio getItemCardapioInstance() {
        return ItemCardapio.create(
                1L,
                "Item Teste",
                "Descrição Teste",
                BigDecimal.valueOf(10.99),
                true,
                "https://example.com/item.jpg",
                getRestauranteInstance()
        );
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