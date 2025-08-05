package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class CriarItemCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    @Mock
    private IRestauranteGateway restauranteGateway;

    @Test
    void deveCriarItemCardapioComSucesso() {
        NovoItemCardapioDTO dto = new NovoItemCardapioDTO(
                "Pizza Margherita",
                "Pizza clássica",
                BigDecimal.valueOf(45.0),
                true,
                "https://example.com/foto.jpg",
                1L
        );

        Endereco enderecoMock = new Endereco();
        enderecoMock.setLogradouro("Rua das Pizzas");
        enderecoMock.setNumero("123");
        enderecoMock.setCidade("São Paulo");
        enderecoMock.setEstado("SP");
        enderecoMock.setCep("01234-567");

        Restaurante restauranteMock = new Restaurante();
        restauranteMock.setId(1L);
        restauranteMock.setNome("Restaurante XPTO");
        restauranteMock.setEndereco(enderecoMock);

        when(restauranteGateway.buscarPorId(1L)).thenReturn(restauranteMock);
        when(itemCardapioGateway.criar(any(ItemCardapio.class))).thenReturn(1L);

        CriarItemCardapioUseCase useCase = CriarItemCardapioUseCase.create(itemCardapioGateway, restauranteGateway);

        Long resultado = useCase.executar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado);
        verify(restauranteGateway).buscarPorId(1L);
        verify(itemCardapioGateway).criar(any(ItemCardapio.class));
    }
}
