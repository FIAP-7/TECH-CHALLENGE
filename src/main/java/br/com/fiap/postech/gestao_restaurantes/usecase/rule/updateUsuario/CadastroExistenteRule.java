package br.com.fiap.postech.gestao_restaurantes.usecase.rule.updateUsuario;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.OutputDto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CadastroExistenteRule implements AtualizarBaseRule{

    private final UsuarioGateway usuarioGateway;

	@Override
	public Optional<OutputDto> validate(Long id, InputDto inputDto) {
        usuarioGateway.buscarPorId(id).orElseThrow(UsuarioNaoEncontradoException::new);
		return Optional.empty();
	}
}
