package br.com.fiap.postech.gestao_restaurantes.controller.json;


import br.com.fiap.postech.gestao_restaurantes.domain.Credenciais;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginJson {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    public Credenciais mapToDomain(){
        return new Credenciais(
                login,
                senha
        );
    }
}
