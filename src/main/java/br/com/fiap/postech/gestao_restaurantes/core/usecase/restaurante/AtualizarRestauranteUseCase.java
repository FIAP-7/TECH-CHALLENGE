package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.handler.RestauranteExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.handler.RestauranteUsuarioExistenteHandler;

public class AtualizarRestauranteUseCase {
	
	private final IRestauranteGateway restauranteGateway;
    private final IUsuarioGateway usuarioGateway;
    
    private AtualizarRestauranteUseCase(final IRestauranteGateway restauranteGateway, final IUsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
    	this.usuarioGateway = usuarioGateway;
    }

    public static AtualizarRestauranteUseCase create(final IRestauranteGateway restauranteGateway, final IUsuarioGateway usuarioGateway) {
        return new AtualizarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }
    	
    public void executar(Long id, RestauranteDTO restauranteDTO) {
        Usuario usuario = usuarioGateway.buscarPorId(restauranteDTO.usuario().id());
        
        EnderecoDTO enderecoDTO = restauranteDTO.endereco();

        Endereco endereco = Endereco.create(
                enderecoDTO.logradouro(),
                enderecoDTO.numero(),
                enderecoDTO.complemento(),
                enderecoDTO.bairro(),
                enderecoDTO.cidade(),
                enderecoDTO.estado(),
                enderecoDTO.cep()
        );

		Restaurante restaurante = Restaurante.create(
				id,
				restauranteDTO.nome(), 
				restauranteDTO.tipoCozinha(),
				restauranteDTO.horarioFuncionamento(),
				usuario,
				endereco
        );
		
        validaRegras(restaurante);

        restauranteGateway.atualizar(id, restaurante);
    }

	private Boolean validaRegras(Restaurante restaurante) {
		var restauranteExistente = new RestauranteExistenteHandler(restauranteGateway);
		var usuarioExistenteHandler = new RestauranteUsuarioExistenteHandler(usuarioGateway);
	
		restauranteExistente.setNext(usuarioExistenteHandler);
	
		return restauranteExistente.handle(restaurante);
	}

}