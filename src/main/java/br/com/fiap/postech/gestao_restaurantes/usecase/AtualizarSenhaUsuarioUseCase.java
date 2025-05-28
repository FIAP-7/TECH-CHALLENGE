package br.com.fiap.postech.gestao_restaurantes.usecase;

import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AtualizarSenhaUsuarioUseCase {

	private final UsuarioGateway usuarioGateway;

	public void executar(Long id, String novaSenha) {
		usuarioGateway.atualizarSenha(id, novaSenha);
	}
}
