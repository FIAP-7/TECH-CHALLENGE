package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.updateTipoUsuario;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.infra.gateway.TipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputTipoUsuarioDto;
import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class CadastroTipoExistenteRule implements AtualizarTipoUsuarioRule{

    private final TipoUsuarioGateway tipoUsuarioGateway;

	@Override
	public Optional<OutputDto> validate(Long id, InputTipoUsuarioDto inputTipoUsuarioDto) {
		tipoUsuarioGateway.buscarPorId(id).orElseThrow(TipoUsuarioNaoEncontradoException::new);
		return Optional.empty();
	}
}