package br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.domain.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.gateway.TipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.TipoUsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository.TipoUsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TipoUsuarioJpaGateway implements TipoUsuarioGateway {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Override
    @Transactional
    public Integer criar(TipoUsuario tipoUsuario){
        try{
	        TipoUsuarioEntity tipoUsuarioEntity = mapToEntity(tipoUsuario);

            return tipoUsuarioRepository.save(tipoUsuarioEntity).getId();

        }catch (Exception e){
        	System.out.println("Erro ao criar tipo de usuário: " + e.getMessage());
            log.error(e.getMessage());
            throw new ErroAoAcessarRepositorioException();
        }
    }

    @Override
    @Transactional
    public void deletar(Integer id) {
    	Optional<TipoUsuarioEntity> tipoUsuarioById = tipoUsuarioRepository.findById(id);
    	
        if (!tipoUsuarioById.isPresent()) {
            throw new TipoUsuarioNaoEncontradoException();
        }
        
        tipoUsuarioRepository.deleteById(id);
        
        log.info("Tipo usuário deletado com sucesso: ID={}", id);
    }

    @Override
    @Transactional
	public void atualizar(Integer id, TipoUsuario tipoUsuario) {
    	try {
    		Optional<TipoUsuarioEntity> tipoUsuarioEntity = tipoUsuarioRepository.findById(id);
    		
			if (!tipoUsuarioEntity.isPresent()) {
				throw new TipoUsuarioNaoEncontradoException();
			}
    		        
	        TipoUsuarioEntity novoTipoUsuario = mapToEntity(tipoUsuario);
	        novoTipoUsuario.setId(id); 
	       
	        tipoUsuarioRepository.save(novoTipoUsuario);
	    }catch (Exception e){
	        log.error(e.getMessage());
	        throw new ErroAoAcessarRepositorioException();
	    }
	}
	
	@Override
	public Optional<TipoUsuario> buscarPorId(Integer id) {
		var tipoUsuarioEntity = tipoUsuarioRepository.findById(id).orElseThrow(TipoUsuarioNaoEncontradoException::new);
		
		var tipoUsuario = mapToDomain(tipoUsuarioEntity);
		return Optional.of(tipoUsuario);
	}
	
	@Override
	public Optional<TipoUsuario> buscarPorNome(String nome) {
        Optional<TipoUsuarioEntity> tipoUsuarioEntityOptional = tipoUsuarioRepository.findByNome(nome);

        if(tipoUsuarioEntityOptional.isEmpty()){
            log.info("Tipo de usuário não foi encontrado: Nome={}", nome);
            return Optional.empty();
        }

        TipoUsuarioEntity tipoUsuarioEntity = tipoUsuarioEntityOptional.get();

        TipoUsuario tipoUsuario = mapToDomain(tipoUsuarioEntity);

        return Optional.of(tipoUsuario);
	}
	
    private TipoUsuario mapToDomain(TipoUsuarioEntity tipoUsuarioEntity){
        return new TipoUsuario(
        		tipoUsuarioEntity.getId(),
                tipoUsuarioEntity.getNome()
        );
    }

    private TipoUsuarioEntity mapToEntity(TipoUsuario tipoUsuario){
    	TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(tipoUsuario.getId())
                .nome(tipoUsuario.getNome())
                .build();

        return tipoUsuarioEntity;
    }
    
}
