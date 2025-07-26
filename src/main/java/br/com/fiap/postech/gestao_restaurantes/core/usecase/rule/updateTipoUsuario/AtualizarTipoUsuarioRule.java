package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.updateTipoUsuario;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputTipoUsuarioDto;

public interface AtualizarTipoUsuarioRule {
	
	Optional<OutputDto> validate(Long id, InputTipoUsuarioDto inputTipoUsuarioDto);

}
