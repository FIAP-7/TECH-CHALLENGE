package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.updateUsuario;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.infra.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class CadastroExistenteRule implements AtualizarBaseRule{

    private final UsuarioGateway usuarioGateway;

	@Override
	public Optional<OutputDto> validate(Long id, InputDto inputDto) {
        usuarioGateway.buscarPorId(id).orElseThrow(UsuarioNaoEncontradoException::new);
		return Optional.empty();
	}
}
