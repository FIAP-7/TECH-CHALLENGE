package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IRestauranteDataSource;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.EnderecoEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.RestauranteEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.UsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.RestauranteJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestauranteRepositoryImpl implements IRestauranteDataSource {
	private final RestauranteJPARepository restauranteRepository;
	
	@Override
	public Long criar(NovoRestauranteDTO novoRestauranteDTO) {
		RestauranteEntity restauranteEntity = mapToEntity(novoRestauranteDTO);
		
		return restauranteRepository.save(restauranteEntity).getId();
	}

	@Override
	public void deletar(Long id) {
		Optional<RestauranteEntity> restauranteById = restauranteRepository.findById(id);

        if(restauranteById.isPresent()) {
        	restauranteRepository.deleteById(id);

            log.info("Restaurante deletado com sucesso: ID={}", id);
        }
		
	}

	@Override
	public RestauranteDTO buscarPorId(Long id) {
		 RestauranteEntity restauranteEntity = restauranteRepository.findById(id).orElseThrow(RestauranteNaoEncontradoException::new);
	     return mapToDomain(restauranteEntity);
	}

	@Override
	public Optional<RestauranteDTO> buscarPorNome(String nome) {
		Optional<RestauranteEntity> restauranteEntityOptional = restauranteRepository.findByNome(nome);
		
		if (restauranteEntityOptional.isEmpty()) {
			return Optional.empty();
		}
		
		RestauranteEntity restauranteEntity = restauranteEntityOptional.get();
		
		RestauranteDTO restaurante = mapToDomain(restauranteEntity);

		return Optional.of(restaurante);
	}
	
	@Override
	public void atualizar(Long id, RestauranteDTO restauranteDTO) {
		try {
            Optional<RestauranteEntity> restauranteEntity = restauranteRepository.findById(id);

            if (restauranteEntity.isPresent()) {
            	RestauranteEntity restaurante = mapToEntity(restauranteDTO);
            	
            	restaurante.setId(id);
            	restaurante.getUsuario().setId(restauranteDTO.usuario().id());
            	restaurante.getEndereco().setId(restauranteEntity.get().getEndereco().getId());
            	
                restauranteRepository.save(restaurante);

                log.info("Restaurante atualizado com sucesso: ID={}", id);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErroAoAcessarRepositorioException();
        }
		
	}

	 private RestauranteEntity mapToEntity(NovoRestauranteDTO novoRestauranteDTO) {
			RestauranteEntity restauranteEntity = RestauranteEntity.builder()
					.nome(novoRestauranteDTO.nome())
					.tipoCozinha(novoRestauranteDTO.tipoCozinha())
					.horarioFuncionamento(novoRestauranteDTO.horarioFuncionamento())
					.build();
			
			UsuarioEntity usuarioEntity = UsuarioEntity.builder()
					.id(novoRestauranteDTO.usuario().id())
					.nome(novoRestauranteDTO.usuario().nome())
					.build();
			
	        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
	                .id(novoRestauranteDTO.endereco().id())
	                .logradouro(novoRestauranteDTO.endereco().logradouro())
	                .numero(novoRestauranteDTO.endereco().numero())
	                .complemento(novoRestauranteDTO.endereco().complemento())
	                .bairro(novoRestauranteDTO.endereco().bairro())
	                .cidade(novoRestauranteDTO.endereco().cidade())
	                .estado(novoRestauranteDTO.endereco().estado())
	                .cep(novoRestauranteDTO.endereco().cep())
	                .build();

			restauranteEntity.setUsuario(usuarioEntity);
			restauranteEntity.setEndereco(enderecoEntity);

			return restauranteEntity;
	}
	 
	 private RestauranteEntity mapToEntity(RestauranteDTO restauranteDTO) {
			RestauranteEntity restauranteEntity = RestauranteEntity.builder()
					.id(restauranteDTO.id())
					.nome(restauranteDTO.nome())
					.tipoCozinha(restauranteDTO.tipoCozinha())
					.horarioFuncionamento(restauranteDTO.horarioFuncionamento())
					.build();
			
			UsuarioEntity usuarioEntity = UsuarioEntity.builder()
					.id(restauranteDTO.usuario().id())
					.nome(restauranteDTO.usuario().nome())
					.build();
			
	        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
	                .id(restauranteDTO.endereco().id())
	                .logradouro(restauranteDTO.endereco().logradouro())
	                .numero(restauranteDTO.endereco().numero())
	                .complemento(restauranteDTO.endereco().complemento())
	                .bairro(restauranteDTO.endereco().bairro())
	                .cidade(restauranteDTO.endereco().cidade())
	                .estado(restauranteDTO.endereco().estado())
	                .cep(restauranteDTO.endereco().cep())
	                .build();

			restauranteEntity.setUsuario(usuarioEntity);
			restauranteEntity.setEndereco(enderecoEntity);

			return restauranteEntity;
	}

	private RestauranteDTO mapToDomain(RestauranteEntity restauranteEntity){
		 	UsuarioRestauranteDTO usuario = new UsuarioRestauranteDTO(
		 			restauranteEntity.getUsuario().getId(),
		 			restauranteEntity.getUsuario().getNome()
 			);
		 
	        EnderecoDTO endereco = new EnderecoDTO(
	        		restauranteEntity.getEndereco().getId(),
	        		restauranteEntity.getEndereco().getLogradouro(),
	        		restauranteEntity.getEndereco().getNumero(),
	        		restauranteEntity.getEndereco().getComplemento(),
	        		restauranteEntity.getEndereco().getBairro(),
	        		restauranteEntity.getEndereco().getCidade(),
	        		restauranteEntity.getEndereco().getEstado(),
	        		restauranteEntity.getEndereco().getCep()
	        );

	        return new RestauranteDTO(
	        		restauranteEntity.getId(),
	        		restauranteEntity.getNome(),
	                restauranteEntity.getTipoCozinha(),
	                restauranteEntity.getHorarioFuncionamento(),
	                usuario,
	                endereco
	        );
	    }

}
