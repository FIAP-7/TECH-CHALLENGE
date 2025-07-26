package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.usuarioRule;

import br.com.fiap.postech.gestao_restaurantes.infra.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.SenhaFormatoInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
//@Component
@RequiredArgsConstructor
public class SenhaRule implements UsuarioRule{

    private static final Pattern compile = Pattern.compile("(?=.*[}{,.?=+_/*|@#!$%¨&)(\\[\\]\\\\-])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}");

    @Override
    public Optional<OutputDto> validate(InputDto inputDto) {
        Usuario usuario = inputDto.getNovoUsuario();

        Matcher matcher = compile.matcher(usuario.getSenha());

        if (!matcher.find()) {
            throw new SenhaFormatoInvalidoException();
        }

        return Optional.empty();
    }
}
