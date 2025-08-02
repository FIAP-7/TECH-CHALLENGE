package br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;

public interface IRestauranteGateway {

	Restaurante buscarPorId(Long id);
	
	Optional<Restaurante> buscarPorNome(String nome);
    
	Long criar(Restaurante restaurante);
    
    void atualizar(Long id, Restaurante restaurante);

    void deletar(Long id); 

}
