package br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.handler;

import br.com.fiap.postech.gestao_restaurantes.domain.Credenciais;
import br.com.fiap.postech.gestao_restaurantes.exception.SenhaInvalidaException;
import java.util.Objects;

public class SenhaCorretaHandler extends AutenticarUsuarioHandler {

    @Override
    public Boolean handle(Credenciais credenciais) {
        if (!Objects.equals(credenciais.getSenha(), credenciais.getUsuario().getSenha())) {
            throw new SenhaInvalidaException();
        }
        return true;
    }
}
