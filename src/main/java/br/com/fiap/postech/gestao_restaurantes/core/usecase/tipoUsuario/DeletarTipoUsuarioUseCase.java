package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

import java.util.Optional;

public class DeletarTipoUsuarioUseCase {

    private final ITipoUsuarioGateway tipoUsuarioGateway;

    private DeletarTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public static DeletarTipoUsuarioUseCase create(ITipoUsuarioGateway tipoUsuarioGateway) {
        return new DeletarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    public void executar(Long id) {
        Optional<TipoUsuario> tipoUsuario = this.tipoUsuarioGateway.buscarPorId(id);

        if (tipoUsuario.isEmpty()) {
            throw new TipoUsuarioNaoEncontradoException();
        }

        tipoUsuarioGateway.deletar(id);
    }
}