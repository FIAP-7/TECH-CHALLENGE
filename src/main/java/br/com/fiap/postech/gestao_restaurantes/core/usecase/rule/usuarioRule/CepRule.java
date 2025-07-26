package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.usuarioRule;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import br.com.fiap.postech.gestao_restaurantes.infra.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.CepInvalidoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
//@Component
@RequiredArgsConstructor
public class CepRule implements UsuarioRule {

    private static final Pattern compile = Pattern.compile("\\d{5}-\\d{3}");

    @Override
    public Optional<OutputDto> validate(InputDto inputDto) {
        Usuario usuario = inputDto.getNovoUsuario();

        if(usuario.getEndereco() != null && usuario.getEndereco().getCep() != null) {
            Matcher matcher = compile.matcher(usuario.getEndereco().getCep());

            if (!matcher.find()) {
                throw new CepInvalidoException();
            }
        }

        return Optional.empty();
    }

}
