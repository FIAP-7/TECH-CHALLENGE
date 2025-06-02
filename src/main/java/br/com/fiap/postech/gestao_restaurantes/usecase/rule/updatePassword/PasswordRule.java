package br.com.fiap.postech.gestao_restaurantes.usecase.rule.updatePassword;

import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputPasswordDTO;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;

import java.util.Optional;

public interface PasswordRule {

    Optional<OutputDto> validate(InputPasswordDTO inputPasswordDTO);

}
