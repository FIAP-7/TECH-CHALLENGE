package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.criarUsuarioRule;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;

import java.util.Optional;

public interface CriarUsuarioRuleBase {

    Optional<OutputDto> validate(InputDto inputDto);

}
