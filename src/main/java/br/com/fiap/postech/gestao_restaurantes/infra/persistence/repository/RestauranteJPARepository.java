package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.RestauranteEntity;

public interface RestauranteJPARepository extends JpaRepository<RestauranteEntity, Long> {

    Optional<RestauranteEntity> findById(Long id);

	Optional<RestauranteEntity> findByNome(String nome);
}
