package br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio;

import br.com.fiap.postech.gestao_restaurantes.core.dto.AtualizarItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;

import java.util.Optional;

public class AtualizarItemCardapioUseCase {

    private final IItemCardapioGateway itemCardapioGateway;
    private final IRestauranteGateway restauranteGateway;


    private AtualizarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public static AtualizarItemCardapioUseCase create(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        return new AtualizarItemCardapioUseCase(itemCardapioGateway, restauranteGateway);
    }

    public void executar(Long id, AtualizarItemCardapioDTO atualizarItemCardapioDTO) {
        Optional<ItemCardapio> itemCardapio = itemCardapioGateway.buscarPorId(id);
        if (itemCardapio.isEmpty()) {
            throw new ItemCardapioNaoEncontradoException();
        }
        Restaurante restaurante = restauranteGateway.buscarPorId(atualizarItemCardapioDTO.restauranteId());
        if (restaurante == null) {
            throw new RestauranteNaoEncontradoException();
        }
        ItemCardapio entity = ItemCardapio.create(id, atualizarItemCardapioDTO.nome(), atualizarItemCardapioDTO.descricao(), atualizarItemCardapioDTO.preco(), atualizarItemCardapioDTO.disponivelApenasNoRestaurante(), atualizarItemCardapioDTO.foto(), restaurante);

        this.itemCardapioGateway.atualizar(id, entity);
    }
}
