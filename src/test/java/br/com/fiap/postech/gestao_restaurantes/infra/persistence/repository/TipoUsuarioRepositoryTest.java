
package br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.TipoUsuarioEntity;

@DataJpaTest
@Sql("/scripts/insert_tipo_usuario.sql")
class TipoUsuarioRepositoryTest {

    @Autowired
    private TipoUsuarioJPARepository tipoUsuarioRepository;

    @Test
    void deveRetornarTipoUsuarioProprietarioAoBuscarPorId1() {
        Optional<TipoUsuarioEntity> tipoUsuarioEntity = tipoUsuarioRepository.findById(1L);

        assertThat(tipoUsuarioEntity).isPresent();
        assertThat(tipoUsuarioEntity.get().getNome()).isEqualTo("Proprietário");
    }
    
    @Test
    void deveRetornarTipoUsuarioAoBuscarNomeExistente() {
        Optional<TipoUsuarioEntity> tipoUsuarioEntity = tipoUsuarioRepository.findByNome("Cliente");

        assertThat(tipoUsuarioEntity).isPresent();
        assertThat(tipoUsuarioEntity.get().getId()).isEqualTo(2);
    }
    
    @Test
    void deveRetornarTipoUsuarioNotPresentAoBuscarIdInexistente() {
        Optional<TipoUsuarioEntity> tipoUsuarioEntity = tipoUsuarioRepository.findById(10L);

        assertThat(tipoUsuarioEntity).isNotPresent();
    }
    
    @Test
    void deveRetornarTipoUsuarioNotPresentAoBuscarNomeInexistente() {
        Optional<TipoUsuarioEntity> tipoUsuarioEntity = tipoUsuarioRepository.findByNome("Dono");

        assertThat(tipoUsuarioEntity).isNotPresent();
    }
}
