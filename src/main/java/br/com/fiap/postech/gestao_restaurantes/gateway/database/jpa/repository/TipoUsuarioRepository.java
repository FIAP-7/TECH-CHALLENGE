package br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.TipoUsuarioEntity;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Integer> {
	
	Optional<TipoUsuarioEntity> findById(Integer id);

	Optional<TipoUsuarioEntity> findByNome(String nome);
	
	@Query("SELECT COUNT(u) > 0 FROM UsuarioEntity u WHERE u.tipoUsuario.id = :tipoUsuarioId")
    boolean isTipoUsuarioInUse(@Param("tipoUsuarioId") Integer tipoUsuarioId);

}
