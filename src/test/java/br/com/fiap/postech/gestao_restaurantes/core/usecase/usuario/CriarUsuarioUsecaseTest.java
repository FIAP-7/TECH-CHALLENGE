package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioExistenteException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

class CriarUsuarioUsecaseTest {
    private static final String LOGIN = "user";
    private static final String EMAIL = "email@teste.com";
    @Mock
    private IUsuarioGateway usuarioGateway;
    @Mock
    private ITipoUsuarioGateway tipoUsuarioGateway;
    private CriarUsuarioUsecase useCase;
    private AutoCloseable mock;
    
    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = CriarUsuarioUsecase.create(usuarioGateway, tipoUsuarioGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    private NovoUsuarioDTO buildNovoUsuarioDTO() {
        var tipoUsuarioDTO = new TipoUsuarioDTO(
            1L,
            "ADMIN"
        );
        var enderecoDTO = new EnderecoDTO(
        	1L,
            "Rua Teste",
            "100",
            "Apto 10",
            "Bairro Teste",
            "Cidade Teste",
            "SP",
            "11345-876"
        );
        return new NovoUsuarioDTO(
            "124.536.528-74",
            "Nome Completo",
            EMAIL,
            LOGIN,
            "Senha@1234",
            java.time.LocalDateTime.now(),
            tipoUsuarioDTO,
            enderecoDTO
        );
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        NovoUsuarioDTO dto = buildNovoUsuarioDTO();
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(mock(br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario.class)));
        when(usuarioGateway.criar(any(br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario.class))).thenReturn(1L);

        Long id = useCase.executar(dto);
        Assertions.assertThat(id).isEqualTo(1L);
        verify(usuarioGateway).criar(any(br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario.class));
    }

    @Test
    void deveLancarExceptionQuandoUsuarioJaExiste() {
        NovoUsuarioDTO dto = buildNovoUsuarioDTO();
        when(usuarioGateway.buscarPorLogin(LOGIN)).thenReturn(Optional.of(mock(br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario.class)));
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.of(mock(br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario.class)));
        UsuarioExistenteException exception = assertThrows(
            UsuarioExistenteException.class,
            () -> useCase.executar(dto)
        );
        Assertions.assertThat(exception.getCode()).isEqualTo("usuario.usuarioJaExiste");
        Assertions.assertThat(exception.getMessage()).isEqualTo("Usuário já existe");
        Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

    @Test
    void deveLancarExceptionQuandoTipoUsuarioNaoExiste() {
        NovoUsuarioDTO dto = buildNovoUsuarioDTO();
        when(tipoUsuarioGateway.buscarPorId(1L)).thenReturn(Optional.empty());
        when(usuarioGateway.buscarPorLogin(LOGIN)).thenReturn(Optional.empty());
        Exception exception = assertThrows(Exception.class, () -> useCase.executar(dto));
        Assertions.assertThat(exception.getMessage()).contains("Tipo de Usuário não encontrado!");
    }
}
