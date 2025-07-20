package br.com.fiap.postech.gestao_restaurantes.usecase.rule.tipoUsuarioRule;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputTipoUsuarioDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;

public interface TipoUsuarioRule {

    Optional<OutputDto> validate(InputTipoUsuarioDto inputTipoUsuarioDto);

}
