package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.exception.ErroAoAcessarRepositorioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.datasource.IUsuarioDataSource;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.EnderecoEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.TipoUsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.UsuarioEntity;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.EnderecoJPARepository;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.UsuarioJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements IUsuarioDataSource {

    private final UsuarioJPARepository usuarioRepository;

    private final EnderecoJPARepository enderecoRepository;

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
        return mapToDomain(usuarioEntity);
    }

    @Override
    public void atualizarSenha(Long id, String novaSenha) {
        try {
            Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);

            if (usuarioEntity.isPresent()) {
                usuarioEntity.get().setDataUltimaAlteracao(LocalDateTime.now());
                usuarioEntity.get().setSenha(novaSenha);

                usuarioRepository.save(usuarioEntity.get());
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErroAoAcessarRepositorioException();
        }
    }

    @Override
    public void atualizar(Long id, UsuarioDTO usuario) {
        try {
            Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);

            if (usuarioEntity.isPresent()) {
                UsuarioEntity novoUsuario = mapToEntity(usuario);
                novoUsuario.setId(id);
                novoUsuario.setDataUltimaAlteracao(LocalDateTime.now());
                novoUsuario.getEndereco().setId(usuarioEntity.get().getEndereco().getId());

                enderecoRepository.save(novoUsuario.getEndereco());
                usuarioRepository.save(novoUsuario);

                log.info("Usuário atualizado com sucesso: ID={}", id);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ErroAoAcessarRepositorioException();
        }

    }

    @Override
    public void deletar(Long id) {
        Optional<UsuarioEntity> usuarioById = usuarioRepository.findById(id);

        if(usuarioById.isPresent()) {
            EnderecoEntity endereco = usuarioById.get().getEndereco();

            //TODO: Verificar se não deveria estar em um especifico para endereco
            enderecoRepository.deleteById(endereco.getId());
            usuarioRepository.deleteById(id);

            log.info("Usuário deletado com sucesso: ID={}", id);
        }
    }

    @Override
    public UsuarioDTO buscarPorLogin(String login) {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findByLogin(login);

        if(usuarioEntityOptional.isEmpty()){
            log.info("Usuário não foi encontrado: Login={}", login);
            return null;
        }

        UsuarioEntity usuarioEntity = usuarioEntityOptional.get();

        return mapToDomain(usuarioEntity);
    }

    @Override
    public Long criar(NovoUsuarioDTO usuario) {
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

    private UsuarioEntity mapToEntity(NovoUsuarioDTO usuario){

        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .cpf(usuario.cpf())
                .nome(usuario.nome())
                .email(usuario.email())
                .login(usuario.login())
                .senha(usuario.senha())
                .build();

        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(usuario.tipoUsuario().id())
                .nome(usuario.tipoUsuario().nome())
                .build();

        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
                .id(usuario.endereco().id())
                .logradouro(usuario.endereco().logradouro())
                .numero(usuario.endereco().numero())
                .complemento(usuario.endereco().complemento())
                .bairro(usuario.endereco().bairro())
                .cidade(usuario.endereco().cidade())
                .estado(usuario.endereco().estado())
                .cep(usuario.endereco().cep())
                .build();

        usuarioEntity.setTipoUsuario(tipoUsuarioEntity);
        usuarioEntity.setEndereco(enderecoEntity);

        return usuarioEntity;
    }


    private UsuarioEntity mapToEntity(UsuarioDTO usuario){

        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .id(usuario.id())
                .cpf(usuario.cpf())
                .nome(usuario.nome())
                .email(usuario.email())
                .login(usuario.login())
                .senha(usuario.senha())
                .build();

        TipoUsuarioEntity tipoUsuarioEntity = TipoUsuarioEntity.builder()
                .id(usuario.tipoUsuario().id())
                .nome(usuario.tipoUsuario().nome())
                .build();

        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
                .id(usuario.endereco().id())
                .logradouro(usuario.endereco().logradouro())
                .numero(usuario.endereco().numero())
                .complemento(usuario.endereco().complemento())
                .bairro(usuario.endereco().bairro())
                .cidade(usuario.endereco().cidade())
                .estado(usuario.endereco().estado())
                .cep(usuario.endereco().cep())
                .build();

        usuarioEntity.setTipoUsuario(tipoUsuarioEntity);
        usuarioEntity.setEndereco(enderecoEntity);

        return usuarioEntity;
    }

    private UsuarioDTO mapToDomain(UsuarioEntity usuarioEntity){
        EnderecoDTO endereco = new EnderecoDTO(
                usuarioEntity.getEndereco().getId(),
                usuarioEntity.getEndereco().getLogradouro(),
                usuarioEntity.getEndereco().getNumero(),
                usuarioEntity.getEndereco().getComplemento(),
                usuarioEntity.getEndereco().getBairro(),
                usuarioEntity.getEndereco().getCidade(),
                usuarioEntity.getEndereco().getEstado(),
                usuarioEntity.getEndereco().getCep()
        );

        TipoUsuarioDTO tipoUsuario = new TipoUsuarioDTO(
                usuarioEntity.getTipoUsuario().getId(),
                usuarioEntity.getTipoUsuario().getNome()
        );

        return new UsuarioDTO(
                usuarioEntity.getId(),
                usuarioEntity.getCpf(),
                usuarioEntity.getNome(),
                usuarioEntity.getEmail(),
                usuarioEntity.getLogin(),
                usuarioEntity.getSenha(),
                usuarioEntity.getDataUltimaAlteracao(),
                tipoUsuario,
                endereco
        );
    }

}
