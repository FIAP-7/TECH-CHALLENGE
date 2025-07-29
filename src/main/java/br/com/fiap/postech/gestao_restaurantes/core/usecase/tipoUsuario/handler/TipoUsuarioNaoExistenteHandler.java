package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioMesmoNomeExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

import java.util.Optional;

public class TipoUsuarioNaoExistenteHandler extends TipoUsuarioHandler{

    private ITipoUsuarioGateway tipoUsuarioGateway;

    public TipoUsuarioNaoExistenteHandler(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @Override
    public Boolean handle(TipoUsuario tipoUsuario) {
        Optional<TipoUsuario> tipoUsuarioExistente = tipoUsuarioGateway.buscarPorNome(tipoUsuario.getNome());

        if(tipoUsuarioExistente.isPresent()){
            throw new TipoUsuarioMesmoNomeExistenteException();
        }

        if(next != null){
            return next.handle(tipoUsuario);
        } else {
            return true;
        }
    }
}
