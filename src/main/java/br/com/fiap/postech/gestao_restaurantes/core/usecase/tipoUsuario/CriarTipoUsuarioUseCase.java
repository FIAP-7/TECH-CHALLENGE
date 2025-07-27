package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioMesmoNomeExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

import br.com.fiap.postech.gestao_restaurantes.core.presenters.TipoUsuarioPresenter;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler.TipoUsuarioNaoExistenteHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class CriarTipoUsuarioUseCase {

    private final ITipoUsuarioGateway tipoUsuarioGateway;

    private CriarTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public static CriarTipoUsuarioUseCase create(ITipoUsuarioGateway tipoUsuarioGateway) {
        return new CriarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    public Long executar(NovoTipoUsuarioDTO novoTipoUsuarioDTO) {
        TipoUsuario entity = TipoUsuario.create(novoTipoUsuarioDTO.nome());

        validaRegras(entity);

        return tipoUsuarioGateway.criar(entity);
    }

    private Boolean validaRegras(TipoUsuario tipoUsuario) {
        var tipoUsuarioNaoExistente = new TipoUsuarioNaoExistenteHandler(this.tipoUsuarioGateway);

        return tipoUsuarioNaoExistente.handle(tipoUsuario);
    }
}