package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class DeletarRestauranteUseCaseTest {

    @Mock
    private RestauranteGateway restauranteGateway;

    @InjectMocks
    private DeletarRestauranteUseCase useCase;

    @Test
    void deveDeletarRestaurantePorId() {
        Long id = 1L;
        Restaurante restaurante = new Restaurante();

        when(restauranteGateway.buscarPorId(id)).thenReturn(restaurante);
        doNothing().when(restauranteGateway).deletar(id);

        useCase.executar(id);

        verify(restauranteGateway).buscarPorId(id);
        verify(restauranteGateway).deletar(id);
    }
}
