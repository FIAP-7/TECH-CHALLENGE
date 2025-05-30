package br.com.fiap.postech.gestao_restaurantes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Credenciais {

    private final String login;
    private final String senha;
    @Setter
    private Usuario usuario;

    public Credenciais(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
