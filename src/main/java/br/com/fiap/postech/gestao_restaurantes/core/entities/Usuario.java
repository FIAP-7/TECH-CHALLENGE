package br.com.fiap.postech.gestao_restaurantes.core.entities;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;

import br.com.fiap.postech.gestao_restaurantes.core.exception.CpfUsuarioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.EmailUsuarioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.NomeUsuarioInvalidoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.SenhaFormatoInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class Usuario {

    @Setter
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    @Setter
    private String login;
    private String senha;
    @Setter
    private LocalDateTime dataUltimaAlteracao;
    @Setter
    private TipoUsuario tipoUsuario;
    @Setter
    private Endereco endereco;

    private static void emailValido(String email){
        EmailValidator emailValidator = EmailValidator.getInstance();

        if(!emailValidator.isValid(email)){
            throw new EmailUsuarioInvalidoException();
        }
    }

    private static void cpfValido(String cpf){
        //Adiciona validacao CPF
        Pattern compile = Pattern.compile("\\d{3}.\\d{3}.\\d{3}-\\d{2}");

        Matcher matcher = compile.matcher(cpf);

        if (!matcher.find()) {
            throw new CpfUsuarioInvalidoException();
        }
    }

    private static void nomeValido(String nome){
        //Adiciona validacao Nome
        Pattern compile = Pattern.compile("[0-9{},.?~=+_/*\\-\\\\|\\[\\]ªº%&()#!$@]+");

        Matcher matcher = compile.matcher(nome);

        if (matcher.find()) {
            throw new NomeUsuarioInvalidoException();
        }
    }

    private static void senhaValida(String senha){
        //Adiciona validacao Senha
        Pattern compile = Pattern.compile("(?=.*[}{,.?=+_/*|@#!$%¨&)(\\[\\]\\\\-])(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,}");

        Matcher matcher = compile.matcher(senha);

        if (!matcher.find()) {
            throw new SenhaFormatoInvalidoException();
        }
    }

    public static Usuario create(String cpf, String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao, TipoUsuario tipoUsuario, Endereco endereco) {
    	validarDados(cpf, nome, email, login, senha, endereco);

        Usuario usuario = new Usuario();

        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setDataUltimaAlteracao(dataUltimaAlteracao);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setEndereco(endereco);

        return usuario;
    }
    
    private static void validarDados(String cpf, String nome, String email, String login, String senha,  Endereco endereco) {
        if (cpf == null || nome == null || email == null || login == null || senha == null || endereco == null) {
            throw new IllegalArgumentException("Faltam dados");
        }
    }

    public static Usuario create(Long id, String cpf, String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao, TipoUsuario tipoUsuario, Endereco endereco) {
    	validarDados(cpf, nome, email, login, senha, endereco);

        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setCpf(cpf);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setDataUltimaAlteracao(dataUltimaAlteracao);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setEndereco(endereco);

        return usuario;
    }

    public static Usuario create(Long id, String nome) {
        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setNome(nome);

        return usuario;
    }

	public static Usuario create(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("Faltam dados");
		}
		
		Usuario usuario = new Usuario();

        usuario.setId(id);
	        
        return usuario;
	}
	
    public void setCpf(String cpf) {
        cpfValido(cpf);

        this.cpf = cpf;
    }

    public void setNome(String nome) {
        nomeValido(nome);

        this.nome = nome;
    }

    public void setEmail(String email) {
        emailValido(email);

        this.email = email;
    }

    public void setSenha(String senha) {
        senhaValida(senha);

        this.senha = senha;
    }

}
