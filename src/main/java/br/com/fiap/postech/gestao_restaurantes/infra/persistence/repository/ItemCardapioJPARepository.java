package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoJPARepository extends JpaRepository<EnderecoEntity, Long> {

}
