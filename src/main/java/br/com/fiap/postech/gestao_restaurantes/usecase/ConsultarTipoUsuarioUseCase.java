package br.com.fiap.postech.gestao_restaurantes.usecase;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.domain.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.gateway.TipoUsuarioGateway;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsultarTipoUsuarioUseCase {
	
	private final TipoUsuarioGateway tipoUsuarioGateway;
	
	public Optional<TipoUsuario> executar(Integer id) {
		return tipoUsuarioGateway.buscarPorId(id);
	}
}
