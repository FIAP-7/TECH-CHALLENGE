package br.com.fiap.postech.gestao_restaurantes.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsultarUsuarioUseCase {
	
	private final UsuarioGateway usuarioGateway;
	
	public Optional<Usuario> executar(Long id) {
		return usuarioGateway.buscarPorId(id);
	}
}
