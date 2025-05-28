package br.com.fiap.postech.gestao_restaurantes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.postech.gestao_restaurantes.controller.json.LoginJson;
import br.com.fiap.postech.gestao_restaurantes.controller.json.NovaSenhaJson;
import br.com.fiap.postech.gestao_restaurantes.controller.json.UsuarioJson;
import br.com.fiap.postech.gestao_restaurantes.usecase.AtualizarSenhaUsuarioUseCase;
import br.com.fiap.postech.gestao_restaurantes.usecase.CriarUsuarioUsecase;
import br.com.fiap.postech.gestao_restaurantes.usecase.DeletarUsuarioUsecase;
import br.com.fiap.postech.gestao_restaurantes.usecase.validarLogin.AutenticarUsuarioUsecase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final CriarUsuarioUsecase criarUsuarioUsecase;
    private final DeletarUsuarioUsecase deletarUsuarioUseCase;
    private final AtualizarSenhaUsuarioUseCase atualizarSenhaUsuarioUseCase;
    private final AutenticarUsuarioUsecase autenticarUsuarioUsecase;

    @PostMapping("/register")
    public Long criar(@Valid @RequestBody UsuarioJson usuarioJson) {
        return criarUsuarioUsecase.criar(usuarioJson.mapToDomain());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("user/{id}/senha")
        public ResponseEntity<Void> atualizarSenha(@PathVariable Long id, @Valid @RequestBody NovaSenhaJson novaSenhaJson) {
    	atualizarSenhaUsuarioUseCase.executar(id, novaSenhaJson.getNovaSenha());
        return ResponseEntity.noContent().build();
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
