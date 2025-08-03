package br.com.fiap.postech.gestao_restaurantes.core.controller;

import br.com.fiap.postech.gestao_restaurantes.core.dto.ItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.exception.itemCardapio.ItemCardapioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.ItemCardapioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.RestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IItemCardapioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IRestauranteDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.ItemCardapioPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio.AtualizarItemCardapioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio.ConsultarItemCardapioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio.CriarItemCardapioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio.DeletarItemCardapioUsecase;

public class ItemCardapioCoreController {

    private final IItemCardapioDataSource dataSource;
    private final IRestauranteDataSource restauranteDataSource;

    private ItemCardapioCoreController(IItemCardapioDataSource dataSource, IRestauranteDataSource restauranteDataSource) {
        this.dataSource = dataSource;
        this.restauranteDataSource = restauranteDataSource;
    }

    public static ItemCardapioCoreController create(IItemCardapioDataSource dataSource, IRestauranteDataSource restauranteDataSource) {
        return new ItemCardapioCoreController(dataSource, restauranteDataSource);
    }

    public Void incluir(NovoItemCardapioDTO novoItemCardapioDTO) {
        IItemCardapioGateway itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        IRestauranteGateway restauranteGateway = RestauranteGateway.create(this.restauranteDataSource);

        CriarItemCardapioUseCase criarItemCardapioUsecase = CriarItemCardapioUseCase.create(itemCardapioGateway, restauranteGateway);

        criarItemCardapioUsecase.executar(novoItemCardapioDTO);

        return null;
    }

    public ItemCardapioDTO buscarPorId(Long id) {
        IItemCardapioGateway itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        ConsultarItemCardapioUseCase consultarItemCardapioUseCase = ConsultarItemCardapioUseCase.create(itemCardapioGateway);

        try {
            ItemCardapio itemCardapio = consultarItemCardapioUseCase.executar(id);

            return ItemCardapioPresenter.toDTO(itemCardapio);
        } catch (ItemCardapioNaoEncontradoException e) {
            return null;
        }
    }

    public ItemCardapioDTO alterar(Long id, ItemCardapioDTO itemCardapioDTO) {
        IItemCardapioGateway itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        IRestauranteGateway restauranteGateway = RestauranteGateway.create(this.restauranteDataSource);

        AtualizarItemCardapioUseCase atualizarItemCardapioUseCase = AtualizarItemCardapioUseCase.create(itemCardapioGateway, restauranteGateway);

        atualizarItemCardapioUseCase.executar(id, itemCardapioDTO);

        return null;
    }

    public void excluir(Long id) {
        IItemCardapioGateway itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        DeletarItemCardapioUsecase deletarItemCardapioUsecase = DeletarItemCardapioUsecase.create(itemCardapioGateway);

        deletarItemCardapioUsecase.executar(id);
    }
}
