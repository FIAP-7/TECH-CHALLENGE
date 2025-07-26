package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.criarTipoUsuarioRule;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputTipoUsuarioDto;

public interface CriarTipoUsuarioRuleBase {

    Optional<OutputDto> validate(InputTipoUsuarioDto inputTipoUsuarioDto);

}
