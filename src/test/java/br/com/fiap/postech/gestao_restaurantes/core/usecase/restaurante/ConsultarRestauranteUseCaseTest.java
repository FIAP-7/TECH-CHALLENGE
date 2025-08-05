package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ConsultarRestauranteUseCaseTest {

    @Mock
    private IRestauranteGateway restauranteGateway;

    @InjectMocks
    private ConsultarRestauranteUseCase useCase;

    @Test
    void deveConsultarRestaurantePorId() {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante XPTO");
        restaurante.setTipoCozinha("Italiana");
        restaurante.setHorarioFuncionamento("10h-22h");
        restaurante.setUsuario(new Usuario());
        restaurante.setEndereco(new Endereco());

        when(restauranteGateway.buscarPorId(1L)).thenReturn(restaurante);

        Restaurante resultado = useCase.executar(1L);

        assertNotNull(resultado);
        assertEquals("Restaurante XPTO", resultado.getNome());
        verify(restauranteGateway).buscarPorId(1L);
    }
}
