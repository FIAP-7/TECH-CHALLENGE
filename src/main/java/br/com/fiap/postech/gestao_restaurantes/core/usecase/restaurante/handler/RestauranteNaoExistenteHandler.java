package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.handler;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.exception.RestauranteExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;

public class RestauranteNaoExistenteHandler extends RestauranteHandler{

    private IRestauranteGateway restauranteGateway;

    public RestauranteNaoExistenteHandler(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public Boolean handle(Restaurante restaurante) {
        Optional<Restaurante> restauranteExistente = restauranteGateway.buscarPorNome(restaurante.getNome());

        if(restauranteExistente.isPresent()){
            throw new RestauranteExistenteException();
        }

        if(next != null){
            return next.handle(restaurante);
        } else {
            return true;
        }
    }
}
