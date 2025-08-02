package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
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

    public void executar(Long id, NovoItemCardapioDTO novoItemCardapioDTO) {
        Optional<ItemCardapio> itemCardapio = itemCardapioGateway.buscarPorId(id);

        if (itemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException();
        }

        ItemCardapio entity = ItemCardapio.create(id, novoItemCardapioDTO.nome(), novoItemCardapioDTO.descricao(), novoItemCardapioDTO.preco(), novoItemCardapioDTO.disponivelApenasNoRestaurante(), novoItemCardapioDTO.foto());

        this.itemCardapioGateway.atualizar(id, entity);
    }
}
