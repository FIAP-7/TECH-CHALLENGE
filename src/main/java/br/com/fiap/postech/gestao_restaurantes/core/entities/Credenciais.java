package br.com.fiap.postech.gestao_restaurantes.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class Credenciais {

    private final String login;
    private final String senha;
    @Setter
    private Usuario usuario;


    public static Credenciais create(String login, String senha) {
        if(login == null || login.isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Faltam dados");
        }

        return new Credenciais(login, senha);
    }

    public Credenciais(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
