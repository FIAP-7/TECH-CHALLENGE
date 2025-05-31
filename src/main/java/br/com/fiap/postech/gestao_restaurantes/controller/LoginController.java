package br.com.fiap.postech.gestao_restaurantes.controller;

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
public class LoginController {

    private final AutenticarUsuarioUsecase autenticarUsuarioUsecase;

    @PostMapping
    public ResponseEntity<String> autenticar(@Valid @RequestBody LoginJson loginJson) {
        boolean credenciaisValidas = autenticarUsuarioUsecase.executar(loginJson.mapToDomain());

        if (credenciaisValidas) {
            return ResponseEntity.ok("Usuário autenticado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

}