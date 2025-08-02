package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.ItemCardapioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCardapioJPARepository extends JpaRepository<ItemCardapioEntity, Long> {

}
