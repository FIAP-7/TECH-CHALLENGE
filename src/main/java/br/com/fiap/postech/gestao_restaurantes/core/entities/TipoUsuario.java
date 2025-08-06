package br.com.fiap.postech.gestao_restaurantes.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TipoUsuario {

    private Long id;
    private String nome;


    public static TipoUsuario create(String nome) {
        if(nome == null) {
            throw new IllegalArgumentException("Faltam dados");
        }

        TipoUsuario tipoUsuario = new TipoUsuario();

        tipoUsuario.setNome(nome);

        return tipoUsuario;
    }

    public static TipoUsuario create(Long id, String nome) {
        if(id == null || nome == null) {
            //throw new IllegalArgumentException("Faltam dados");
        }

        TipoUsuario tipoUsuario = new TipoUsuario();

        tipoUsuario.setId(id);
        tipoUsuario.setNome(nome);

        return tipoUsuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
