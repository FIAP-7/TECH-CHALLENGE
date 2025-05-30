package br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.handler;

import br.com.fiap.postech.gestao_restaurantes.domain.Credenciais;
import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;

public class LoginExistenteHandler extends AutenticarUsuarioHandler {

    private final UsuarioGateway usuarioGateway;

    public LoginExistenteHandler(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Boolean handle(Credenciais credenciais) {
        Usuario usuario = usuarioGateway
                .buscarPorLogin(credenciais.getLogin())
                .orElseThrow(UsuarioNaoEncontradoException::new);

        credenciais.setUsuario(usuario);
        return next.handle(credenciais);
    }
}
