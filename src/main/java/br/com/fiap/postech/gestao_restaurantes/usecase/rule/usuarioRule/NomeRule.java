package br.com.fiap.postech.gestao_restaurantes.usecase.rule.usuarioRule;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.exception.NomeUsuarioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputDto;
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
public class NomeRule implements UsuarioRule{

    private static final Pattern compile = Pattern.compile("[0-9{},.?~=+_/*\\-\\\\|\\[\\]ªº%&()#!$@]+");

    @Override
    public Optional<OutputDto> validate(InputDto inputDto) {
        Usuario usuario = inputDto.getNovoUsuario();

        Matcher matcher = compile.matcher(usuario.getNome());

        if (matcher.find()) {
            log.info("Nome no formato incorreto: {}", usuario.getNome());
            throw new NomeUsuarioInvalidoException();
        }

        return Optional.empty();
    }
}
