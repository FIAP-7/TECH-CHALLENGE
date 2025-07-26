package br.com.fiap.postech.gestao_restaurantes.infra.domain;

import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.TipoUsuarioJson;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TipoUsuario {
	
	private Long id;
	private String nome;
	
	public TipoUsuarioJson mapToJson(){
        return new TipoUsuarioJson(
                id,
                nome
        );
    }
}
