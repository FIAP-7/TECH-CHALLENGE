package br.com.fiap.postech.gestao_restaurantes.core.usecase.validarLogin;

import br.com.fiap.postech.gestao_restaurantes.core.dto.CredenciaisDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Credenciais;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.validarLogin.handler.LoginExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.validarLogin.handler.SenhaCorretaHandler;


public class AutenticarUsuarioUsecase {

    private final IUsuarioGateway usuarioGateway;

    private AutenticarUsuarioUsecase(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static AutenticarUsuarioUsecase create(IUsuarioGateway usuarioGateway) {
        return new AutenticarUsuarioUsecase(usuarioGateway);
    }

    public Boolean executar(CredenciaisDTO credenciaisDTO) {
        Credenciais credenciais = Credenciais.create(credenciaisDTO.login(), credenciaisDTO.senha());

        var senhaCorretaHandler = new SenhaCorretaHandler();
        var loginExistenteHandler = new LoginExistenteHandler(usuarioGateway);

        loginExistenteHandler.setNext(senhaCorretaHandler);

        return loginExistenteHandler.handle(credenciais);
    }
}
