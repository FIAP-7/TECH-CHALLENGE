package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.CadastroExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.UsuarioTipoUsuarioExistenteHandler;

public class AtualizarUsuarioUseCase {

	private final IUsuarioGateway usuarioGateway;
	private final ITipoUsuarioGateway tipoUsuarioGateway;

	private AtualizarUsuarioUseCase(IUsuarioGateway usuarioGateway, ITipoUsuarioGateway tipoUsuarioGateway) {
		this.usuarioGateway = usuarioGateway;
		this.tipoUsuarioGateway = tipoUsuarioGateway;
	}

	public static AtualizarUsuarioUseCase create(IUsuarioGateway usuarioGateway, ITipoUsuarioGateway tipoUsuarioGateway) {
		return new AtualizarUsuarioUseCase(usuarioGateway, tipoUsuarioGateway);
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
				id,
				usuarioDTO.cpf(),
				usuarioDTO.nome(),
				usuarioDTO.email(),
				usuarioDTO.login(),
				usuarioDTO.senha(),
				usuarioDTO.dataUltimaAlteracao(),
				tipoUsuario,
				endereco
		);

		validaRegras(usuario);

		usuarioGateway.atualizar(id, usuario);
	}

	private Boolean validaRegras(Usuario usuario) {
		var cadastroExistente = new CadastroExistenteHandler(usuarioGateway);
		var usuarioTipoUsuarioExistenteHandler = new UsuarioTipoUsuarioExistenteHandler(tipoUsuarioGateway);

		cadastroExistente.setNext(usuarioTipoUsuarioExistenteHandler);

		return cadastroExistente.handle(usuario);
	}
}
