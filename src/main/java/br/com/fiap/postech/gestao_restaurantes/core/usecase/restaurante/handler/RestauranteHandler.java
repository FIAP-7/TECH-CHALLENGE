package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;

public abstract class RestauranteHandler {

    protected RestauranteHandler next;

    public RestauranteHandler setNext(RestauranteHandler next) {
        this.next = next;
        return next;
    }

    public abstract Boolean handle(Restaurante restaurante);

}
