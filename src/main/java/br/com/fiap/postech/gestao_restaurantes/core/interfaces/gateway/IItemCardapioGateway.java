package br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway;

import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;

import java.util.Optional;

public interface IItemCardapioGateway {

    Long criar(ItemCardapio usuario);

    void atualizar(Long id, ItemCardapio usuario);

    void deletar(Long id);

    Optional<ItemCardapio> buscarPorId(Long id);

    Optional<ItemCardapio> buscarPorRestaurante(Long idRestaurante);
}
