package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TipoUsuarioJPARepository extends JpaRepository<TipoUsuarioEntity, Long> {
	
	Optional<TipoUsuarioEntity> findById(Long id);

	Optional<TipoUsuarioEntity> findByNome(String nome);
	
	@Query("SELECT COUNT(u) > 0 FROM UsuarioEntity u WHERE u.tipoUsuario.id = :tipoUsuarioId")
    boolean isTipoUsuarioInUse(@Param("tipoUsuarioId") Long tipoUsuarioId);

}
