package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.criarUsuarioRule;

import br.com.fiap.postech.gestao_restaurantes.infra.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioExistenteException;
import br.com.fiap.postech.gestao_restaurantes.infra.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Deprecated
@Slf4j
//@Component
//@RequiredArgsConstructor
public class CriarUsuarioCadastradoRule implements CriarUsuarioRuleBase {

    private final UsuarioGateway usuarioGateway;

    public CriarUsuarioCadastradoRule(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Optional<OutputDto> validate(InputDto inputDto) {
        Usuario novoUsuario = inputDto.getNovoUsuario();

        Optional<Usuario> usuario = usuarioGateway.buscarPorLogin(novoUsuario.getLogin());

        if(usuario.isPresent()) {
            log.warn("Usuário já existe com o login informado. {}", novoUsuario.getLogin());
            throw new UsuarioExistenteException();
        }

        return Optional.empty();
    }
}
