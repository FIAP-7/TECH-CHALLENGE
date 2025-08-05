package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.dto.AtualizarItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;

import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AtualizarItemCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    @Mock
    private IRestauranteGateway restauranteGateway;

    @InjectMocks
    private AtualizarItemCardapioUseCase useCase;

    @Test
    void deveAtualizarItemCardapioComSucesso() {

        AtualizarItemCardapioDTO dto = new AtualizarItemCardapioDTO(
                "Pizza Calabresa",
                "Pizza com calabresa",
                new BigDecimal("50.00"),
                true,
                "https://exemplo.com/imagem.jpg",
                1L
        );

        Restaurante restaurante = new Restaurante();
        ItemCardapio item = ItemCardapio.create(
                1L,
                dto.nome(),
                dto.descricao(),
                dto.preco(),
                dto.disponivelApenasNoRestaurante(),
                dto.foto(),
                restaurante
        );

        when(itemCardapioGateway.buscarPorId(1L)).thenReturn(Optional.of(item));
        when(restauranteGateway.buscarPorId(1L)).thenReturn(restaurante);
        doNothing().when(itemCardapioGateway).atualizar(eq(1L), any(ItemCardapio.class));

        useCase.executar(1L, dto);

        verify(itemCardapioGateway).atualizar(eq(1L), any(ItemCardapio.class));
    }


}
