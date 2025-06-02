package br.com.fiap.postech.gestao_restaurantes.usecase;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.criarUsuarioRule.CriarUsuarioRuleBase;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.dto.InputDto;
import br.com.fiap.postech.gestao_restaurantes.usecase.rule.usuarioRule.UsuarioRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CriarUsuarioUsecase {

    private final UsuarioGateway usuarioGateway;
    private final List<CriarUsuarioRuleBase> rules;
    private final List<UsuarioRule> rulesUsuario;

    public Long criar(Usuario usuario) {
        validaRegras(usuario);

        return usuarioGateway.criar(usuario);
    }

    private void validaRegras(Usuario usuario) {
        var inputDto = new InputDto(usuario);
        rules.forEach(rule -> rule.validate(inputDto));
        rulesUsuario.forEach(rule -> rule.validate(inputDto));
    }


}
