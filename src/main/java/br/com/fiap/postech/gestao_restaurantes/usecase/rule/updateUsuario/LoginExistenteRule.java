package br.com.fiap.postech.gestao_restaurantes.usecase.rule.updateUsuario;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.exception.UsuarioExistenteException;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginExistenteRule implements AtualizarBaseRule {

    private final UsuarioGateway usuarioGateway;

	@Override
	public Optional<OutputDto> validate(Long id, InputDto inputDto) {
		Usuario novoUsuario = inputDto.getNovoUsuario();
        usuarioGateway.buscarPorLogin(novoUsuario.getLogin())
			.ifPresent(usuarioExistente -> {
		        if (!usuarioExistente.getId().equals(id)) {
		            log.warn("Usuário já existe com o login informado: {}", novoUsuario.getLogin());
		            throw new UsuarioExistenteException();
		        }
			});
		return Optional.empty();
	}

}
