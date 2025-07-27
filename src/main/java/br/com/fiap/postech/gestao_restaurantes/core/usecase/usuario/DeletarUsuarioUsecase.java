package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

public class DeletarUsuarioUsecase {

    private final IUsuarioGateway usuarioGateway;

    private DeletarUsuarioUsecase(IUsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public static DeletarUsuarioUsecase create(final IUsuarioGateway usuarioGateway) {
        return new DeletarUsuarioUsecase(usuarioGateway);
    }

    public void executar(Long id) {
        final Usuario usuario = this.usuarioGateway.buscarPorId(id);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException();
        }

        this.usuarioGateway.deletar(id);
    }
}