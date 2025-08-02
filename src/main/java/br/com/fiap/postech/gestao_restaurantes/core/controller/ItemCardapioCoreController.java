package br.com.fiap.postech.gestao_restaurantes.core.controller;

import br.com.fiap.postech.gestao_restaurantes.core.dto.ItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EnderecoNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.ItemCardapioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IItemCardapioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IItemCardapioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.ItemCardapioPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio.AtualizarItemCardapioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio.ConsultarItemCardapioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio.CriarItemCardapioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.itemCardapio.DeletarItemCardapioUsecase;

public class ItemCardapioCoreController {

    private final IItemCardapioDataSource dataSource;

    private ItemCardapioCoreController(IItemCardapioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ItemCardapioCoreController create(IItemCardapioDataSource dataSource) {
        return new ItemCardapioCoreController(dataSource
        );
    }

    public ItemCardapioDTO incluir(NovoItemCardapioDTO novoItemCardapioDTO) {
        IItemCardapioGateway itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);

        CriarItemCardapioUseCase criarItemCardapioUsecase = CriarItemCardapioUseCase.create(itemCardapioGateway);

        Long idUsuario = criarItemCardapioUsecase.executar(novoItemCardapioDTO);

        return null;
    }

    public ItemCardapioDTO buscarPorId(Long id) {
        IItemCardapioGateway itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        ConsultarItemCardapioUseCase consultarItemCardapioUseCase = ConsultarItemCardapioUseCase.create(itemCardapioGateway);

        try {
            ItemCardapio itemCardapio = consultarItemCardapioUseCase.executar(id);

            return ItemCardapioPresenter.toDTO(itemCardapio);
        } catch (UsuarioNaoEncontradoException | EnderecoNaoEncontradoException | TipoUsuarioNaoEncontradoException e) {
            return null;
        }
    }

    public ItemCardapioDTO alterar(Long id, ItemCardapioDTO usuarioDTO) {
        IItemCardapioGateway itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);

        AtualizarItemCardapioUseCase atualizarItemCardapioUseCase = AtualizarItemCardapioUseCase.create(itemCardapioGateway);

        atualizarItemCardapioUseCase.executar(id, usuarioDTO);

        return null;
    }

    public void excluir(Long id) {
        IItemCardapioGateway itemCardapioGateway = ItemCardapioGateway.create(this.dataSource);
        DeletarItemCardapioUsecase deletarUsuarioUsecase = DeletarItemCardapioUsecase.create(itemCardapioGateway);

        deletarUsuarioUsecase.executar(id);
    }

}
