package br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource;

import br.com.fiap.postech.gestao_restaurantes.core.dto.ItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;

import java.util.List;
import java.util.Optional;

public interface IItemCardapioDataSource {

    Long criar(NovoItemCardapioDTO novoItemCardapio);

    void deletar(Long id);

    void atualizar(Long id, ItemCardapioDTO itemCardapio);

    Optional<ItemCardapioDTO> buscarPorId(Long id);

    Optional<List<ItemCardapioDTO>> buscarPorIdRestaurante(Long idRestaurante);
}
