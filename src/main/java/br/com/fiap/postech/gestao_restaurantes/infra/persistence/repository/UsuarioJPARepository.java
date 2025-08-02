package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.UsuarioEntity;

public interface UsuarioJPARepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByLogin(String login);
    
    Optional<UsuarioEntity> findById(Long id);
    
    @Query("SELECT COUNT(r) > 0 FROM RestauranteEntity r WHERE r.usuario.id = :usuarioId")
    boolean isUsuarioInUse(@Param("usuarioId") Long usuarioId);
}
