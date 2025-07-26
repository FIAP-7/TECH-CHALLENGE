package br.com.fiap.postech.gestao_restaurantes.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Usuario {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAlteracao;
    private TipoUsuario tipoUsuario;
    private Endereco endereco;

    private static void emailValido(String email){
        EmailValidator emailValidator = EmailValidator.getInstance();

        if(!emailValidator.isValid(email)){
            throw new IllegalArgumentException("Endereco de email invalido");
        }
    }

    private static void cpfValido(String cpf){
        //Adiciona validacao CPF
    }

    private static void nomeValido(String nome){
        //Adiciona validacao Nome
    }

    private static void senhaValida(String senha){
        //Adiciona validacao Senha
    }

    public static Usuario create(String cpf, String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao, TipoUsuario tipoUsuario, Endereco endereco) {
        if (cpf == null || nome == null || email == null || login == null || senha == null || endereco == null) {
            throw new IllegalArgumentException("Faltam dados");
        }

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

    public static Usuario create(Long id, String cpf, String nome, String email, String login, String senha, LocalDateTime dataUltimaAlteracao, TipoUsuario tipoUsuario, Endereco endereco) {
        if (cpf == null || nome == null || email == null || login == null || senha == null || endereco == null) {
            throw new IllegalArgumentException("Faltam dados");
        }

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



    public void setId(Long id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        cpfValido(cpf);

        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        emailValido(email);

        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        senhaValida(senha);

        this.senha = senha;
    }

    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
