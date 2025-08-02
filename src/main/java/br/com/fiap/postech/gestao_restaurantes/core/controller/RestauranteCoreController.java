package br.com.fiap.postech.gestao_restaurantes.core.controller;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.RestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IRestauranteDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IUsuarioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.RestaurantePresenter;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.AtualizarRestauranteUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.ConsultarRestauranteUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.CriarRestauranteUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.DeletarRestauranteUseCase;

public class RestauranteCoreController {

    private final IRestauranteDataSource restauranteDataSource;
    private final IUsuarioDataSource usuarioDataSource;

    private RestauranteCoreController(IRestauranteDataSource restauranteDataSource, IUsuarioDataSource usuarioDataSource) {
        this.restauranteDataSource = restauranteDataSource;
        this.usuarioDataSource = usuarioDataSource;
    }

    public static RestauranteCoreController create(IRestauranteDataSource restauranteDataSource, IUsuarioDataSource usuarioDataSource) {
        return new RestauranteCoreController(restauranteDataSource, usuarioDataSource);
    }

    public RestauranteDTO incluir(NovoRestauranteDTO novoRestauranteDTO) {
    	IRestauranteGateway restauranteGateway = RestauranteGateway.create(restauranteDataSource);
        IUsuarioGateway usuarioGateway = UsuarioGateway.create(this.usuarioDataSource);

        CriarRestauranteUseCase criarRestauranteUseCase = CriarRestauranteUseCase.create(restauranteGateway, usuarioGateway);
        
        criarRestauranteUseCase.executar(novoRestauranteDTO);
        
        return null;
    }
    
    public RestauranteDTO buscarPorId(Long id) {
    	IRestauranteGateway restauranteGateway = RestauranteGateway.create(restauranteDataSource);
    	
    	ConsultarRestauranteUseCase consultarRestauranteUseCase = ConsultarRestauranteUseCase.create(restauranteGateway);
    	
        Restaurante restaurante = consultarRestauranteUseCase.executar(id);

        return RestaurantePresenter.toDTO(restaurante);
    }
    
    public RestauranteDTO alterar(Long id, RestauranteDTO restauranteDTO) {
    	IRestauranteGateway restauranteGateway = RestauranteGateway.create(restauranteDataSource);
        IUsuarioGateway usuarioGateway = UsuarioGateway.create(this.usuarioDataSource);
        
        AtualizarRestauranteUseCase atualizarRestauranteUseCase = AtualizarRestauranteUseCase.create(restauranteGateway, usuarioGateway);
        
        atualizarRestauranteUseCase.executar(id, restauranteDTO);

        return null;
    }
    
    public void excluir(Long id) {
    	IRestauranteGateway restauranteGateway = RestauranteGateway.create(restauranteDataSource);

    	DeletarRestauranteUseCase deletarRestauranteUseCase = DeletarRestauranteUseCase.create(restauranteGateway);

    	deletarRestauranteUseCase.executar(id);
    }
}
