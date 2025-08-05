package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
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
import br.com.fiap.postech.gestao_restaurantes.core.dto.TipoUsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.DadoInvalidoUsuarioException;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.CadastroExistenteHandler;
import br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario.handler.UsuarioTipoUsuarioExistenteHandler;

class AtualizarUsuarioUseCaseTest {
    private static final Long ID = 1L;
    private static final String CPF = "123.456.789-00";
    private static final String NOME = "Nome";
    private static final String EMAIL = "email@teste.com";
    private static final String LOGIN = "login";
    private static final String SENHA = "Senha$123";
    private static final String TIPO_NOME = "ADMIN";
    private static final Long TIPO_ID = 1L;
    private static final Long END_ID = 1L;
    private static final String LOGRADOURO = "Rua X";
    private static final String NUMERO = "123";
    private static final String COMPLEMENTO = "Apto 1";
    private static final String BAIRRO = "Bairro Y";
    private static final String CIDADE = "Cidade Z";
    private static final String ESTADO = "UF";
    private static final String CEP = "12345-678";

	@Mock
    private IUsuarioGateway usuarioGateway;
	@Mock
    private ITipoUsuarioGateway tipoUsuarioGateway;
    private AtualizarUsuarioUseCase useCase;
    private AutoCloseable mock;
    private CadastroExistenteHandler handler;
    @Mock
    private UsuarioTipoUsuarioExistenteHandler nextHandler;

    
    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = AtualizarUsuarioUseCase.create(usuarioGateway, tipoUsuarioGateway);
    }
    
    @AfterEach
    void tearDown() throws Exception {
    	mock.close();
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        handler = new CadastroExistenteHandler(usuarioGateway);
        handler.setNext(nextHandler);

        UsuarioDTO dto = new UsuarioDTO(ID, CPF, NOME, EMAIL, LOGIN, SENHA, java.time.LocalDateTime.now(), new TipoUsuarioDTO(TIPO_ID, TIPO_NOME), new EnderecoDTO(END_ID, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP));
        doNothing().when(usuarioGateway).atualizar(anyLong(), any(Usuario.class));
        when(tipoUsuarioGateway.buscarPorId(anyLong())).thenReturn(Optional.of(new TipoUsuario()));
        when(usuarioGateway.buscarPorId(anyLong())).thenReturn(new Usuario());
        when(nextHandler.handle(any(Usuario.class))).thenReturn(false);
        useCase.executar(ID, dto);
        verify(usuarioGateway, times(1)).atualizar(anyLong(), any(Usuario.class));
    }
    
    @Test
    void deveLancarExceptionDadoInvalidoUsuario() {
        handler = new CadastroExistenteHandler(usuarioGateway);
        handler.setNext(nextHandler);

        UsuarioDTO dto = new UsuarioDTO(ID, CPF, NOME, EMAIL, LOGIN, SENHA, java.time.LocalDateTime.now(), new TipoUsuarioDTO(null, TIPO_NOME), new EnderecoDTO(END_ID, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP));
        when(tipoUsuarioGateway.buscarPorId(anyLong())).thenReturn(Optional.of(new TipoUsuario()));
        when(usuarioGateway.buscarPorId(anyLong())).thenReturn(new Usuario());
        when(nextHandler.handle(any(Usuario.class))).thenReturn(false);
        assertThrows(
                DadoInvalidoUsuarioException.class,
                () -> useCase.executar(ID, dto)
            );
    }

    @Test
    void deveLancarExceptionAoNaoEncontrarUsuario() {
        UsuarioDTO dto = new UsuarioDTO(ID, CPF, NOME, EMAIL, LOGIN, SENHA, java.time.LocalDateTime.now(), new TipoUsuarioDTO(TIPO_ID, TIPO_NOME), new EnderecoDTO(END_ID, LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP));
        doThrow(new UsuarioNaoEncontradoException()).when(usuarioGateway).atualizar(anyLong(), any(Usuario.class));

        UsuarioNaoEncontradoException exception = assertThrows(
            UsuarioNaoEncontradoException.class,
            () -> useCase.executar(ID, dto)
        );

        Assertions.assertThat(exception.getCode())
            .isEqualTo("usuario.naoEncontrado");
        Assertions.assertThat(exception.getMessage())
            .isEqualTo("Usuário não encontrado!");
        Assertions.assertThat(exception.getHttpStatus())
            .isEqualTo(HttpStatus.NOT_FOUND.value());
    }

}