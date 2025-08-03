package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CriarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;
    private final IRestauranteGateway restauranteGateway;


    private CriarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public static CriarItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        return new CriarItemCardapioUseCase(itemCardapioGateway, restauranteGateway);
    }

    public Long executar(NovoItemCardapioDTO novoItemCardapioDTO) {
        Restaurante restaurante = restauranteGateway.buscarPorId(novoItemCardapioDTO.restaurante().id());

        ItemCardapio entity = ItemCardapio.create(novoItemCardapioDTO.nome(), novoItemCardapioDTO.descricao(), novoItemCardapioDTO.preco(), novoItemCardapioDTO.disponivelApenasNoRestaurante(), novoItemCardapioDTO.foto(), restaurante);

        return itemCardapioGateway.criar(entity);
    }
}