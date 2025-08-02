package br.com.fiap.postech.gestao_restaurantes.infra.controller;

import br.com.fiap.postech.gestao_restaurantes.core.controller.ItemCardapioCoreController;
import br.com.fiap.postech.gestao_restaurantes.core.dto.ItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoItemCardapioDTO;
import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.ItemCardapioJson;
import br.com.fiap.postech.gestao_restaurantes.infra.repositories.ItemCardapioRepositoryImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item-cardapio")
@RequiredArgsConstructor
@Tag(name = "Itens de cardápio", description = "Endpoints para gerenciamento dos itens de cardápio do restaurante.")
public class ItemCardapioController {

    private final ItemCardapioRepositoryImpl itemCardapioRepository;

    @PostMapping
    @Operation(summary = "Criar item de cardápio", description = "Cria um novo item de cardápio no sistema.")
    public ResponseEntity<Void> criar(@Valid @RequestBody ItemCardapioJson itemCardapioJson) {
        ItemCardapioCoreController itemCardapioCoreController = ItemCardapioCoreController.create(itemCardapioRepository);

        NovoItemCardapioDTO novoItemCardapioDTO = itemCardapioJson.mapToNovoDTO();
        itemCardapioCoreController.incluir(novoItemCardapioDTO);

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar item de cardápio", description = "Remove um item de cardápio do sistema com base no ID informado.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ItemCardapioCoreController itemCardapioCoreController = ItemCardapioCoreController.create(itemCardapioRepository);

        itemCardapioCoreController.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar item de cardápio por ID", description = "Retorna os dados de um item de cardápio a partir do seu ID.")
    public ResponseEntity<ItemCardapioJson> getItemCardapioById(@PathVariable Long id) {
        ItemCardapioCoreController itemCardapioCoreController = ItemCardapioCoreController.create(itemCardapioRepository);

        ItemCardapioDTO itemCardapioDTO = itemCardapioCoreController.buscarPorId(id);

        if (itemCardapioDTO == null) {
            return ResponseEntity.notFound().build();
        }

        ItemCardapioJson itemCardapioJson = new ItemCardapioJson(
                itemCardapioDTO.id(),
                itemCardapioDTO.nome(),
                itemCardapioDTO.descricao(),
                itemCardapioDTO.preco(),
                itemCardapioDTO.disponivelApenasNoRestaurante(),
                itemCardapioDTO.foto()
        );

        return ResponseEntity.ok(itemCardapioJson);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do item de cardápio", description = "Atualiza as informações de um item de cardápio existente.")
    public ResponseEntity<Void> atualizarItemCardapio(@PathVariable Long id, @Valid @RequestBody ItemCardapioJson itemCardapioJson) {
        ItemCardapioCoreController itemCardapioCoreController = ItemCardapioCoreController.create(itemCardapioRepository);

        ItemCardapioDTO itemCardapioDTO = itemCardapioJson.mapToDTO();
        itemCardapioCoreController.alterar(id, itemCardapioDTO);

        return ResponseEntity.noContent().build();
    }
}