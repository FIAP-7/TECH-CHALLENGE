package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarItemCardapioUseCaseTest {

    @Mock
    private IItemCardapioGateway itemCardapioGateway;

    @InjectMocks
    private DeletarItemCardapioUsecase useCase;

    @Test
    void deveDeletarItemCardapioPorId() {
        Long id = 1L;
        ItemCardapio item = new ItemCardapio();

        when(itemCardapioGateway.buscarPorId(id)).thenReturn(Optional.of(item));
        doNothing().when(itemCardapioGateway).deletar(id);

        useCase.executar(id);

        verify(itemCardapioGateway).buscarPorId(id);
        verify(itemCardapioGateway).deletar(id);
    }
}
