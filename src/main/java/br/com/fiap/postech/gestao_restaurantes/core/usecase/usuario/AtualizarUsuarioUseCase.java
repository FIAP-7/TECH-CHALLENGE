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

		TipoUsuarioDTO tipoUsuarioDTO = usuarioDTO.tipoUsuario();

		TipoUsuario tipoUsuario = TipoUsuario.create(
				tipoUsuarioDTO.id(),
				tipoUsuarioDTO.nome()
		);

		EnderecoDTO enderecoDTO = usuarioDTO.endereco();

		Endereco endereco = Endereco.create(
				enderecoDTO.logradouro(),
				enderecoDTO.numero(),
				enderecoDTO.complemento(),
				enderecoDTO.bairro(),
				enderecoDTO.cidade(),
				enderecoDTO.estado(),
				enderecoDTO.cep()
		);

		Usuario usuario = Usuario.create(
				usuarioDTO.id(),
				usuarioDTO.cpf(),
				usuarioDTO.nome(),
				usuarioDTO.email(),
				usuarioDTO.login(),
				usuarioDTO.senha(),
				usuarioDTO.dataUltimaAlteracao(),
				tipoUsuario,
				endereco
		);


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
