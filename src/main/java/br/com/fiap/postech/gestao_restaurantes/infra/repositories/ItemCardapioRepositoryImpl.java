package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import br.com.fiap.postech.gestao_restaurantes.core.dto.ItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IItemCardapioDataSource;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.ItemCardapioEntity;
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
        return Optional.empty();
    }

    @Override
    public Optional<ItemCardapioDTO> buscarPorRestaurante(Long idRestaurante) {
        return Optional.empty();
    }

    private ItemCardapioDTO mapToDomain(ItemCardapioEntity itemCardapioEntity) {

        return new ItemCardapioDTO(
                itemCardapioEntity.getId(),
                itemCardapioEntity.getNome(),
                itemCardapioEntity.getDescricao(),
                itemCardapioEntity.getPreco(),
                itemCardapioEntity.getDisponivelApenasNoRestaurante(),
                itemCardapioEntity.getFoto()
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
