package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CriarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;

    private CriarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static CriarItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway) {
        return new CriarItemCardapioUseCase(itemCardapioGateway);
    }

    public Long executar(NovoItemCardapioDTO novoItemCardapioDTO) {
        ItemCardapio entity = ItemCardapio.create(novoItemCardapioDTO.nome(), novoItemCardapioDTO.descricao(), novoItemCardapioDTO.preco(), novoItemCardapioDTO.disponivelApenasNoRestaurante(), novoItemCardapioDTO.foto());

        return itemCardapioGateway.criar(entity);
    }
}