package br.com.fiap.postech.gestao_restaurantes.gateway;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;

import java.util.Optional;

public interface UsuarioGateway {

    public Long criar(Usuario usuario);

    public Optional<Usuario> buscarPorLogin(String login);
}
