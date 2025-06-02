package br.com.fiap.postech.gestao_restaurantes.usecase.rule.updatePassword;

import br.com.fiap.postech.gestao_restaurantes.exception.SenhaFormatoInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputPasswordDTO;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordRequisitosRule implements PasswordRule {

    private static final Pattern compile = Pattern.compile("(?=.*[}{,.?=+_/*|@#!$%¨&)(\\[\\]\\\\-])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}");

    @Override
    public Optional<OutputDto> validate(InputPasswordDTO inputPasswordDTO) {
        String password = inputPasswordDTO.getPassword();

        Matcher matcher = compile.matcher(password);

        if (!matcher.find()) {
            throw new SenhaFormatoInvalidoException();
        }

        return Optional.empty();
    }
}
