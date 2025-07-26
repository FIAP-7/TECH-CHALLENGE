package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

public class ConsultarUsuarioUseCase {
	
	private final IUsuarioGateway usuarioGateway;

	private ConsultarUsuarioUseCase(IUsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	public static ConsultarUsuarioUseCase create(IUsuarioGateway usuarioGateway) {
		return new ConsultarUsuarioUseCase(usuarioGateway);
	}

	public Usuario executar(Long id) {
		Usuario usuario = usuarioGateway.buscarPorId(id);

		if(usuario == null) {
			throw new UsuarioNaoEncontradoException();
		}

		return usuario;
	}
}
