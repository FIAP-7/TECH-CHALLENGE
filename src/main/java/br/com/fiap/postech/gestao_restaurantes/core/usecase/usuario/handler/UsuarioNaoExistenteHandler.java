package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;


public class UsuarioNaoExistenteHandler extends UsuarioHandler {

    private final IUsuarioGateway usuarioGateway;

    public UsuarioNaoExistenteHandler(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Boolean handle(Usuario usuario) {
        usuarioGateway.buscarPorLogin(usuario.getLogin())
                .orElseThrow(UsuarioExistenteException::new);

        return next.handle(usuario);
    }
}
