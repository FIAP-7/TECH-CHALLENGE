package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioMesmoNomeExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

import br.com.fiap.postech.gestao_restaurantes.core.presenters.TipoUsuarioPresenter;
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
        //validaRegras(tipoUsuario);

        Optional<TipoUsuario> tipoUsuario = this.tipoUsuarioGateway.buscarPorNome(novoTipoUsuarioDTO.nome());

        if (tipoUsuario.isPresent()) {
            throw new TipoUsuarioMesmoNomeExistenteException();
        }

        TipoUsuario entity = TipoUsuarioPresenter.toEntity(novoTipoUsuarioDTO);

        return tipoUsuarioGateway.criar(entity);
    }

    /*
    private void validaRegras(TipoUsuario tipoUsuario) {
        var inputTipoUsuarioDto = new InputTipoUsuarioDto(tipoUsuario);
        rules.forEach(rule -> rule.validate(inputTipoUsuarioDto));
        rulesTipoUsuario.forEach(rule -> rule.validate(inputTipoUsuarioDto));
    }
     */
}