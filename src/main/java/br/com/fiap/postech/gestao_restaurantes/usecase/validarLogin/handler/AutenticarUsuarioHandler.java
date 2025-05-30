package br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.handler;

import br.com.fiap.postech.gestao_restaurantes.domain.Credenciais;

public abstract class AutenticarUsuarioHandler {

    protected AutenticarUsuarioHandler next;

    public AutenticarUsuarioHandler setNext(AutenticarUsuarioHandler next) {
        this.next = next;
        return next;
    }

    public abstract Boolean handle(Credenciais credenciais);
}
