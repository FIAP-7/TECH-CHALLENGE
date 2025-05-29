package br.com.fiap.postech.gestao_restaurantes.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.criarUsuarioRule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.updateUsuario.AtualizarBaseRule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AtualizarUsuarioUseCase {

	private final UsuarioGateway usuarioGateway;
    private final List<AtualizarBaseRule> rules;

	public void executar(Long id, Usuario usuario) {
		validaRegras(id, usuario);
		usuarioGateway.atualizar(id, usuario);
	}
	
	private void validaRegras(Long id, Usuario usuario) {
		var inputDto = new InputDto(usuario);
		rules.forEach(rule -> rule.validate(id, inputDto));
	}
}
