package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import br.com.fiap.postech.gestao_restaurantes.core.dto.*;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IItemCardapioDataSource;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.EnderecoEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.ItemCardapioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.RestauranteEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.UsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.ItemCardapioJPARepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemCardapioRepositoryImpl implements IItemCardapioDataSource {

    private final ItemCardapioJPARepository itemCardapioRepository;
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Long criar(NovoItemCardapioDTO novoItemCardapio) {
        ItemCardapioEntity itemCardapioEntity = mapToEntity(novoItemCardapio);
        ItemCardapioEntity savedEntity = itemCardapioRepository.save(itemCardapioEntity);
        return savedEntity.getId();
    }

    @Override
    public void deletar(Long id) {
        Optional<ItemCardapioEntity> itemCardapioById = itemCardapioRepository.findById(id);

        if (!itemCardapioById.isPresent()) {
            log.error("Item de cardápio não encontrado: ID={}", id);
            return;
        }

        itemCardapioRepository.deleteById(id);
        log.info("Item de cardápio deletado com sucesso: ID={}", id);
    }

    @Override
    public void atualizar(Long id, ItemCardapioDTO itemCardapio) {
        Optional<ItemCardapioEntity> itemCardapioById = itemCardapioRepository.findById(id);

        if (!itemCardapioById.isPresent()) {
            log.error("Item de cardápio não encontrado: ID={}", id);
            return;
        }

        ItemCardapioEntity updatedItem = mapToEntity(itemCardapio);
        updatedItem.setId(id);
        itemCardapioRepository.save(updatedItem);

        log.info("Item de cardápio atualizado com sucesso: ID={}", id);
    }

    @Override
    public Optional<ItemCardapioDTO> buscarPorId(Long id) {

        return itemCardapioRepository.findById(id)
                .map(this::mapToDomain)
                .or(Optional::empty);
    }

    @Override
    public Optional<List<ItemCardapioDTO>> buscarPorIdRestaurante(Long idRestaurante) {
        return itemCardapioRepository.findAllByIdRestaurante(idRestaurante)
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Optional::ofNullable
                ));
    }

    private ItemCardapioDTO mapToDomain(ItemCardapioEntity itemCardapioEntity) {
        RestauranteEntity restauranteEntity = itemCardapioEntity.getRestaurante();
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

        RestauranteDTO restauranteDTO = new RestauranteDTO(
                restauranteEntity.getId(),
                restauranteEntity.getNome(),
                restauranteEntity.getTipoCozinha(),
                restauranteEntity.getHorarioFuncionamento(),
                usuario,
                endereco
        );

        return new ItemCardapioDTO(
                itemCardapioEntity.getId(),
                itemCardapioEntity.getNome(),
                itemCardapioEntity.getDescricao(),
                itemCardapioEntity.getPreco(),
                itemCardapioEntity.getDisponivelApenasNoRestaurante(),
                itemCardapioEntity.getFoto(),
                restauranteDTO
        );
    }

    private ItemCardapioEntity mapToEntity(ItemCardapioDTO itemCardapio) {

        return ItemCardapioEntity.builder()
                .id(itemCardapio.id())
                .nome(itemCardapio.nome())
                .descricao(itemCardapio.descricao())
                .preco(itemCardapio.preco())
                .disponivelApenasNoRestaurante(itemCardapio.disponivelApenasNoRestaurante())
                .foto(itemCardapio.foto())
                .restaurante(mapToEntity(itemCardapio.restaurante()))
                .build();
    }

    private ItemCardapioEntity mapToEntity(NovoItemCardapioDTO novoItemCardapioDTO) {
        RestauranteEntity restaurante = entityManager.getReference(RestauranteEntity.class, novoItemCardapioDTO.idRestaurante());

        return ItemCardapioEntity.builder()
                .nome(novoItemCardapioDTO.nome())
                .descricao(novoItemCardapioDTO.descricao())
                .preco(novoItemCardapioDTO.preco())
                .disponivelApenasNoRestaurante(novoItemCardapioDTO.disponivelApenasNoRestaurante())
                .foto(novoItemCardapioDTO.foto())
                .restaurante(restaurante)
                .build();
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
}
