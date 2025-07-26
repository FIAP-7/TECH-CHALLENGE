package br.com.fiap.postech.gestao_restaurantes.core.usecase.validarLogin.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Credenciais;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.exception.LoginSenhaInvalidosException;

public class LoginExistenteHandler extends AutenticarUsuarioHandler {

    private final IUsuarioGateway usuarioGateway;

    public LoginExistenteHandler(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Boolean handle(Credenciais credenciais) {
        Usuario usuario = usuarioGateway
                .buscarPorLogin(credenciais.getLogin())
                .orElseThrow(LoginSenhaInvalidosException::new);

        credenciais.setUsuario(usuario);
        return next.handle(credenciais);
    }
}
