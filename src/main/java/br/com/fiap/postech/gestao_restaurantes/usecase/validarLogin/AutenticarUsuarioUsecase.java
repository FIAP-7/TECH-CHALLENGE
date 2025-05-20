package br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin;

import br.com.fiap.postech.gestao_restaurantes.domain.Credenciais;

public interface AutenticarUsuarioUsecase {

    Boolean executar(Credenciais credenciais);
}
