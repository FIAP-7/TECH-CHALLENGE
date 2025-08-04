package br.com.fiap.postech.gestao_restaurantes.core.presenters;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;

public class RestaurantePresenter {

	public static RestauranteDTO toDTO(Restaurante restaurante) {
		UsuarioRestauranteDTO usuarioRestauranteDTO = UsuarioPresenter.toUsuarioRestauranteDTO(restaurante.getUsuario());
		EnderecoDTO enderecoDTO = EnderecoPresenter.toDTO(restaurante.getEndereco());
		
		RestauranteDTO restauranteDTO = new RestauranteDTO(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                usuarioRestauranteDTO,
                enderecoDTO
        );
		
		return restauranteDTO;
	}
	
	public static NovoRestauranteDTO toNovoRestauranteDTO(Restaurante restaurante) {
		UsuarioRestauranteDTO usuarioRestauranteDTO = UsuarioPresenter.toUsuarioRestauranteDTO(restaurante.getUsuario());
		EnderecoDTO enderecoDTO = EnderecoPresenter.toDTO(restaurante.getEndereco());
		
		NovoRestauranteDTO restauranteDTO = new NovoRestauranteDTO(
                restaurante.getNome(),
                restaurante.getTipoCozinha(),
                restaurante.getHorarioFuncionamento(),
                usuarioRestauranteDTO,
                enderecoDTO
        );
		
		return restauranteDTO;
	}

    public static Restaurante toEntity(RestauranteDTO restauranteDTO, Usuario usuario, Endereco endereco) {
		return Restaurante.create(restauranteDTO.id(), restauranteDTO.nome(), restauranteDTO.tipoCozinha(), restauranteDTO.horarioFuncionamento(), usuario, endereco);
    }

    public static Restaurante toEntity(NovoRestauranteDTO restauranteDTO, Usuario usuario, Endereco endereco) {
    	return Restaurante.create(restauranteDTO.nome(), restauranteDTO.tipoCozinha(), restauranteDTO.horarioFuncionamento(), usuario, endereco);
    }

	public static Restaurante toEntity(RestauranteDTO restauranteDTO) {
		Usuario usuario = UsuarioPresenter.toEntity(restauranteDTO.usuario());
		Endereco endereco = EnderecoPresenter.toEntity(restauranteDTO.endereco());

		return Restaurante.create(restauranteDTO.nome(), restauranteDTO.tipoCozinha(), restauranteDTO.horarioFuncionamento(), usuario, endereco);
	}
	
}
