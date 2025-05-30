package br.com.fiap.postech.gestao_restaurantes.controller;

import java.util.Optional;

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
public class UsuarioController {

    private final CriarUsuarioUsecase criarUsuarioUsecase;
    private final DeletarUsuarioUsecase deletarUsuarioUseCase;
    private final AtualizarSenhaUsuarioUseCase atualizarSenhaUsuarioUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final ConsultarUsuarioUseCase consultarUsuarioUseCase;
    

    @PostMapping
    public Long criar(@Valid @RequestBody UsuarioJson usuarioJson) {
        return criarUsuarioUsecase.criar(usuarioJson.mapToDomain());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> atualizarSenha(@PathVariable Long id, @Valid @RequestBody NovaSenhaJson novaSenhaJson) {
    	atualizarSenhaUsuarioUseCase.executar(id, novaSenhaJson.getNovaSenha());
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable Long id){
    	var usuario = consultarUsuarioUseCase.executar(id);
    	return ResponseEntity.ok(usuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id,@Valid @RequestBody UsuarioJson usuarioJson){
    	atualizarUsuarioUseCase.executar(id, usuarioJson.mapToDomain());
    	return ResponseEntity.noContent().build();
    }
}
