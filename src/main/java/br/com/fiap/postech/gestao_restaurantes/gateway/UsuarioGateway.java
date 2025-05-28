package br.com.fiap.postech.gestao_restaurantes.gateway;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;

public interface UsuarioGateway {

    public Long criar(Usuario usuario);
    
    public void deletar(Long id); 

    public Optional<Usuario> buscarPorLogin(String login);
    
    public void atualizarSenha(Long id, String novaSenha);
    
    public Optional<Usuario> buscarPorId(Long id);

	public void atualizar(Long id, Usuario usuario);
	
	public Optional<Usuario> buscarPorCpf(String cpf);

}
