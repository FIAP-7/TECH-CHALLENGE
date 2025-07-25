package br.com.fiap.postech.gestao_restaurantes.domain;

import br.com.fiap.postech.gestao_restaurantes.controller.json.TipoUsuarioJson;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TipoUsuario {
	
	private Integer id;
	private String nome;
	
	public TipoUsuarioJson mapToJson(){
        return new TipoUsuarioJson(
                id,
                nome
        );
    }
}
