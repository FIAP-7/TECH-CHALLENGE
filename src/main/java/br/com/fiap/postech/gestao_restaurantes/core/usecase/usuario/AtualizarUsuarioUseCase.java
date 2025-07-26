package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;


import br.com.fiap.postech.gestao_restaurantes.core.presenters.UsuarioPresenter;


public class AtualizarUsuarioUseCase {

	private final IUsuarioGateway usuarioGateway;

	private AtualizarUsuarioUseCase(IUsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	public static AtualizarUsuarioUseCase create(IUsuarioGateway usuarioGateway) {
		return new AtualizarUsuarioUseCase(usuarioGateway);
	}

	public void executar(Long id, UsuarioDTO usuarioDTO) {
		//validaRegras(id, usuario);

		Usuario usuario = UsuarioPresenter.toEntity(usuarioDTO);

		usuarioGateway.atualizar(id, usuario);
	}

	/*
	private void validaRegras(Long id, Usuario usuario) {
		var inputDto = new InputDto(usuario);
		//rules.forEach(rule -> rule.validate(id, inputDto));
		//rulesUsuario.forEach(rule -> rule.validate(inputDto));
	}
	 */
}
