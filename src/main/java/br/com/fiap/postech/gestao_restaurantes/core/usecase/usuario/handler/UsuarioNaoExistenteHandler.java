package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

import java.util.Optional;


public class UsuarioNaoExistenteHandler extends UsuarioHandler {

    private final IUsuarioGateway usuarioGateway;

    public UsuarioNaoExistenteHandler(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Boolean handle(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioGateway.buscarPorLogin(usuario.getLogin());

        if (usuarioExistente.isPresent()) {
            throw new UsuarioExistenteException();
        }

        if(next != null){
            return next.handle(usuario);
        } else {
            return true;
        }
    }
}
