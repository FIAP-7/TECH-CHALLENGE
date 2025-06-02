package br.com.fiap.postech.gestao_restaurantes.usecase.rule.usuarioRule;

import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;

import java.util.Optional;

public interface UsuarioRule {

    Optional<OutputDto> validate(InputDto inputDto);

}
