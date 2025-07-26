package br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EnderecoNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;

import java.util.Optional;

public interface IUsuarioGateway {

    Usuario buscarPorId(Long id);

    Optional<Usuario> buscarPorLogin(String login) throws UsuarioNaoEncontradoException, TipoUsuarioNaoEncontradoException, EnderecoNaoEncontradoException;

    Long criar(Usuario usuario);

    void atualizar(Long id, Usuario usuario);

    void atualizarSenha(Long id, String novaSenha);

    void deletar(Long id);

}
