package br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;

import java.util.Optional;

public interface ITipoUsuarioDataSource {

    Long criar(NovoTipoUsuarioDTO tipoUsuario);

    void deletar(Long id);

    Optional<TipoUsuarioDTO> buscarPorId(Long id);

    void atualizar(Long id, TipoUsuarioDTO tipoUsuario);

    Optional<TipoUsuarioDTO> buscarPorNome(String nome);
}
