package br.com.fiap.postech.gestao_restaurantes.infra.controller.json;


import java.time.LocalDateTime;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.infra.domain.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representação de um usuário")
public class UsuarioJson {

    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "CPF do usuário", example = "123.456.789-00", required = true)
    @Pattern(regexp = "^\\d{3}.\\d{3}.\\d{3}-\\d{2}", message = "CPF necessita de 14 caracteres no formato (123.456.789-00)")
    private String cpf;

    @NotBlank
    @Schema(description = "Nome completo", example = "João da Silva", required = true)
    private String nome;

    @NotBlank
    @Schema(description = "Email", example = "joao.silva@email.com", required = true)
    @Email
    private String email;

    @NotBlank
    @Schema(description = "Login de acesso", example = "joaosilva", required = true)
    private String login;

    @NotBlank
    @Schema(description = "Senha de acesso", example = "senhaForte123", required = true)
    @Size(min = 8, message = "Senha necessita de 8 caracteres ou mais")
    private String senha;

    @NotNull
    @Schema(description = "Tipo de usuário", required = true)
    private TipoUsuarioJson tipoUsuario;

    @Schema(description = "Endereço do usuário")
    @Valid
    private EnderecoJson endereco;

    private LocalDateTime dataUltimaAlteracao;

    public NovoUsuarioDTO mapToNovoUsuarioDTO(){
        return new NovoUsuarioDTO(
                cpf,
                nome,
                email,
                login,
                senha,
                LocalDateTime.now(),
                tipoUsuario.mapToDTO(),
                endereco.mapToDTO()
        );
    }


    public UsuarioDTO mapToUsuarioDTO(){
        return new UsuarioDTO(
                id,
                cpf,
                nome,
                email,
                login,
                senha,
                LocalDateTime.now(),
                tipoUsuario.mapToDTO(),
                endereco.mapToDTO()
        );
    }
}
