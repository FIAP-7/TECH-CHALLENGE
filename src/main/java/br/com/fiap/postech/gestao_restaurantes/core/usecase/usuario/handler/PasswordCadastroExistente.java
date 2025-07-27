package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

public class PasswordCadastroExistente extends PasswordHandler {

    private final IUsuarioGateway usuarioGateway;

    public PasswordCadastroExistente(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Boolean handle(Long id, String novaSenha) {
        Usuario usuario = usuarioGateway.buscarPorId(id);

        if(usuario == null) {
            throw new UsuarioNaoEncontradoException();
        }

        return next.handle(id, novaSenha);
    }
}
