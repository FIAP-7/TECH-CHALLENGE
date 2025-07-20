package br.com.fiap.postech.gestao_restaurantes.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.domain.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.gateway.TipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputTipoUsuarioDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.tipoUsuarioRule.TipoUsuarioRule;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.updateTipoUsuario.AtualizarTipoUsuarioRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AtualizarTipoUsuarioUseCase {

	private final TipoUsuarioGateway tipoUsuarioGateway;
    private final List<AtualizarTipoUsuarioRule> rules;
	private final List<TipoUsuarioRule> rulesTipoUsuario;

	public void executar(Integer id, TipoUsuario tipoUsuario) {
		validaRegras(id, tipoUsuario);
		tipoUsuarioGateway.atualizar(id, tipoUsuario);
	}
	
	private void validaRegras(Integer id, TipoUsuario tipoUsuario) {
		var inputTipoUsuarioDto = new InputTipoUsuarioDto(tipoUsuario);
		rules.forEach(rule -> rule.validate(id, inputTipoUsuarioDto));
		rulesTipoUsuario.forEach(rule -> rule.validate(inputTipoUsuarioDto));
	}
}
