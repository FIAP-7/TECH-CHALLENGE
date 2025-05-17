package br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.handler;

import br.com.fiap.postech.gestao_restaurantes.domain.Credenciais;
import br.com.fiap.postech.gestao_restaurantes.exception.CampoVazioException;

public class CampoNaoVazioHandler extends AutenticarUsuarioHandler {

    @Override
    public Boolean handle(Credenciais credenciais) {
        if (credenciais.getLogin() == null || credenciais.getLogin().isBlank()
                || credenciais.getSenha() == null || credenciais.getSenha().isBlank()) {
            throw new CampoVazioException();
        }
        return next.handle(credenciais);
    }
}
