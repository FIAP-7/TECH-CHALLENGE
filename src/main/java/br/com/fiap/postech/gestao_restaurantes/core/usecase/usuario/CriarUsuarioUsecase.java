package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.UsuarioNaoExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.UsuarioTipoUsuarioExistenteHandler;

public class CriarUsuarioUsecase {

    private final IUsuarioGateway usuarioGateway;
    private final ITipoUsuarioGateway tipoUsuarioGateway;

    private CriarUsuarioUsecase(final IUsuarioGateway usuarioGateway, final ITipoUsuarioGateway tipoUsuarioGateway) {
        this.usuarioGateway = usuarioGateway;
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public static CriarUsuarioUsecase create(final IUsuarioGateway usuarioGateway, final ITipoUsuarioGateway tipoUsuarioGateway) {
        return new CriarUsuarioUsecase(usuarioGateway, tipoUsuarioGateway);
    }

    public Long executar(NovoUsuarioDTO novoUsuarioDTO) {

        TipoUsuarioDTO tipoUsuarioDTO = novoUsuarioDTO.tipoUsuario();

        TipoUsuario tipoUsuario = TipoUsuario.create(
                tipoUsuarioDTO.id(),
                tipoUsuarioDTO.nome()
        );

        EnderecoDTO enderecoDTO = novoUsuarioDTO.endereco();

        Endereco endereco = Endereco.create(
                enderecoDTO.logradouro(),
                enderecoDTO.numero(),
                enderecoDTO.complemento(),
                enderecoDTO.bairro(),
                enderecoDTO.cidade(),
                enderecoDTO.estado(),
                enderecoDTO.cep()
        );

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

        validaRegras(usuario);

        return usuarioGateway.criar(usuario);
    }

    private Boolean validaRegras(Usuario usuario) {
        var usuarioExistenteHandler = new UsuarioNaoExistenteHandler(usuarioGateway);
        var usuarioTipoUsuarioExistenteHandler = new UsuarioTipoUsuarioExistenteHandler(tipoUsuarioGateway);

        usuarioExistenteHandler.setNext(usuarioTipoUsuarioExistenteHandler);

        return usuarioExistenteHandler.handle(usuario);
    }


}
