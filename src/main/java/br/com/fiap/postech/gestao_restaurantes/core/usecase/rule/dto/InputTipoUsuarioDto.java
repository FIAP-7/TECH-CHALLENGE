package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto;

import br.com.fiap.postech.gestao_restaurantes.infra.domain.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputTipoUsuarioDto {

    private TipoUsuario novoTipoUsuario;
}
