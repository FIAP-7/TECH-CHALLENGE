package br.com.fiap.postech.gestao_restaurantes.infra.controller;

import br.com.fiap.postech.gestao_restaurantes.core.controller.AutenticarCoreController;
import br.com.fiap.postech.gestao_restaurantes.infra.controller.json.LoginJson;
import br.com.fiap.postech.gestao_restaurantes.infra.repositories.UsuarioRepositoryImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoint para autenticação de usuários")
public class LoginController {

    private final UsuarioRepositoryImpl usuarioRepository;

    @PostMapping
    @Operation(summary = "Autenticar usuário", description = "Valida as credenciais de um usuário e retorna uma mensagem de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<String> autenticar(@Valid @RequestBody LoginJson loginJson) {
        AutenticarCoreController autenticarCoreController = AutenticarCoreController.create(usuarioRepository);

        boolean credenciaisValidas = autenticarCoreController.autenticar(loginJson.mapToDTO());

        if (credenciaisValidas) {
            return ResponseEntity.ok("Usuário autenticado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}