package br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin;

import br.com.fiap.postech.gestao_restaurantes.domain.Credenciais;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.handler.LoginExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.handler.SenhaCorretaHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AutenticarUsuarioUsecaseImpl implements AutenticarUsuarioUsecase {

    private final UsuarioGateway usuarioGateway;

    public Boolean executar(Credenciais credenciais) {
        var senhaCorretaHandler = new SenhaCorretaHandler();
        var loginExistenteHandler = new LoginExistenteHandler(usuarioGateway);

        loginExistenteHandler.setNext(senhaCorretaHandler);

        return loginExistenteHandler.handle(credenciais);
    }
}
