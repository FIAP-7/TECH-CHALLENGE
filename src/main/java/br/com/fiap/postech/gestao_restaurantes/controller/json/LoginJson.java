package br.com.fiap.postech.gestao_restaurantes.controller.json;


import br.com.fiap.postech.gestao_restaurantes.domain.Credenciais;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para autenticação de usuário")
public class LoginJson {

    @NotBlank
    @Schema(description = "Login ou email do usuário", example = "usuario@exemplo.com", required = true)
    private String login;

    @NotBlank
    @Schema(description = "Senha do usuário", example = "senhaSegura123", required = true)
    private String senha;

    public Credenciais mapToDomain(){
        return new Credenciais(
                login,
                senha
        );
    }
}
