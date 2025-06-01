package br.com.fiap.postech.gestao_restaurantes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.postech.gestao_restaurantes.controller.json.LoginJson;
import br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.AutenticarUsuarioUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoint para autenticação de usuários")
public class LoginController {

    private final AutenticarUsuarioUsecase autenticarUsuarioUsecase;

    @PostMapping
    @Operation(summary = "Autenticar usuário", description = "Valida as credenciais de um usuário e retorna uma mensagem de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<String> autenticar(@Valid @RequestBody LoginJson loginJson) {
        boolean credenciaisValidas = autenticarUsuarioUsecase.executar(loginJson.mapToDomain());

        if (credenciaisValidas) {
            return ResponseEntity.ok("Usuário autenticado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}