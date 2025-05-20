package br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository;

import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByLogin(String login);
}
