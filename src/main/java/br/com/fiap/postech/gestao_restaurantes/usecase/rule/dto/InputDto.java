package br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputDto {

    private Usuario novoUsuario;
}
