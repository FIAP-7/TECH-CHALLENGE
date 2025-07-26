package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.updatePassword;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputPasswordDTO;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.infra.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

//@Component
//@RequiredArgsConstructor
public class PasswordCadastroExistenteRule implements PasswordRule {

    private final UsuarioGateway usuarioGateway;

    public PasswordCadastroExistenteRule(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Optional<OutputDto> validate(InputPasswordDTO inputPasswordDTO) {
        usuarioGateway.buscarPorId(inputPasswordDTO.getId()).orElseThrow(UsuarioNaoEncontradoException::new);
        return Optional.empty();
    }
}
