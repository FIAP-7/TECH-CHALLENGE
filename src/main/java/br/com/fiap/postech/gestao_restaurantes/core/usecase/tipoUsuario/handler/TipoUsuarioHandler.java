package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;

public abstract class TipoUsuarioHandler {

    protected TipoUsuarioHandler next;

    public TipoUsuarioHandler setNext(TipoUsuarioHandler next) {
        this.next = next;
        return next;
    }

    public abstract Boolean handle(TipoUsuario tipoUsuario);

}
