package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoTipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.ITipoUsuarioDataSource;
import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.core.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioUtilizadoException;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.TipoUsuarioJPARepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TipoUsuarioRepositoryImpl implements ITipoUsuarioDataSource {

    private final TipoUsuarioJPARepository tipoUsuarioRepository;

    @Override
    @Transactional
    public Long criar(NovoTipoUsuarioDTO novoTipoUsuarioDTO){
        try{
	        TipoUsuarioEntity tipoUsuarioEntity = mapToEntity(novoTipoUsuarioDTO);

            return tipoUsuarioRepository.save(tipoUsuarioEntity).getId();

        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErroAoAcessarRepositorioException();
        }
    }

    @Override
    @Transactional
    public void deletar(Long id) {
    	Optional<TipoUsuarioEntity> tipoUsuarioById = tipoUsuarioRepository.findById(id);
    	
        if (!tipoUsuarioById.isPresent()) {
            throw new TipoUsuarioNaoEncontradoException();
        }
        
        if (tipoUsuarioRepository.isTipoUsuarioInUse(id)) {
            throw new TipoUsuarioUtilizadoException();
        }
        
        tipoUsuarioRepository.deleteById(id);
        
        log.info("Tipo usuário deletado com sucesso: ID={}", id);
    }

    @Override
    @Transactional
	public void atualizar(Long id, TipoUsuarioDTO tipoUsuarioDTO) {
    	try {
    		Optional<TipoUsuarioEntity> tipoUsuarioEntity = tipoUsuarioRepository.findById(id);
    		
			if (!tipoUsuarioEntity.isPresent()) {
				throw new TipoUsuarioNaoEncontradoException();
			}
    		        
	        TipoUsuarioEntity novoTipoUsuario = mapToEntity(tipoUsuarioDTO);
	        novoTipoUsuario.setId(id); 
	       
	        tipoUsuarioRepository.save(novoTipoUsuario);
	    }catch (Exception e){
	        log.error(e.getMessage());
	        throw new ErroAoAcessarRepositorioException();
	    }
	}
	
	@Override
	public Optional<TipoUsuarioDTO> buscarPorId(Long id) {
		var tipoUsuarioEntity = tipoUsuarioRepository.findById(id).orElseThrow(TipoUsuarioNaoEncontradoException::new);
		
		var tipoUsuario = mapToDomain(tipoUsuarioEntity);
		return Optional.of(tipoUsuario);
	}
	
	@Override
	public Optional<TipoUsuarioDTO> buscarPorNome(String nome) {
        Optional<TipoUsuarioEntity> tipoUsuarioEntityOptional = tipoUsuarioRepository.findByNome(nome);

        if(tipoUsuarioEntityOptional.isEmpty()){
            log.info("Tipo de usuário não foi encontrado: Nome={}", nome);
            return Optional.empty();
        }

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioEntityOptional.get();

        TipoUsuarioDTO tipoUsuario = mapToDomain(tipoUsuarioEntity);

        return Optional.of(tipoUsuario);
	}
	
    private TipoUsuarioDTO mapToDomain(TipoUsuarioEntity tipoUsuarioEntity){
        return new TipoUsuarioDTO(
        		tipoUsuarioEntity.getId(),
                tipoUsuarioEntity.getNome()
        );
    }

    private TipoUsuarioEntity mapToEntity(TipoUsuarioDTO tipoUsuario){
    	TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(tipoUsuario.id())
                .nome(tipoUsuario.nome())
                .build();

        return tipoUsuarioEntity;
    }

    private TipoUsuarioEntity mapToEntity(NovoTipoUsuarioDTO tipoUsuario){
        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .nome(tipoUsuario.nome())
                .build();

        return tipoUsuarioEntity;
    }
    
}
