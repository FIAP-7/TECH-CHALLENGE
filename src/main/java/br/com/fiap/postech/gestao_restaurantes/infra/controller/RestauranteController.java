package br.com.fiap.postech.gestao_restaurantes.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.postech.gestao_restaurantes.core.controller.RestauranteCoreController;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.RestauranteJson;
import br.com.fiap.postech.gestao_restaurantes.infra.repositories.RestauranteRepositoryImpl;
import br.com.fiap.postech.gestao_restaurantes.infra.repositories.UsuarioRepositoryImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("restaurante")
@RequiredArgsConstructor
@Tag(name = "Restaurante", description = "Endpoints para gerenciamento de restaurantes")
public class RestauranteController {

	private final RestauranteRepositoryImpl restauranteRepository;
	private final UsuarioRepositoryImpl usuarioRepository;
	
    @PostMapping
    @Operation(summary = "Criar restaurante", description = "Cria um novo restaurante no sistema.")
    public ResponseEntity<Void> criar(@Valid @RequestBody RestauranteJson restauranteJson) {
        RestauranteCoreController restauranteCoreController = RestauranteCoreController.create(restauranteRepository, usuarioRepository);

        NovoRestauranteDTO novoRestauranteDTO = restauranteJson.mapToNovoRestauranteDTO();

        restauranteCoreController.incluir(novoRestauranteDTO);

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar restaurante", description = "Remove um restaurante do sistema com base no ID informado.")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	RestauranteCoreController restauranteCoreController = RestauranteCoreController.create(restauranteRepository, usuarioRepository);

    	restauranteCoreController.excluir(id);

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar restaurante por ID", description = "Retorna os dados de um restaurante a partir do seu ID.")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@PathVariable Long id){
    	RestauranteCoreController restauranteCoreController = RestauranteCoreController.create(restauranteRepository, usuarioRepository);

        RestauranteDTO restauranteDTO = restauranteCoreController.buscarPorId(id);

        return ResponseEntity.ok(restauranteDTO);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do restaurante", description = "Atualiza as informações de um restaurante existente.")
    public ResponseEntity<RestauranteJson> atualizarRestaurante(@PathVariable Long id,@Valid @RequestBody RestauranteJson restauranteJson){
    	RestauranteCoreController restauranteCoreController = RestauranteCoreController.create(restauranteRepository, usuarioRepository);

    	RestauranteDTO restauranteDTO = restauranteJson.mapToDTO();
    	 
    	restauranteCoreController.alterar(id, restauranteDTO);
    	
        return ResponseEntity.noContent().build();
    }
    
}
