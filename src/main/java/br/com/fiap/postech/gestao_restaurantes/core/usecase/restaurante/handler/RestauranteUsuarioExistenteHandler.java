package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.DadoInvalidoUsuarioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

public class RestauranteUsuarioExistenteHandler extends RestauranteHandler {
	
	private final IUsuarioGateway usuarioGateway;
	
    public RestauranteUsuarioExistenteHandler(final IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Boolean handle(Restaurante restaurante) {

		if (restaurante.getUsuario() == null || restaurante.getUsuario().getId() == null || restaurante.getUsuario().getId() < 0) {
			throw new DadoInvalidoUsuarioException("Informe um valor válido para o proprietário!");
		}
    	
		Usuario usuario = usuarioGateway.buscarPorId(restaurante.getUsuario().getId());

		if (usuario == null) {
			throw new UsuarioNaoEncontradoException();
		}

        if(next != null){
            return next.handle(restaurante);
        } else {
            return true;
        }
    }
	
}
