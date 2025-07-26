package br.com.fiap.postech.gestao_restaurantes.infra.domain;

import java.time.LocalDateTime;

import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.EnderecoJson;
import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.TipoUsuarioJson;
import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.UsuarioJson;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Usuario {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAlteracao;
    private TipoUsuario tipoUsuario;
    private Endereco endereco;

    public UsuarioJson mapToJson(){
        EnderecoJson enderecoJson = new EnderecoJson(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
        
		TipoUsuarioJson tipoUsuarioJson = new TipoUsuarioJson(
				tipoUsuario.getId(), 
				tipoUsuario.getNome()
		);

        return new UsuarioJson(
                id,
                cpf,
                nome,
                email,
                login,
                senha,
                tipoUsuarioJson,
                enderecoJson,
                dataUltimaAlteracao
        );
    }
}
