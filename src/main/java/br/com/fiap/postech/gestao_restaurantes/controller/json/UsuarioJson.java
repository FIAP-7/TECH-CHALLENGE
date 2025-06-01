package br.com.fiap.postech.gestao_restaurantes.controller.json;


import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representação de um usuário")
public class UsuarioJson {

    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "CPF do usuário", example = "123.456.789-00", required = true)
    private String cpf;

    @NotBlank
    @Schema(description = "Nome completo", example = "João da Silva", required = true)
    private String nome;

    @NotBlank
    @Schema(description = "Email", example = "joao.silva@email.com", required = true)
    private String email;

    @NotBlank
    @Schema(description = "Login de acesso", example = "joaosilva", required = true)
    private String login;

    @NotBlank
    @Schema(description = "Senha de acesso", example = "senhaForte123", required = true)
    private String senha;

    @Schema(description = "Endereço do usuário")
    private EnderecoJson endereco;

    public Usuario mapToDomain(){
        return new Usuario(
                id,
                cpf,
                nome,
                email,
                login,
                senha,
                LocalDateTime.now(),
                endereco.mapToDomain()
        );
    }
}
