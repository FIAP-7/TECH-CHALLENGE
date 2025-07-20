package br.com.fiap.postech.gestao_restaurantes.usecase;

import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.gateway.TipoUsuarioGateway;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeletarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public void executar(Integer id) {
    	tipoUsuarioGateway.deletar(id);
    }
}