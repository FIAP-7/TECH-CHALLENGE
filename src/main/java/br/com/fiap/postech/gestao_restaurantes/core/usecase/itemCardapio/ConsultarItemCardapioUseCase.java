package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardaptio.ItemCardapioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;

import java.util.Optional;

public class ConsultarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private ConsultarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static ConsultarItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new ConsultarItemCardapioUseCase(itemCardapioGateway);
    }

    public ItemCardapio executar(Long id) {
        Optional<ItemCardapio> itemCardapio = itemCardapioGateway.buscarPorId(id);

        if (itemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException();
        }

        return itemCardapio.get();
    }
}
