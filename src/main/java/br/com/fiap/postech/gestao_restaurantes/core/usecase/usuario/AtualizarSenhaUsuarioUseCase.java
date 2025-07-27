package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.PasswordCadastroExistente;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.PasswordValidaHandler;

public class AtualizarSenhaUsuarioUseCase {

	private final IUsuarioGateway usuarioGateway;

	private AtualizarSenhaUsuarioUseCase(final IUsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	public static AtualizarSenhaUsuarioUseCase create(final IUsuarioGateway usuarioGateway) {
		return new AtualizarSenhaUsuarioUseCase(usuarioGateway);
	}

	public void executar(Long id, String novaSenha) {
		validaRegras(id, novaSenha);

		usuarioGateway.atualizarSenha(id, novaSenha);
	}

	private void validaRegras(Long id, String novaSenha) {
		PasswordCadastroExistente passwordCadastroExistente = new PasswordCadastroExistente(this.usuarioGateway);
		PasswordValidaHandler passwordValidaHandler = new PasswordValidaHandler(this.usuarioGateway);

		passwordCadastroExistente.setNext(passwordValidaHandler);

		passwordCadastroExistente.handle(id, novaSenha);
	}
}
