package br.com.fiap.postech.gestao_restaurantes.usecase.rule.criarUsuarioRule;

import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;

import java.util.Optional;

public interface CriarUsuarioRuleBase {

    Optional<OutputDto> validate(InputDto inputDto);

}
