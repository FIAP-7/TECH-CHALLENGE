package br.com.fiap.postech.gestao_restaurantes.core.entities;

import br.com.fiap.postech.gestao_restaurantes.core.exception.CepInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@EqualsAndHashCode
public class Endereco {

    @Setter
    private Long id;
    @Setter
    private String logradouro;
    @Setter
    private String numero;
    @Setter
    private String complemento;
    @Setter
    private String bairro;
    @Setter
    private String cidade;
    @Setter
    private String estado;
    private String cep;

    private static void cepValido(String cep) {
        //Validacao CEP valido
        Pattern compile = Pattern.compile("\\d{5}-\\d{3}");

        Matcher matcher = compile.matcher(cep);

        if (!matcher.find()) {
            throw new CepInvalidoException();
        }

    }

    public static Endereco create(String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep) throws IllegalArgumentException {
        if(logradouro == null || numero == null || complemento == null || bairro == null || cidade == null || estado == null || cep == null) {
            throw new IllegalArgumentException("Faltam dados");
        }

        Endereco endereco = new Endereco();

        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);

        return endereco;
    }

    public static Endereco create(Long id, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep) throws IllegalArgumentException {
        if(logradouro == null || numero == null || complemento == null || bairro == null || cidade == null || estado == null || cep == null) {
            throw new IllegalArgumentException("Faltam dados");
        }

        Endereco endereco = new Endereco();

        endereco.setId(id);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);

        return endereco;
    }

    public void setCep(String cep) {
        cepValido(cep);

        this.cep = cep;
    }
}
