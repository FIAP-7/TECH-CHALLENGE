package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

public class ConsultarTipoUsuarioUseCase {
	
	private final ITipoUsuarioGateway tipoUsuarioGateway;

	private ConsultarTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway) {
		this.tipoUsuarioGateway = tipoUsuarioGateway;
	}

	public static ConsultarTipoUsuarioUseCase create(ITipoUsuarioGateway tipoUsuarioGateway) {
		return new ConsultarTipoUsuarioUseCase(tipoUsuarioGateway);
	}
	
	public Optional<TipoUsuario> executar(Long id) {
		Optional<TipoUsuario> tipoUsuario = tipoUsuarioGateway.buscarPorId(id);

		if (tipoUsuario.isEmpty()) {
			throw new TipoUsuarioNaoEncontradoException();
		}

		return tipoUsuario;
	}
}
