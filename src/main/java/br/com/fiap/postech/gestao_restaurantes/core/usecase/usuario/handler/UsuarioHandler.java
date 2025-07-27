package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;

public abstract class UsuarioHandler {

    protected UsuarioHandler next;

    public UsuarioHandler setNext(UsuarioHandler next) {
        this.next = next;
        return next;
    }

    public abstract Boolean handle(Usuario usuario);

}
