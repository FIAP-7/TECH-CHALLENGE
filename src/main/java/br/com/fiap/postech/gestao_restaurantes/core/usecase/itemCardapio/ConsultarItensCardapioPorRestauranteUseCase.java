package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.ItemCardapioPorRestauranteNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;

import java.util.List;
import java.util.Optional;

public class ConsultarItensCardapioPorRestauranteUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private ConsultarItensCardapioPorRestauranteUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static ConsultarItensCardapioPorRestauranteUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new ConsultarItensCardapioPorRestauranteUseCase(itemCardapioGateway);
    }

    public List<ItemCardapio> executar(Long idRestaurante) {
        Optional<List<ItemCardapio>> itemCardapioList = itemCardapioGateway.buscarPorIdRestaurante(idRestaurante);

        if (itemCardapioList.isEmpty() || itemCardapioList.get().isEmpty()) {
            throw new ItemCardapioPorRestauranteNaoEncontradoException();
        }

        return itemCardapioList.get();
    }
}
