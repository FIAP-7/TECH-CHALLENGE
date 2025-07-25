package br.com.fiap.postech.gestao_restaurantes.gateway;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.domain.TipoUsuario;

public interface TipoUsuarioGateway {

    public Integer criar(TipoUsuario tipoUsuario);
    
    public void deletar(Integer id); 

    public Optional<TipoUsuario> buscarPorId(Integer id);

	void atualizar(Integer id, TipoUsuario tipoUsuario);

	public Optional<TipoUsuario> buscarPorNome(String nome);
}
