package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

public class CadastroExistenteHandler extends UsuarioHandler{

    private final IUsuarioGateway usuarioGateway;

    public CadastroExistenteHandler(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Boolean handle(Usuario usuario) {

        Usuario usuarioExistente = usuarioGateway.buscarPorId(usuario.getId());

        if (usuarioExistente == null) {
            throw new UsuarioNaoEncontradoException();
        }

        if(next != null){
            return next.handle(usuario);
        } else {
            return true;
        }
    }
}
