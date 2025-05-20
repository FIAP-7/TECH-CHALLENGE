package br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa;

import br.com.fiap.postech.gestao_restaurantes.domain.Endereco;
import br.com.fiap.postech.gestao_restaurantes.domain.Usuario;
import br.com.fiap.postech.gestao_restaurantes.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.gateway.UsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.EnderecoEntity;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.entity.UsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository.EnderecoRepository;
import br.com.fiap.postech.gestao_restaurantes.gateway.database.jpa.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioJpaGateway implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;

    private final EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public Long criar(Usuario usuario){
        try{
            UsuarioEntity usuarioEntity = mapToEntity(usuario);

            usuarioEntity.setDataUltimaAlteracao(LocalDateTime.now());

            enderecoRepository.save(usuarioEntity.getEndereco());

            return usuarioRepository.save(usuarioEntity).getId();

        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErroAoAcessarRepositorioException();
        }
    }

    @Override
    public Optional<Usuario> buscarPorLogin(String login) {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findByLogin(login);

        if(usuarioEntityOptional.isEmpty()){
            log.info("Usuário não foi encontrado: Login={}", login);
            return Optional.empty();
        }

        UsuarioEntity usuarioEntity = usuarioEntityOptional.get();

        Usuario usuario = mapToDomain(usuarioEntity);

        return Optional.of(usuario);
    }

    private Usuario mapToDomain(UsuarioEntity usuarioEntity){
        Endereco endereco = new Endereco(
                usuarioEntity.getEndereco().getId(),
                usuarioEntity.getEndereco().getLogradouro(),
                usuarioEntity.getEndereco().getNumero(),
                usuarioEntity.getEndereco().getComplemento(),
                usuarioEntity.getEndereco().getBairro(),
                usuarioEntity.getEndereco().getCidade(),
                usuarioEntity.getEndereco().getEstado(),
                usuarioEntity.getEndereco().getCep()
        );

        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getCpf(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getLogin(),
                usuarioEntity.getSenha(),
                usuarioEntity.getDataUltimaAlteracao(),
                endereco
        );
    }

    private UsuarioEntity mapToEntity(Usuario usuario){

        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .id(usuario.getId())
                .cpf(usuario.getCpf())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .login(usuario.getLogin())
                .senha(usuario.getSenha())
                .build();

        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
                .id(usuario.getEndereco().getId())
                .logradouro(usuario.getEndereco().getLogradouro())
                .numero(usuario.getEndereco().getNumero())
                .complemento(usuario.getEndereco().getComplemento())
                .bairro(usuario.getEndereco().getBairro())
                .cidade(usuario.getEndereco().getCidade())
                .estado(usuario.getEndereco().getEstado())
                .cep(usuario.getEndereco().getCep())
                .build();

        usuarioEntity.setEndereco(enderecoEntity);

        return usuarioEntity;
    }

}
