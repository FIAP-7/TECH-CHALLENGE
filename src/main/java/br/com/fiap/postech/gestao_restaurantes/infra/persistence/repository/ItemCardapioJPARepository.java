package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemCardapioJPARepository extends JpaRepository<ItemCardapioEntity, Long> {

    Optional<ItemCardapioEntity> findById(@Param("itemCardapioId") Long itemCardapioId);
}
