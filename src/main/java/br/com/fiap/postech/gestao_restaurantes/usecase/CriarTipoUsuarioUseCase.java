package br.com.fiap.postech.gestao_restaurantes.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.postech.gestao_restaurantes.domain.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.gateway.TipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.criarTipoUsuarioRule.CriarTipoUsuarioRuleBase;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputTipoUsuarioDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.tipoUsuarioRule.TipoUsuarioRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriarTipoUsuarioUseCase {

    private final TipoUsuarioGateway tipoUsuarioGateway;
    private final List<CriarTipoUsuarioRuleBase> rules;
    private final List<TipoUsuarioRule> rulesTipoUsuario;

    public Integer criar(TipoUsuario tipoUsuario) {
        validaRegras(tipoUsuario);

        return tipoUsuarioGateway.criar(tipoUsuario);
    }

    private void validaRegras(TipoUsuario tipoUsuario) {
        var inputTipoUsuarioDto = new InputTipoUsuarioDto(tipoUsuario);
        rules.forEach(rule -> rule.validate(inputTipoUsuarioDto));
        rulesTipoUsuario.forEach(rule -> rule.validate(inputTipoUsuarioDto));
    }
}