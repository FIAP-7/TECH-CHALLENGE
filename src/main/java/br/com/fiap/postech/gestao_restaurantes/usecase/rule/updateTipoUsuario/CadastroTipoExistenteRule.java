package br.com.fiap.postech.gestao_restaurantes.usecase.rule.updateTipoUsuario;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.gateway.TipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputTipoUsuarioDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.OutputDto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CadastroTipoExistenteRule implements AtualizarTipoUsuarioRule{

    private final TipoUsuarioGateway tipoUsuarioGateway;

	@Override
	public Optional<OutputDto> validate(Integer id, InputTipoUsuarioDto inputTipoUsuarioDto) {
		tipoUsuarioGateway.buscarPorId(id).orElseThrow(TipoUsuarioNaoEncontradoException::new);
		return Optional.empty();
	}
}