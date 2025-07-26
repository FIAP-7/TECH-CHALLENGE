package br.com.fiap.postech.gestao_restaurantes.core.controller;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EnderecoNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.TipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.ITipoUsuarioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.presenters.TipoUsuarioPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.AtualizarTipoUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.ConsultarTipoUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.CriarTipoUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.DeletarTipoUsuarioUseCase;

import java.util.Optional;

public class TipoUsuarioCoreController {

    private final ITipoUsuarioDataSource dataSource;

    private TipoUsuarioCoreController(ITipoUsuarioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static TipoUsuarioCoreController create(ITipoUsuarioDataSource dataSource) {
        return new TipoUsuarioCoreController(dataSource);
    }

    public TipoUsuarioDTO incluir(NovoTipoUsuarioDTO novoTipoUsuarioDTO) {
        ITipoUsuarioGateway tipoUsuarioGateway = TipoUsuarioGateway.create(this.dataSource);
        CriarTipoUsuarioUseCase criarTipoUsuarioUseCase = CriarTipoUsuarioUseCase.create(tipoUsuarioGateway);

        criarTipoUsuarioUseCase.executar(novoTipoUsuarioDTO);

        return null;
    }

    public TipoUsuarioDTO buscarPorId(Long id) {
        ITipoUsuarioGateway tipoUsuarioGateway = TipoUsuarioGateway.create(this.dataSource);
        ConsultarTipoUsuarioUseCase consultarTipoUsuarioUseCase = ConsultarTipoUsuarioUseCase.create(tipoUsuarioGateway);

        try {
            Optional<TipoUsuario> executar = consultarTipoUsuarioUseCase.executar(id);

            return executar.map(TipoUsuarioPresenter::toDTO).orElse(null);

        }catch (UsuarioNaoEncontradoException | EnderecoNaoEncontradoException | TipoUsuarioNaoEncontradoException e){
            return null;
        }
    }

    public TipoUsuarioDTO alterar(Long id, TipoUsuarioDTO tipoUsuarioDTO) {
        ITipoUsuarioGateway tipoUsuarioGateway = TipoUsuarioGateway.create(this.dataSource);
        AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase = AtualizarTipoUsuarioUseCase.create(tipoUsuarioGateway);

        atualizarTipoUsuarioUseCase.executar(id, tipoUsuarioDTO);

        return null;
    }

    public void excluir(Long id) {
        ITipoUsuarioGateway tipoUsuarioGateway = TipoUsuarioGateway.create(this.dataSource);
        DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase = DeletarTipoUsuarioUseCase.create(tipoUsuarioGateway);

        deletarTipoUsuarioUseCase.executar(id);
    }
}
