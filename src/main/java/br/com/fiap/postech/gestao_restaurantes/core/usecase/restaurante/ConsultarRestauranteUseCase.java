package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;

public class ConsultarRestauranteUseCase {
	
	private final IRestauranteGateway restauranteGateway;

	private ConsultarRestauranteUseCase(IRestauranteGateway restauranteGateway) {
		this.restauranteGateway = restauranteGateway;
	}
	
	public static ConsultarRestauranteUseCase create(IRestauranteGateway restauranteGateway) {
		return new ConsultarRestauranteUseCase(restauranteGateway);
	}
	
	public Restaurante executar(Long id) {
		Restaurante restaurante = restauranteGateway.buscarPorId(id);
		
		if (restaurante == null) {
			throw new RestauranteNaoEncontradoException();
		}
		
		return restaurante;
	}

}