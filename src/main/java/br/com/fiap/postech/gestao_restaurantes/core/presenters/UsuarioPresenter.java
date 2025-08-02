package br.com.fiap.postech.gestao_restaurantes.core.presenters;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;

public class UsuarioPresenter {

    public static UsuarioDTO toDTO(Usuario usuario) {
        EnderecoDTO enderecoDTO = EnderecoPresenter.toDTO(usuario.getEndereco());
        TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioPresenter.toDTO(usuario.getTipoUsuario());

        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getDataUltimaAlteracao(),
                tipoUsuarioDTO,
                enderecoDTO
        );

        return usuarioDTO;
    }


    public static NovoUsuarioDTO toNovoUsuarioDTO(Usuario usuario) {
        EnderecoDTO enderecoDTO = EnderecoPresenter.toDTO(usuario.getEndereco());
        TipoUsuarioDTO tipoUsuarioDTO = TipoUsuarioPresenter.toDTO(usuario.getTipoUsuario());

        NovoUsuarioDTO usuarioDTO = new NovoUsuarioDTO(
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getDataUltimaAlteracao(),
                tipoUsuarioDTO,
                enderecoDTO
        );

        return usuarioDTO;
    }

    public static UsuarioRestauranteDTO toUsuarioRestauranteDTO(Usuario usuario) {
    	UsuarioRestauranteDTO usuarioRestauranteDTO = new UsuarioRestauranteDTO(
                usuario.getId(),
                usuario.getNome()
        );

        return usuarioRestauranteDTO;
    }
    
    
    public static Usuario toEntity(UsuarioDTO usuarioDTO, TipoUsuario tipoUsuario, Endereco endereco) {
        return Usuario.create(usuarioDTO.id(), usuarioDTO.cpf(), usuarioDTO.nome(), usuarioDTO.email(), usuarioDTO.login(), usuarioDTO.senha(), usuarioDTO.dataUltimaAlteracao(), tipoUsuario, endereco);
    }

    public static Usuario toEntity(NovoUsuarioDTO usuarioDTO, TipoUsuario tipoUsuario, Endereco endereco) {
        return Usuario.create(usuarioDTO.cpf(), usuarioDTO.nome(), usuarioDTO.email(), usuarioDTO.login(), usuarioDTO.senha(), usuarioDTO.dataUltimaAlteracao(), tipoUsuario, endereco);
    }


    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        TipoUsuario tipoUsuario = TipoUsuarioPresenter.toEntity(usuarioDTO.tipoUsuario());

        Endereco endereco = EnderecoPresenter.toEntity(usuarioDTO.endereco());

        return Usuario.create(usuarioDTO.cpf(), usuarioDTO.nome(), usuarioDTO.email(), usuarioDTO.login(), usuarioDTO.senha(), usuarioDTO.dataUltimaAlteracao(), tipoUsuario, endereco);
    }
    
    public static Usuario toEntity(UsuarioRestauranteDTO usuarioRestauranteDTO) {
        return Usuario.create(usuarioRestauranteDTO.id(), usuarioRestauranteDTO.nome());
    }
}
