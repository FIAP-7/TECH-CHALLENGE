package br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway;

public interface IAutenticarGateway {

    boolean autenticar(String email, String senha);
}
