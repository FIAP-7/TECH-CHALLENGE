package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.EnderecoPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.TipoUsuarioPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.UsuarioPresenter;

import java.util.Optional;

public class CriarUsuarioUsecase {

    private final IUsuarioGateway usuarioGateway;

    private CriarUsuarioUsecase(final IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static CriarUsuarioUsecase create(final IUsuarioGateway usuarioGateway) {
        return new CriarUsuarioUsecase(usuarioGateway);
    }

    public Long executar(NovoUsuarioDTO novoUsuarioDTO) {
        //validaRegras(usuario);
        Optional<Usuario> usuarioExistenteOptional = this.usuarioGateway.buscarPorLogin(novoUsuarioDTO.login());

        if (usuarioExistenteOptional.isPresent()) {
            throw new UsuarioExistenteException();
        }

        TipoUsuarioDTO tipoUsuarioDTO = novoUsuarioDTO.tipoUsuario();

        TipoUsuario tipoUsuario = TipoUsuarioPresenter.toEntity(tipoUsuarioDTO);

        /*
        TipoUsuario tipoUsuario = TipoUsuario.create(
                tipoUsuarioDTO.id(),
                tipoUsuarioDTO.nome()
        );
        */

        EnderecoDTO enderecoDTO = novoUsuarioDTO.endereco();

        Endereco endereco = EnderecoPresenter.toEntity(enderecoDTO);

        /*
        Endereco endereco = Endereco.create(
                enderecoDTO.logradouro(),
                enderecoDTO.numero(),
                enderecoDTO.complemento(),
                enderecoDTO.bairro(),
                enderecoDTO.cidade(),
                enderecoDTO.estado(),
                enderecoDTO.cep()
        );

         */

        Usuario usuario = UsuarioPresenter.toEntity(novoUsuarioDTO, tipoUsuario, endereco);

        /*
        Usuario usuario = Usuario.create(
                novoUsuarioDTO.cpf(),
                novoUsuarioDTO.nome(),
                novoUsuarioDTO.email(),
                novoUsuarioDTO.login(),
                novoUsuarioDTO.senha(),
                novoUsuarioDTO.dataUltimaAlteracao(),
                tipoUsuario,
                endereco
        );
         */

        return usuarioGateway.criar(usuario);
    }

    /*
    private void validaRegras(Usuario usuario) {
        var inputDto = new InputDto(usuario);
        rules.forEach(rule -> rule.validate(inputDto));
        rulesUsuario.forEach(rule -> rule.validate(inputDto));
    }
     */


}
