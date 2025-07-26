package br.com.fiap.postech.gestao_restaurantes.core.controller;

import br.com.fiap.postech.gestao_restaurantes.core.dto.CredenciaisDTO;
import br.com.fiap.postech.gestao_restaurantes.core.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IUsuarioDataSource;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.validarLogin.AutenticarUsuarioUsecase;

public class AutenticarCoreController {

    private final IUsuarioDataSource dataSource;

    private AutenticarCoreController(IUsuarioDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static AutenticarCoreController create(IUsuarioDataSource dataSource) {
        return new AutenticarCoreController(dataSource);
    }

    public boolean autenticar(CredenciaisDTO credenciaisDTO) {
        IUsuarioGateway usuarioGateway = UsuarioGateway.create(dataSource);
        AutenticarUsuarioUsecase autenticarUsuarioUsecase = AutenticarUsuarioUsecase.create(usuarioGateway);

        return autenticarUsuarioUsecase.executar(credenciaisDTO);
    }
}
