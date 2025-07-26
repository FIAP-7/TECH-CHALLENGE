package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.usuarioRule;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;

import java.util.Optional;

public interface UsuarioRule {

    Optional<OutputDto> validate(InputDto inputDto);

}
