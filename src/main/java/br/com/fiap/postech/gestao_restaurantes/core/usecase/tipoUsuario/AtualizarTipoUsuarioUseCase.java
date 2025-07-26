package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.TipoUsuarioPresenter;

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

		//validaRegras(id, tipoUsuario);

		TipoUsuario tipoUsuario = TipoUsuarioPresenter.toEntity(tipoUsuarioDTO);

		tipoUsuarioGateway.atualizar(id, tipoUsuario);
	}

	/*
	private void validaRegras(Long id, TipoUsuario tipoUsuario) {
		var inputTipoUsuarioDto = new InputTipoUsuarioDto(tipoUsuario);
		rules.forEach(rule -> rule.validate(id, inputTipoUsuarioDto));
		rulesTipoUsuario.forEach(rule -> rule.validate(inputTipoUsuarioDto));
	}

	 */
}
