package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJPARepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByLogin(String login);
    
    Optional<UsuarioEntity> findById(Long id);
}
