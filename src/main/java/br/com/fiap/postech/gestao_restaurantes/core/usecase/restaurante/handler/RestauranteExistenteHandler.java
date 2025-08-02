package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;

public class RestauranteExistenteHandler extends RestauranteHandler {

    private final IRestauranteGateway restauranteGateway;

    public RestauranteExistenteHandler(final IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public Boolean handle(Restaurante restaurante) {
    	Restaurante restauranteExistente = restauranteGateway.buscarPorId(restaurante.getId());
    	
    	if (restauranteExistente == null) {
    		throw new RestauranteNaoEncontradoException();
    	}

        if(next != null){
            return next.handle(restaurante);
        } else {
            return true;
        }
    }
}
