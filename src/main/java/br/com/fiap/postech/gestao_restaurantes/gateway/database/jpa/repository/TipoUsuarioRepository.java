package br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.TipoUsuarioEntity;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Integer> {
	
	Optional<TipoUsuarioEntity> findById(Integer id);

	Optional<TipoUsuarioEntity> findByNome(String nome);

}
