package br.com.fiap.postech.gestao_restaurantes.infra.controller;


import br.com.fiap.postech.gestao_restaurantes.core.controller.UsuarioCoreController;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.ExceptionJson;
import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.NovaSenhaJson;
import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.UsuarioJson;
import br.com.fiap.postech.gestao_restaurantes.infra.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.infra.repositories.UsuarioRepositoryImpl;
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

import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.AtualizarSenhaUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.AtualizarUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.DeletarUsuarioUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioRepositoryImpl usuarioRepository;

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
        UsuarioCoreController usuarioController = UsuarioCoreController.create(usuarioRepository);

        NovoUsuarioDTO novoUsuarioDTO = usuarioJson.mapToNovoUsuarioDTO();

        usuarioController.incluir(novoUsuarioDTO);

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
        UsuarioCoreController usuarioController = UsuarioCoreController.create(usuarioRepository);

        usuarioController.excluir(id);

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
        UsuarioCoreController usuarioController = UsuarioCoreController.create(usuarioRepository);

        usuarioController.alterarSenha(id, novaSenhaJson.getNovaSenha());

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário a partir do seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ExceptionJson.class)))
    })
    //TODO: Verificar se vai manter como UsuarioDTO ou vai trocar para o usuarioJson
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id){
        UsuarioCoreController usuarioController = UsuarioCoreController.create(usuarioRepository);

        UsuarioDTO usuarioDTO = usuarioController.buscarPorId(id);

        //var usuario = consultarUsuarioUseCase.executar(id);
        //return usuario.map(value -> ResponseEntity.ok(value.mapToJson())).orElseGet(() -> ResponseEntity.ok(null));

        return ResponseEntity.ok(usuarioDTO);
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
    public ResponseEntity<UsuarioJson> atualizarUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioJson usuarioJson){
        UsuarioCoreController usuarioController = UsuarioCoreController.create(usuarioRepository);

        UsuarioDTO usuarioDTO = usuarioJson.mapToUsuarioDTO();
        usuarioController.alterar(id, usuarioDTO);

        return ResponseEntity.noContent().build();
    }
}
