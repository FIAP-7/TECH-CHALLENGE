package br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputPasswordDTO {

    private Long id;
    private String password;

}
