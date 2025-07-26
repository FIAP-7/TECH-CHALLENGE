package br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.criarTipoUsuarioRule;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.OutputDto;
import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.infra.domain.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioMesmoNomeExistenteException;
import br.com.fiap.postech.gestao_restaurantes.infra.gateway.TipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.rule.dto.InputTipoUsuarioDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
//@RequiredArgsConstructor
public class CriarTipoUsuarioCadastradoRule implements CriarTipoUsuarioRuleBase {

    private final TipoUsuarioGateway tipoUsuarioGateway;

    public CriarTipoUsuarioCadastradoRule(TipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @Override
    public Optional<OutputDto> validate(InputTipoUsuarioDto inputTipoUsuarioDto) {
        TipoUsuario novoTipoUsuario = inputTipoUsuarioDto.getNovoTipoUsuario();

        Optional<TipoUsuario> tipoUsuario = tipoUsuarioGateway.buscarPorNome(novoTipoUsuario.getNome());

        if(tipoUsuario.isPresent()) {
            log.warn("Tipo de usuário já existe com o nome informado. {}", novoTipoUsuario.getNome());
            throw new TipoUsuarioMesmoNomeExistenteException();
        }

        return Optional.empty();
    }
}
