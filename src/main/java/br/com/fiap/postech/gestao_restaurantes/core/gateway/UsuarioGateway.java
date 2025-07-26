package br.com.fiap.postech.gestao_restaurantes.core.gateway;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EnderecoNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IUsuarioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.EnderecoPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.TipoUsuarioPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.UsuarioPresenter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class UsuarioGateway implements IUsuarioGateway {

    private final IUsuarioDataSource dataSource;

    private UsuarioGateway(IUsuarioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static IUsuarioGateway create(IUsuarioDataSource dataSource) {
        return new UsuarioGateway(dataSource);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        final UsuarioDTO usuarioDTO = this.dataSource.buscarPorId(id);

        return getUsuario(usuarioDTO);
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) throws UsuarioNaoEncontradoException, TipoUsuarioNaoEncontradoException, EnderecoNaoEncontradoException {
        final UsuarioDTO usuarioDTO = this.dataSource.buscarPorLogin(login);

        return Optional.ofNullable(getUsuario(usuarioDTO));
    }


    @Override
    public Long criar(Usuario usuario) {
        NovoUsuarioDTO novoUsuarioDTO = UsuarioPresenter.toNovoUsuarioDTO(usuario);

        return this.dataSource.criar(novoUsuarioDTO);
    }

    @Override
    public void atualizar(Long id, Usuario usuario) {
        UsuarioDTO usuarioDTO = this.dataSource.buscarPorId(id);

        if (usuarioDTO == null) {
            throw new UsuarioNaoEncontradoException();
        }

        this.dataSource.atualizar(id, usuarioDTO);
    }

    @Override
    public void atualizarSenha(Long id, String novaSenha) {
        final UsuarioDTO usuarioDTO = this.dataSource.buscarPorId(id);

        Usuario usuario = getUsuario(usuarioDTO);

        usuario.setSenha(novaSenha);

        this.dataSource.atualizarSenha(id, usuario.getSenha());
    }

    @Override
    public void deletar(Long id) {
        UsuarioDTO usuarioDTO = this.dataSource.buscarPorId(id);

        if (usuarioDTO == null) {
            throw new UsuarioNaoEncontradoException();
        }

        this.dataSource.deletar(id);
    }


    private static Usuario getUsuario(UsuarioDTO usuarioDTO) {
        if(usuarioDTO == null){
            return null;
            //throw new UsuarioNaoEncontradoException();
        }

        if(usuarioDTO.tipoUsuario() == null){
            throw new TipoUsuarioNaoEncontradoException();
        }

        TipoUsuario tipoUsuario = TipoUsuarioPresenter.toEntity(usuarioDTO.tipoUsuario());

        if(usuarioDTO.endereco() == null){
            throw new EnderecoNaoEncontradoException();
        }

        Endereco endereco = EnderecoPresenter.toEntity(usuarioDTO.endereco());

        return UsuarioPresenter.toEntity(usuarioDTO, tipoUsuario, endereco);
    }

}
