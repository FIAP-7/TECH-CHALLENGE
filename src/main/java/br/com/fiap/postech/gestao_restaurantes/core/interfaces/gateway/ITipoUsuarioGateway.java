package br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;

import java.util.Optional;

public interface ITipoUsuarioGateway {

    public Long criar(TipoUsuario tipoUsuario);

    public void deletar(Long id);

    public Optional<TipoUsuario> buscarPorId(Long id);

    void atualizar(Long id, TipoUsuario tipoUsuario);

    public Optional<TipoUsuario> buscarPorNome(String nome);
}
