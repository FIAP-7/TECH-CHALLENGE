package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler.TipoUsuarioExistenteHandler;

public class AtualizarTipoUsuarioUseCase {

	private final ITipoUsuarioGateway tipoUsuarioGateway;

	private AtualizarTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway) {
		this.tipoUsuarioGateway = tipoUsuarioGateway;
	}

	public static AtualizarTipoUsuarioUseCase create(ITipoUsuarioGateway tipoUsuarioGateway) {
		return new AtualizarTipoUsuarioUseCase(tipoUsuarioGateway);
	}

	public void executar(Long id, TipoUsuarioDTO tipoUsuarioDTO) {
		TipoUsuario tipoUsuario = TipoUsuario.create(id, tipoUsuarioDTO.nome());

		validaRegras(tipoUsuario);

		this.tipoUsuarioGateway.atualizar(id, tipoUsuario);
	}

	private Boolean validaRegras(TipoUsuario tipoUsuario) {
		var tipoUsuarioExistenteHander = new TipoUsuarioExistenteHandler(this.tipoUsuarioGateway);

		return tipoUsuarioExistenteHander.handle(tipoUsuario);
	}
}
