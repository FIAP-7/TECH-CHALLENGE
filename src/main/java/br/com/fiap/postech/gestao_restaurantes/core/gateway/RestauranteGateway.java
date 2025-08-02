package br.com.fiap.postech.gestao_restaurantes.core.gateway;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EnderecoNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IRestauranteDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.EnderecoPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.RestaurantePresenter;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.UsuarioPresenter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestauranteGateway implements IRestauranteGateway  {

	private final IRestauranteDataSource restauranteDataSource;

    private RestauranteGateway(IRestauranteDataSource restauranteDataSource) {
        this.restauranteDataSource = restauranteDataSource;
    }
	    
    public static IRestauranteGateway create(IRestauranteDataSource restauranteDataSource) {
        return new RestauranteGateway(restauranteDataSource);
    }
    
	@Override
	public Restaurante buscarPorId(Long id) {
		 final RestauranteDTO restauranteDTO = this.restauranteDataSource.buscarPorId(id);

		 return getRestaurante(restauranteDTO);
	}

	@Override
	public Optional<Restaurante> buscarPorNome(String nome) {
		Optional<RestauranteDTO> restauranteDTO = this.restauranteDataSource.buscarPorNome(nome);
		
		if (restauranteDTO.isEmpty()) {
			return Optional.empty();
		}

		return restauranteDTO.map(RestaurantePresenter::toEntity);
	}

	@Override
	public Long criar(Restaurante restaurante) {
		NovoRestauranteDTO novoRestauranteDTO = RestaurantePresenter.toNovoRestauranteDTO(restaurante);

		return this.restauranteDataSource.criar(novoRestauranteDTO);
	}

	@Override
	public void atualizar(Long id, Restaurante restaurante) {
		 RestauranteDTO restauranteDTO = this.restauranteDataSource.buscarPorId(id);

        if (restauranteDTO != null) {
            this.restauranteDataSource.atualizar(id, RestaurantePresenter.toDTO(restaurante));
        }
	}

	@Override
	public void deletar(Long id) {
		RestauranteDTO restauranteDTO = this.restauranteDataSource.buscarPorId(id);

        if (restauranteDTO != null) {
            this.restauranteDataSource.deletar(id);
        }
	}

	private static Restaurante getRestaurante(RestauranteDTO restauranteDTO) {
        if(restauranteDTO == null){
           return null;
        }

        if(restauranteDTO.usuario() == null){
            throw new UsuarioNaoEncontradoException();
        }

        Usuario usuario = UsuarioPresenter.toEntity(restauranteDTO.usuario());

        if(restauranteDTO.endereco() == null){
            throw new EnderecoNaoEncontradoException();
        }

        Endereco endereco = EnderecoPresenter.toEntity(restauranteDTO.endereco());

        return RestaurantePresenter.toEntity(restauranteDTO, usuario, endereco);
    }
}
