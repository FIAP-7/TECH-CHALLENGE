package br.com.fiap.postech.gestao_restaurantes.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.postech.gestao_restaurantes.controller.json.TipoUsuarioJson;
import br.com.fiap.postech.gestao_restaurantes.usecase.AtualizarTipoUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.usecase.ConsultarTipoUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.usecase.CriarTipoUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.usecase.DeletarTipoUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tipo_usuario")
@RequiredArgsConstructor
@Tag(name = "Tipo usuário", description = "Endpoints para gerenciamento de tipo de usuário")
public class TipoUsuarioController {

    private final CriarTipoUsuarioUseCase criarTipoUsuarioUsecase;
    private final DeletarTipoUsuarioUseCase deletarTipoUsuarioUseCase;
    private final AtualizarTipoUsuarioUseCase atualizarTipoUsuarioUseCase;
    private final ConsultarTipoUsuarioUseCase consultarTipoUsuarioUseCase;
    

    @PostMapping
    @Operation(summary = "Criar tipo usuário", description = "Cria um novo tipo de usuário no sistema.")
    public ResponseEntity<Void> criar(@Valid @RequestBody TipoUsuarioJson tipoUsuarioJson) {
        criarTipoUsuarioUsecase.criar(tipoUsuarioJson.mapToDomain());
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar tipo de usuário", description = "Remove um tipo de usuário do sistema com base no ID informado.")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        deletarTipoUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar tipo de usuário por ID", description = "Retorna os dados de um tipo de usuário a partir do seu ID.")
    public ResponseEntity<TipoUsuarioJson> getTipoUsuarioById(@PathVariable Integer id){
    	var tipoUsuario = consultarTipoUsuarioUseCase.executar(id);
    	
        return tipoUsuario.map(value -> ResponseEntity.ok(value.mapToJson())).orElseGet(() -> ResponseEntity.ok(null));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do tipo de usuário", description = "Atualiza as informações de um tipo de usuário existente.")
    public ResponseEntity<TipoUsuarioJson> atualizarTipoUsuario(@PathVariable Integer id, @Valid @RequestBody TipoUsuarioJson tipoUsuarioJson){
    	atualizarTipoUsuarioUseCase.executar(id, tipoUsuarioJson.mapToDomain());
    	return ResponseEntity.noContent().build();
    }
}
