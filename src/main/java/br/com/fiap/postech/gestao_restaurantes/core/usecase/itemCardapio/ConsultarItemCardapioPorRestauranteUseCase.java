package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;

import java.util.Optional;

public class ConsultarItemCardapioPorRestauranteUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private ConsultarItemCardapioPorRestauranteUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static ConsultarItemCardapioPorRestauranteUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new ConsultarItemCardapioPorRestauranteUseCase(itemCardapioGateway);
    }

    public ItemCardapio executar(Long idRestaurante) {
        Optional<ItemCardapio> itemCardapio = itemCardapioGateway.buscarPorRestaurante(idRestaurante);

        if (itemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException();
        }

        return itemCardapio.get();
    }
}
