package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

public class PasswordValidaHandler extends PasswordHandler {

    private final IUsuarioGateway usuarioGateway;

    public PasswordValidaHandler(final IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Boolean handle(Long id, String novaSenha) {
        Usuario usuario = this.usuarioGateway.buscarPorId(id);

        usuario.setSenha(novaSenha);

        return next.handle(id, novaSenha);
    }
}
