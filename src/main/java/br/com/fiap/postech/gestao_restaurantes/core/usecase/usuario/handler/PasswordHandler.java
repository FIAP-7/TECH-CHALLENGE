package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler;

public abstract class PasswordHandler {

    protected PasswordHandler next;

    public PasswordHandler setNext(PasswordHandler next) {
        this.next = next;
        return next;
    }

    public abstract Boolean handle(Long id, String novaSenha);

}
