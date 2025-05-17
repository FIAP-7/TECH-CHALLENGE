package br.com.fiap.postech.gestao_restaurantes.controller;

import br.com.fiap.postech.gestao_restaurantes.controller.json.LoginJson;
import br.com.fiap.postech.gestao_restaurantes.controller.json.UsuarioJson;
import br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.AutenticarUsuarioUsecase;
import br.com.fiap.postech.gestao_restaurantes.usecase.CriarUsuarioUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final CriarUsuarioUsecase criarUsuarioUsecase;
    private final AutenticarUsuarioUsecase autenticarUsuarioUsecase;

    @PostMapping("/register")
    public Long criar(@Valid @RequestBody UsuarioJson usuarioJson) {
        return criarUsuarioUsecase.criar(usuarioJson.mapToDomain());
    }

    @PostMapping("/login")
    public ResponseEntity<String> autenticar(@Valid @RequestBody LoginJson loginJson) {
        boolean credenciaisValidas = autenticarUsuarioUsecase.executar(loginJson.mapToDomain());

        if (credenciaisValidas) {
            return ResponseEntity.ok("Usuário autenticado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
