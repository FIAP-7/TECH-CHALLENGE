package br.com.fiap.postech.gestao_restaurantes.core.usecase.validarLogin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import br.com.fiap.postech.gestao_restaurantes.core.dto.CredenciaisDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.LoginSenhaInvalidosException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.validarLogin.handler.LoginExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.validarLogin.handler.SenhaCorretaHandler;

class AutenticarUsuarioUsecaseTest {
    @Mock
    private IUsuarioGateway usuarioGateway;
    @Mock
    private LoginExistenteHandler loginExistenteHandler;
    @Mock
    private SenhaCorretaHandler senhaCorretaHandler;
    private AutenticarUsuarioUsecase useCase;
    private AutoCloseable mock;
    
    private static final String LOGIN = "login";
    private static final String SENHA = "Senha@1234";
    

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = AutenticarUsuarioUsecase.create(usuarioGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveAutenticarUsuarioComSucesso() {
        CredenciaisDTO credenciaisDTO = new CredenciaisDTO(LOGIN, SENHA);
        Usuario usuario = new Usuario();
        usuario.setLogin(LOGIN);
        usuario.setSenha(SENHA);
        when(usuarioGateway.buscarPorLogin(anyString())).thenReturn(Optional.of(usuario));
        boolean autenticado = useCase.executar(credenciaisDTO);
        assertThat(autenticado).isTrue();
    }
    
    @Test
    void deveFalharAutenticacaoQuandoUsuarioVazioInvalido() {
        CredenciaisDTO credenciaisDTO = new CredenciaisDTO(LOGIN, SENHA);
        when(usuarioGateway.buscarPorLogin(anyString())).thenReturn(Optional.of(new Usuario()));

        LoginSenhaInvalidosException exception = assertThrows(
        		LoginSenhaInvalidosException.class,
        () -> useCase.executar(credenciaisDTO)
    );

    assertThat(exception.getCode())
        .isEqualTo("usuario.loginSenhaInvalidos");
    assertThat(exception.getMessage())
        .isEqualTo("Login ou senha invalidos");
    assertThat(exception.getHttpStatus())
        .isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

}
