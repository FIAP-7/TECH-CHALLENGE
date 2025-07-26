package br.com.fiap.postech.gestao_restaurantes.core.presenters;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;

public class TipoUsuarioPresenter {

    public static TipoUsuarioDTO toDTO(TipoUsuario tipoUsuario) {
        return new TipoUsuarioDTO(tipoUsuario.getId(), tipoUsuario.getNome());
    }
    public static NovoTipoUsuarioDTO toNovoTipoDTO(TipoUsuario tipoUsuario) {
        return new NovoTipoUsuarioDTO(tipoUsuario.getNome());
    }

    public static TipoUsuario toEntity(TipoUsuarioDTO tipoUsuarioDTO) {
        return TipoUsuario.create(tipoUsuarioDTO.id(), tipoUsuarioDTO.nome());
    }

    public static TipoUsuario toEntity(NovoTipoUsuarioDTO novoTipoUsuarioDTO) {
        return TipoUsuario.create(novoTipoUsuarioDTO.nome());
    }
}
