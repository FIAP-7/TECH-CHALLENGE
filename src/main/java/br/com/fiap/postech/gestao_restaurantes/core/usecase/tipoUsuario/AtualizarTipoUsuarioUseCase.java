package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.TipoUsuarioPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler.TipoUsuarioExistenteHandler;

import java.util.Optional;

public class AtualizarTipoUsuarioUseCase {

	private final ITipoUsuarioGateway tipoUsuarioGateway;

	private AtualizarTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway) {
		this.tipoUsuarioGateway = tipoUsuarioGateway;
	}

	public static AtualizarTipoUsuarioUseCase create(ITipoUsuarioGateway tipoUsuarioGateway) {
		return new AtualizarTipoUsuarioUseCase(tipoUsuarioGateway);
	}

	public void executar(Long id, TipoUsuarioDTO tipoUsuarioDTO) {
		Optional<TipoUsuario> tipoUsuarioOptional = this.tipoUsuarioGateway.buscarPorId(id);

		if(tipoUsuarioOptional.isEmpty()){
			throw new TipoUsuarioNaoEncontradoException();
		}

		TipoUsuario tipoUsuario = TipoUsuarioPresenter.toEntity(tipoUsuarioDTO);

		validaRegras(tipoUsuario);

		this.tipoUsuarioGateway.atualizar(id, tipoUsuario);
	}

	private Boolean validaRegras(TipoUsuario tipoUsuario) {
		var tipoUsuarioExistenteHander = new TipoUsuarioExistenteHandler(this.tipoUsuarioGateway);

		return tipoUsuarioExistenteHander.handle(tipoUsuario);
	}
}
