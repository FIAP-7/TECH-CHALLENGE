package br.com.fiap.postech.gestao_restaurantes.core.presenters;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;

public class EnderecoPresenter {

    public static EnderecoDTO toDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );

        return enderecoDTO;
    }

    public static Endereco toEntity(EnderecoDTO enderecoDTO) {
        return Endereco.create(enderecoDTO.logradouro(), enderecoDTO.numero(), enderecoDTO.complemento(), enderecoDTO.bairro(), enderecoDTO.cidade(), enderecoDTO.estado(), enderecoDTO.cep());
    }
}
