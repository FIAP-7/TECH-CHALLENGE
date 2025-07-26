package br.com.fiap.postech.gestao_restaurantes.core.exception;

import lombok.Getter;

@Getter
public class ErroAoAcessarRepositorioException extends SystemBaseException {

    private static final long serialVersionUID = 6962253955393952663L;

    private final String code = "usuario.erroAcessarRepositorio";
    private final String message = "Erro ao acessar repositório de dados.";
    private final Integer httpStatus = 500;
}
