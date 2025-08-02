package br.com.fiap.postech.gestao_restaurantes.core.gateway;


import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IItemCardapioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.ItemCardapioPresenter;

import java.util.Optional;

public class ItemCardapioGateway implements IItemCardapioGateway {

    private final IItemCardapioDataSource datasource;

    private ItemCardapioGateway(IItemCardapioDataSource datasource) {
        this.datasource = datasource;
    }

    public static ItemCardapioGateway create(IItemCardapioDataSource datasource) {
        return new ItemCardapioGateway(datasource);
    }

    @Override
    public Long criar(ItemCardapio itemCardapio) {
        NovoItemCardapioDTO novoItemCardapio = ItemCardapioPresenter.toNovoTipoDTO(itemCardapio);

        return this.datasource.criar(novoItemCardapio);
    }

    @Override
    public void deletar(Long id) {
        this.datasource.deletar(id);
    }

    @Override
    public void atualizar(Long id, ItemCardapio tipoUsuario) {
        this.datasource.atualizar(id, ItemCardapioPresenter.toDTO(tipoUsuario));
    }

    @Override
    public Optional<ItemCardapio> buscarPorId(Long id) {
        return this.datasource.buscarPorId(id).map(ItemCardapioPresenter::toEntity);
    }

    @Override
    public Optional<ItemCardapio> buscarPorRestaurante(Long idRestaurante) {
        return this.datasource.buscarPorRestaurante(idRestaurante).map(ItemCardapioPresenter::toEntity);
    }
}
