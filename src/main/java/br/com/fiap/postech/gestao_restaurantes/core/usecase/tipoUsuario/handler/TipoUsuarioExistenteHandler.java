package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

import java.util.Optional;

public class TipoUsuarioExistenteHandler extends TipoUsuarioHandler {

    private final ITipoUsuarioGateway tipoUsuarioGateway;

    public TipoUsuarioExistenteHandler(final ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @Override
    public Boolean handle(TipoUsuario tipoUsuario) {
        tipoUsuarioGateway.buscarPorId(tipoUsuario.getId())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        return next.handle(tipoUsuario);
    }
}
