package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.DadoInvalidoUsuarioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

public class UsuarioTipoUsuarioExistenteHandler extends UsuarioHandler {

    private final ITipoUsuarioGateway tipoUsuarioGateway;

    public UsuarioTipoUsuarioExistenteHandler(final ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @Override
    public Boolean handle(Usuario usuario) {

        if (usuario.getTipoUsuario() == null || usuario.getTipoUsuario().getId() == null || usuario.getTipoUsuario().getId() < 0) {
            throw new DadoInvalidoUsuarioException("Informe um valor válido para o tipo de usuário!");
        }

        tipoUsuarioGateway.buscarPorId(usuario.getTipoUsuario().getId()).orElseThrow(TipoUsuarioNaoEncontradoException::new);

        return next.handle(usuario);
    }
}
