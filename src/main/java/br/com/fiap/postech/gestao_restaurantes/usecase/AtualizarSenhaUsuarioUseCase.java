package br.com.fiap.postech.gestao_restaurantes.usecase;

import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputPasswordDTO;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.updatePassword.PasswordRule;
import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AtualizarSenhaUsuarioUseCase {

	private final UsuarioGateway usuarioGateway;
	private final List<PasswordRule> passwordRules;

	public void executar(Long id, String novaSenha) {
		validaRegras(id, novaSenha);
		usuarioGateway.atualizarSenha(id, novaSenha);
	}

	private void validaRegras(Long id, String novaSenha) {
		InputPasswordDTO inputPasswordDTO = new InputPasswordDTO(id, novaSenha);

		passwordRules.forEach(passwordRule -> passwordRule.validate(inputPasswordDTO));
	}
}
