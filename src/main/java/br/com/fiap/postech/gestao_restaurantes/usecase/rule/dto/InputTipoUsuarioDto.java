package br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto;

import br.com.fiap.postech.gestao_restaurantes.domain.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputTipoUsuarioDto {

    private TipoUsuario novoTipoUsuario;
}
