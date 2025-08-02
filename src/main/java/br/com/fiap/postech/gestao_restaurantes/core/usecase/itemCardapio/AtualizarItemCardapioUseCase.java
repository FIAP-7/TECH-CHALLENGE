package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.dto.ItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardaptio.ItemCardapioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;

import java.util.Optional;

public class AtualizarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private AtualizarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static AtualizarItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new AtualizarItemCardapioUseCase(itemCardapioGateway);
    }

    public void executar(Long id, ItemCardapioDTO itemCardapioDTO) {
        Optional<ItemCardapio> itemCardapio = itemCardapioGateway.buscarPorId(id);

        if (itemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException();
        }

        ItemCardapio entity = ItemCardapio.create(id, itemCardapioDTO.nome(), itemCardapioDTO.descricao(), itemCardapioDTO.preco(), itemCardapioDTO.disponivelApenasNoRestaurante(), itemCardapioDTO.foto());

        this.itemCardapioGateway.atualizar(id, entity);
    }
}
