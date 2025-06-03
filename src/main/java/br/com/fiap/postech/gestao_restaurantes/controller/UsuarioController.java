package br.com.fiap.postech.gestao_restaurantes.controller;

import java.util.Optional;

import br.com.fiap.postech.gestao_restaurantes.controller.json.ExceptionJson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.postech.gestao_restaurantes.controller.json.NovaSenhaJson;
import br.com.fiap.postech.gestao_restaurantes.controller.json.UsuarioJson;
import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.usecase.AtualizarSenhaUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.usecase.AtualizarUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.usecase.ConsultarUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.usecase.CriarUsuarioUsecase;
import br.com.fiap.postech.gestao_restaurantes.usecase.DeletarUsuarioUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    private final CriarUsuarioUsecase criarUsuarioUsecase;
    private final DeletarUsuarioUsecase deletarUsuarioUseCase;
    private final AtualizarSenhaUsuarioUseCase atualizarSenhaUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final ConsultarUsuarioUseCase consultarUsuarioUseCase;
    

    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class))
            )
    })
    public ResponseEntity<Void> criar(@Valid @RequestBody UsuarioJson usuarioJson) {
        criarUsuarioUsecase.criar(usuarioJson.mapToDomain());
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class)))
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/senha")
    @Operation(summary = "Atualizar senha do usuário", description = "Atualiza a senha de um usuário com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class)))
    })
    public ResponseEntity<Void> atualizarSenha(@PathVariable Long id, @Valid @RequestBody NovaSenhaJson novaSenhaJson) {
    	atualizarSenhaUsuarioUseCase.executar(id, novaSenhaJson.getNovaSenha());
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário a partir do seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class)))
    })
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable Long id){
    	var usuario = consultarUsuarioUseCase.executar(id);
    	return ResponseEntity.ok(usuario);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do usuário", description = "Atualiza as informações de um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class)))
    })
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioJson usuarioJson){
    	atualizarUsuarioUseCase.executar(id, usuarioJson.mapToDomain());
    	return ResponseEntity.noContent().build();
    }
}
