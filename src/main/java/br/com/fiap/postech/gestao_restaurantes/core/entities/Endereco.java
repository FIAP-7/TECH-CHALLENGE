package br.com.fiap.postech.gestao_restaurantes.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Endereco {

    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    private static void cepValido(String cep) {
        //Validacao CEP valido
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCep(String cep) {
        cepValido(cep);

        this.cep = cep;
    }
}
