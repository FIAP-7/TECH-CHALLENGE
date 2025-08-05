package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.PasswordCadastroExistente;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.PasswordValidaHandler;

class AtualizarSenhaUsuarioUseCaseTest {
    private static final Long USUARIO_ID = 1L;
    private static final String NOVA_SENHA = "Senha@1234";
    private static final String USUARIO_NAO_ENCONTRADO_CODE = "usuario.naoEncontrado";
    private static final String USUARIO_NAO_ENCONTRADO_MSG = "Usuário não encontrado!";
    private static final int HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND.value();

    @Mock
    private IUsuarioGateway usuarioGateway;
    private AtualizarSenhaUsuarioUseCase useCase;
    private AutoCloseable mock;
    private PasswordCadastroExistente handler;
    @Mock
    private PasswordValidaHandler nextHandler;


    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = AtualizarSenhaUsuarioUseCase.create(usuarioGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveAtualizarSenhaComSucesso() {
        handler = new PasswordCadastroExistente(usuarioGateway);
        handler.setNext(nextHandler);
        when(nextHandler.handle(anyLong(), anyString())).thenReturn(false);

        when(usuarioGateway.buscarPorId(USUARIO_ID)).thenReturn(new Usuario());
        doNothing().when(usuarioGateway).atualizarSenha(USUARIO_ID, NOVA_SENHA);
        useCase.executar(USUARIO_ID, NOVA_SENHA);
        verify(usuarioGateway, times(1)).atualizarSenha(USUARIO_ID, NOVA_SENHA);
    }

    @Test
    void deveLancarExceptionAoNaoEncontrarUsuario() {
        when(usuarioGateway.buscarPorId(USUARIO_ID)).thenReturn(null);
        UsuarioNaoEncontradoException exception = assertThrows(
            UsuarioNaoEncontradoException.class,
            () -> useCase.executar(USUARIO_ID, NOVA_SENHA)
        );
        Assertions.assertThat(exception.getCode()).isEqualTo(USUARIO_NAO_ENCONTRADO_CODE);
        Assertions.assertThat(exception.getMessage()).isEqualTo(USUARIO_NAO_ENCONTRADO_MSG);
        Assertions.assertThat(exception.getHttpStatus()).isEqualTo(HTTP_STATUS_NOT_FOUND);
    }

    @Test
    void naoDeveAtualizarQuandoUsuarioNaoExiste() {
        when(usuarioGateway.buscarPorId(USUARIO_ID)).thenReturn(null);
        assertThrows(UsuarioNaoEncontradoException.class,
            () -> useCase.executar(USUARIO_ID, NOVA_SENHA));
        verify(usuarioGateway, never()).atualizarSenha(anyLong(), any());
    }

    @Test
    void deveValidarParametrosDaExceptionUsuarioNaoEncontrado() {
        when(usuarioGateway.buscarPorId(USUARIO_ID)).thenReturn(null);
        try {
            useCase.executar(USUARIO_ID, NOVA_SENHA);
        } catch (UsuarioNaoEncontradoException ex) {
            Assertions.assertThat(ex.getCode()).isEqualTo(USUARIO_NAO_ENCONTRADO_CODE);
            Assertions.assertThat(ex.getMessage()).isEqualTo(USUARIO_NAO_ENCONTRADO_MSG);
            Assertions.assertThat(ex.getHttpStatus()).isEqualTo(HTTP_STATUS_NOT_FOUND);
        }
    }
}