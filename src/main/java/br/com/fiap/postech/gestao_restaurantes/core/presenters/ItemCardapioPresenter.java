package br.com.fiap.postech.gestao_restaurantes.core.presenters;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.ItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;

public class ItemCardapioPresenter {

    public static ItemCardapioDTO toDTO(ItemCardapio itemCardapio) {
        return new ItemCardapioDTO(itemCardapio.getId(), itemCardapio.getNome(), itemCardapio.getDescricao(), itemCardapio.getPreco(), itemCardapio.isDisponivelApenasNoRestaurante(), itemCardapio.getFoto());
    }

    public static NovoItemCardapioDTO toNovoTipoDTO(ItemCardapio itemCardapio) {
        return new NovoItemCardapioDTO(itemCardapio.getNome(), itemCardapio.getDescricao(), itemCardapio.getPreco(), itemCardapio.isDisponivelApenasNoRestaurante(), itemCardapio.getFoto());
    }

    public static ItemCardapio toEntity(ItemCardapioDTO itemCardapioDTO) {
        return ItemCardapio.create(itemCardapioDTO.id(), itemCardapioDTO.nome(), itemCardapioDTO.descricao(), itemCardapioDTO.preco(), itemCardapioDTO.disponivelApenasNoRestaurante(), itemCardapioDTO.foto());
    }

    public static ItemCardapio toEntity(NovoItemCardapioDTO novoItemCardapioDTO) {
        return ItemCardapio.create(novoItemCardapioDTO.nome(), novoItemCardapioDTO.descricao(), novoItemCardapioDTO.preco(), novoItemCardapioDTO.disponivelApenasNoRestaurante(), novoItemCardapioDTO.foto());
    }
}
