package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;

public class DeletarRestauranteUseCase {

    private final IRestauranteGateway restauranteGateway;
    
    private DeletarRestauranteUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }
    
    public static DeletarRestauranteUseCase create(final IRestauranteGateway restauranteGateway) {
        return new DeletarRestauranteUseCase(restauranteGateway);
    }

    public void executar(Long id) {
        final Restaurante restaurante = this.restauranteGateway.buscarPorId(id);

        if (restaurante == null) {
            throw new RestauranteNaoEncontradoException();
        }

        this.restauranteGateway.deletar(id);
    }
    
}
