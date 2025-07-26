package br.com.fiap.postech.gestao_restaurantes.infra.gateway;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.infra.domain.Usuario;

@Deprecated
public interface UsuarioGateway {

    public Long criar(Usuario usuario);
    
    public void deletar(Long id); 

    public Optional<Usuario> buscarPorLogin(String login);

    public void atualizarSenha(Long id, String novaSenha);
       
    public Optional<Usuario> buscarPorId(Long id);

    public void atualizar(Long id, Usuario usuario);

}
