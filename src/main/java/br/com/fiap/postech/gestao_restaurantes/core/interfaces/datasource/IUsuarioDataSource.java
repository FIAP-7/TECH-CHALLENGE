package br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;

public interface IUsuarioDataSource {

    Long criar(NovoUsuarioDTO usuario);

    void deletar(Long id);

    UsuarioDTO buscarPorLogin(String login);

    UsuarioDTO buscarPorId(Long id);

    void atualizarSenha(Long id, String novaSenha);

    void atualizar(Long id, UsuarioDTO usuario);
}
