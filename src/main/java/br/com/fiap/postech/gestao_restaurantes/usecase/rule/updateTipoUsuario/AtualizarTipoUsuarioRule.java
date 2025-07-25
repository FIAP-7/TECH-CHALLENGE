package br.com.fiap.postech.gestao_restaurantes.usecase.rule.updateTipoUsuario;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputTipoUsuarioDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;

public interface AtualizarTipoUsuarioRule {
	
	Optional<OutputDto> validate(Integer id, InputTipoUsuarioDto inputTipoUsuarioDto);

}
