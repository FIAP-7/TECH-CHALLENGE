package br.com.fiap.postech.gestao_restaurantes.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoUsuarioEnum {
    DONO(0),
    CLIENTE(1);

    private Integer id;

    public static TipoUsuarioEnum getTipoUsuario(Integer id) {
        for (TipoUsuarioEnum tipoUsuario : TipoUsuarioEnum.values()) {
            if (tipoUsuario.id.equals(id)) {
                return tipoUsuario;
            }
        }
        return null;
    }
}
