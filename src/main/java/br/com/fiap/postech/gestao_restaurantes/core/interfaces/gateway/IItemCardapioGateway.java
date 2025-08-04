package br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway;

import br.com.fiap.postech.gestao_restaurantes.core.entities.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface IItemCardapioGateway {

    Long criar(ItemCardapio usuario);

    void atualizar(Long id, ItemCardapio itemCardapio);

    void deletar(Long id);

    Optional<ItemCardapio> buscarPorId(Long id);

    Optional<List<ItemCardapio>> buscarPorIdRestaurante(Long idRestaurante);
}
