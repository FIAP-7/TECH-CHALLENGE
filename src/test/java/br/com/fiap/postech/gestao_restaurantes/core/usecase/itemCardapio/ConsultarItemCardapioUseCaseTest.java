package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ConsultarItemCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    @InjectMocks
    private ConsultarItemCardapioUseCase useCase;

    @Test
    void deveConsultarItemCardapioPorId() {
        ItemCardapio item = new ItemCardapio();
        item.setId(1L);
        item.setNome("Pizza Margherita");
        item.setDescricao("Pizza clássica");
        item.setPreco(BigDecimal.valueOf(45.0));
        item.setDisponivelApenasNoRestaurante(true);

        when(itemCardapioGateway.buscarPorId(1L)).thenReturn(java.util.Optional.of(item));

        ItemCardapio resultado = useCase.executar(1L);

        assertNotNull(resultado);
        assertEquals("Pizza Margherita", resultado.getNome());
        verify(itemCardapioGateway).buscarPorId(1L);
    }
}
