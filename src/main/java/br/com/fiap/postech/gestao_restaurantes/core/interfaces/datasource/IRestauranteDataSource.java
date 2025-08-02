package br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;

public interface IRestauranteDataSource {

	Long criar(NovoRestauranteDTO restaurante);

    void deletar(Long id);

    RestauranteDTO buscarPorId(Long id);

    void atualizar(Long id, RestauranteDTO restaurante);

	Optional<RestauranteDTO> buscarPorNome(String nome);

}
