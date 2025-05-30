package br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByLogin(String login);
    
    Optional<UsuarioEntity> findById(Long id);
}
