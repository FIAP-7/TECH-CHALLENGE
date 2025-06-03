package br.com.fiap.postech.gestao_restaurantes.usecase.rule.usuarioRule;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TipoUsuarioRule implements UsuarioRule{

    @Override
    public Optional<OutputDto> validate(InputDto inputDto) {
        Usuario usuario = inputDto.getNovoUsuario();

        if (usuario.getTipoUsuario() == null) {
            throw new TipoUsuarioNaoEncontradoException();
        }

        return Optional.empty();
    }
}
