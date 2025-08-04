package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestauranteJPARepository extends JpaRepository<RestauranteEntity, Long> {

    Optional<RestauranteEntity> findById(Long id);

	Optional<RestauranteEntity> findByNome(String nome);

    @Query("SELECT COUNT(i) > 0 FROM ItemCardapioEntity i WHERE i.restaurante.id = :idRestaurante")
    boolean isUsuarioInUse(@Param("idRestaurante") Long idRestaurante);
}
