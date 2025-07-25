package br.com.fiap.postech.gestao_restaurantes.usecase.rule.criarTipoUsuarioRule;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputTipoUsuarioDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;

public interface CriarTipoUsuarioRuleBase {

    Optional<OutputDto> validate(InputTipoUsuarioDto inputTipoUsuarioDto);

}
