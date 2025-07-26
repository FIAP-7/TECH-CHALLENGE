package br.com.fiap.postech.gestao_restaurantes.core.gateway;


import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.ITipoUsuarioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.TipoUsuarioPresenter;

import java.util.Optional;

public class TipoUsuarioGateway implements ITipoUsuarioGateway {

    private final ITipoUsuarioDataSource datasource;

    private TipoUsuarioGateway(ITipoUsuarioDataSource datasource) {
        this.datasource = datasource;
    }

    public static TipoUsuarioGateway create(ITipoUsuarioDataSource datasource) {
        return new TipoUsuarioGateway(datasource);
    }


    @Override
    public Long criar(TipoUsuario tipoUsuario) {
        NovoTipoUsuarioDTO novoTipoDTO = TipoUsuarioPresenter.toNovoTipoDTO(tipoUsuario);

        return this.datasource.criar(novoTipoDTO);
    }

    @Override
    public void deletar(Long id) {
        Optional<TipoUsuarioDTO> tipoUsuarioDTO = this.datasource.buscarPorId(id);

        if (tipoUsuarioDTO.isEmpty()) {
            throw new TipoUsuarioNaoEncontradoException();
        }

        this.datasource.deletar(id);
    }

    @Override
    public Optional<TipoUsuario> buscarPorId(Long id) {
        Optional<TipoUsuarioDTO> tipoUsuarioDTO = this.datasource.buscarPorId(id);

        return tipoUsuarioDTO.map(TipoUsuarioPresenter::toEntity);

    }

    @Override
    public void atualizar(Long id, TipoUsuario tipoUsuario) {
        Optional<TipoUsuarioDTO> tipoUsuarioDTO = this.datasource.buscarPorId(id);

        if (tipoUsuarioDTO.isEmpty()) {
            throw new TipoUsuarioNaoEncontradoException();
        }

        this.datasource.atualizar(id, tipoUsuarioDTO.get());
    }

    @Override
    public Optional<TipoUsuario> buscarPorNome(String nome) {
        Optional<TipoUsuarioDTO> tipoUsuarioDTO = this.datasource.buscarPorNome(nome);

        return tipoUsuarioDTO.map(TipoUsuarioPresenter::toEntity);

    }
}
