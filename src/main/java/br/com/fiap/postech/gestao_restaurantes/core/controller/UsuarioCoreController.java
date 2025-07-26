package br.com.fiap.postech.gestao_restaurantes.core.controller;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EnderecoNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IUsuarioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.UsuarioPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.*;

public class UsuarioCoreController {

    private final IUsuarioDataSource dataSource;

    private UsuarioCoreController(IUsuarioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UsuarioCoreController create(IUsuarioDataSource dataSource) {
        return new UsuarioCoreController(dataSource);
    }

    public UsuarioDTO incluir(NovoUsuarioDTO novoUsuarioDTO) {
        IUsuarioGateway usuarioGateway = UsuarioGateway.create(this.dataSource);
        CriarUsuarioUsecase criarUsuarioUsecase = CriarUsuarioUsecase.create(usuarioGateway);

        Long idUsuario = criarUsuarioUsecase.executar(novoUsuarioDTO);

        return null;
    }

    public UsuarioDTO buscarPorId(Long id) {
        IUsuarioGateway usuarioGateway = UsuarioGateway.create(this.dataSource);
        ConsultarUsuarioUseCase consultarUsuarioUseCase = ConsultarUsuarioUseCase.create(usuarioGateway);

        try {
            Usuario usuario = consultarUsuarioUseCase.executar(id);

            return UsuarioPresenter.toDTO(usuario);
        }catch (UsuarioNaoEncontradoException | EnderecoNaoEncontradoException | TipoUsuarioNaoEncontradoException e){
            return null;
        }
    }

    public UsuarioDTO alterar(Long id, UsuarioDTO usuarioDTO) {
        IUsuarioGateway usuarioGateway = UsuarioGateway.create(this.dataSource);
        AtualizarUsuarioUseCase atualizarUsuarioUseCase = AtualizarUsuarioUseCase.create(usuarioGateway);

        atualizarUsuarioUseCase.executar(id, usuarioDTO);

        return null;
    }

    public void alterarSenha(Long id, String senha) {
        IUsuarioGateway usuarioGateway = UsuarioGateway.create(this.dataSource);
        AtualizarSenhaUsuarioUseCase atualizarSenhaUsuarioUseCase = AtualizarSenhaUsuarioUseCase.create(usuarioGateway);

        atualizarSenhaUsuarioUseCase.executar(id, senha);
    }

    public void excluir(Long id) {
        IUsuarioGateway usuarioGateway = UsuarioGateway.create(this.dataSource);
        DeletarUsuarioUsecase deletarUsuarioUsecase = DeletarUsuarioUsecase.create(usuarioGateway);

        deletarUsuarioUsecase.executar(id);
    }

}
