package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardaptio.ItemCardapioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;

import java.util.Optional;

public class DeletarItemCardapioUsecase {

    private final IItemCardapioGateway itemCardapioGateway;

    private DeletarItemCardapioUsecase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static DeletarItemCardapioUsecase create(final IItemCardapioGateway itemCardapioGateway) {
        return new DeletarItemCardapioUsecase(itemCardapioGateway);
    }

    public void executar(Long id) {
        Optional<ItemCardapio> itemCardapio = itemCardapioGateway.buscarPorId(id);

        if (itemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException();
        }

        this.itemCardapioGateway.deletar(id);
    }
}