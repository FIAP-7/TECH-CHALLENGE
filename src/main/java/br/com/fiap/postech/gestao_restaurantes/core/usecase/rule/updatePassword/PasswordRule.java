package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.updatePassword;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputPasswordDTO;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;

import java.util.Optional;

public interface PasswordRule {

    Optional<OutputDto> validate(InputPasswordDTO inputPasswordDTO);

}
