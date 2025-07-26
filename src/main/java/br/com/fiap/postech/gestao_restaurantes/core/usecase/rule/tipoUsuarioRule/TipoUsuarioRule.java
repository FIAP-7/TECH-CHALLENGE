package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.tipoUsuarioRule;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputTipoUsuarioDto;

public interface TipoUsuarioRule {

    Optional<OutputDto> validate(InputTipoUsuarioDto inputTipoUsuarioDto);

}
