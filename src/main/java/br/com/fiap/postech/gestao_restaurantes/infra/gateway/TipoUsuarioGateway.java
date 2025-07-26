package br.com.fiap.postech.gestao_restaurantes.infra.gateway;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.infra.domain.TipoUsuario;

@Deprecated
public interface TipoUsuarioGateway {

    public Long criar(TipoUsuario tipoUsuario);
    
    public void deletar(Long id);

    public Optional<TipoUsuario> buscarPorId(Long id);

	void atualizar(Long id, TipoUsuario tipoUsuario);

	public Optional<TipoUsuario> buscarPorNome(String nome);
}
