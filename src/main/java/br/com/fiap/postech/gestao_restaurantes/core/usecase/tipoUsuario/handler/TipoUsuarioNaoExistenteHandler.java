package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioMesmoNomeExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

public class TipoUsuarioNaoExistenteHandler extends TipoUsuarioHandler{

    private ITipoUsuarioGateway tipoUsuarioGateway;

    public TipoUsuarioNaoExistenteHandler(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @Override
    public Boolean handle(TipoUsuario tipoUsuario) {
        tipoUsuarioGateway.buscarPorNome(tipoUsuario.getNome())
                .orElseThrow(TipoUsuarioMesmoNomeExistenteException::new);

        return next.handle(tipoUsuario);
    }
}
