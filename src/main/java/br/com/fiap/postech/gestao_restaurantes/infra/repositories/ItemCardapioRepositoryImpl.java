package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import br.com.fiap.postech.gestao_restaurantes.core.dto.*;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IItemCardapioDataSource;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.ItemCardapioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.RestauranteEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.ItemCardapioJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemCardapioRepositoryImpl implements IItemCardapioDataSource {

    private final ItemCardapioJPARepository itemCardapioRepository;

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
                .or(() -> {
                    log.error("Item de cardápio não encontrado: ID={}", id);
                    return Optional.empty();
                });
    }

    @Override
    public Optional<ItemCardapioDTO> buscarPorRestaurante(Long idRestaurante) {
        return Optional.empty();
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
                .build();
    }

    private ItemCardapioEntity mapToEntity(NovoItemCardapioDTO itemCardapio) {

        return ItemCardapioEntity.builder()
                .nome(itemCardapio.nome())
                .descricao(itemCardapio.descricao())
                .preco(itemCardapio.preco())
                .disponivelApenasNoRestaurante(itemCardapio.disponivelApenasNoRestaurante())
                .foto(itemCardapio.foto())
                .build();
    }
}
