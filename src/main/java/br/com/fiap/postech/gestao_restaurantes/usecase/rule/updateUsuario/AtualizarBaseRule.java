package br.com.fiap.postech.gestao_restaurantes.usecase.rule.updateUsuario;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.OutputDto;

public interface AtualizarBaseRule {

	Optional<OutputDto> validate(Long id, InputDto inputDto);
}
