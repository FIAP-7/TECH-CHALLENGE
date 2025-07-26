package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.usuarioRule;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.infra.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.DadoInvalidoUsuarioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.infra.gateway.TipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
@RequiredArgsConstructor
public class TipoUsuarioRule implements UsuarioRule{

	private final TipoUsuarioGateway tipoUsuarioGateway;
	
    @Override
    public Optional<OutputDto> validate(InputDto inputDto) {
        Usuario usuario = inputDto.getNovoUsuario();

		if (usuario.getTipoUsuario() == null || usuario.getTipoUsuario().getId() == null || usuario.getTipoUsuario().getId() < 0) {
			throw new DadoInvalidoUsuarioException("Informe um valor válido para o tipo de usuário!");
		}
        
		tipoUsuarioGateway.buscarPorId(usuario.getTipoUsuario().getId()).orElseThrow(TipoUsuarioNaoEncontradoException::new);

        return Optional.empty();
    }
}
