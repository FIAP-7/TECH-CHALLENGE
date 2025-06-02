package br.com.fiap.postech.gestao_restaurantes.usecase.rule.updatePassword;

import br.com.fiap.postech.gestao_restaurantes.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputPasswordDTO;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PasswordCadastroExistenteRule implements PasswordRule {

    private final UsuarioGateway usuarioGateway;

    @Override
    public Optional<OutputDto> validate(InputPasswordDTO inputPasswordDTO) {
        usuarioGateway.buscarPorId(inputPasswordDTO.getId()).orElseThrow(UsuarioNaoEncontradoException::new);
        return Optional.empty();
    }
}
