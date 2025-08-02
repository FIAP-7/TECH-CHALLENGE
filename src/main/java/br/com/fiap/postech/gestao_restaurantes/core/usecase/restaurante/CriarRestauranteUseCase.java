package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.handler.RestauranteNaoExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.handler.RestauranteUsuarioExistenteHandler;

public class CriarRestauranteUseCase {

	private final IRestauranteGateway restauranteGateway;
    private final IUsuarioGateway usuarioGateway;
    
    private CriarRestauranteUseCase(final IRestauranteGateway restauranteGateway, final IUsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
    	this.usuarioGateway = usuarioGateway;
    }
    
    public static CriarRestauranteUseCase create(final IRestauranteGateway restauranteGateway, final IUsuarioGateway usuarioGateway) {
        return new CriarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }
    
    public Long executar(NovoRestauranteDTO novoRestauranteDTO) {
        Usuario usuario = usuarioGateway.buscarPorId(novoRestauranteDTO.usuario().id());
        
        EnderecoDTO enderecoDTO = novoRestauranteDTO.endereco();

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
				novoRestauranteDTO.nome(), 
				novoRestauranteDTO.tipoCozinha(),
				novoRestauranteDTO.horarioFuncionamento(),
				usuario,
				endereco
        );
		
        validaRegras(restaurante);

        return restauranteGateway.criar(restaurante);
    }

    private Boolean validaRegras(Restaurante restaurante) {
    	var restauranteExistenteHandler = new RestauranteNaoExistenteHandler(restauranteGateway);
    	var usuarioExistenteHandler = new RestauranteUsuarioExistenteHandler(usuarioGateway);
    	
    	restauranteExistenteHandler.setNext(usuarioExistenteHandler);

        return restauranteExistenteHandler.handle(restaurante);
    }
    
}
