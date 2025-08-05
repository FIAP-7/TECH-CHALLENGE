package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ConsultarTipoUsuarioUseCaseTest {
    @Mock
    private ITipoUsuarioGateway tipoUsuarioGateway;
    private ConsultarTipoUsuarioUseCase useCase;
    private AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = ConsultarTipoUsuarioUseCase.create(tipoUsuarioGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }
    
    @Test
    void deveRetornarTipoUsuarioQuandoEncontrado() {
        TipoUsuario tipoUsuario = new TipoUsuario();
        when(tipoUsuarioGateway.buscarPorId(anyLong())).thenReturn(Optional.of(tipoUsuario));
        Optional<TipoUsuario> resultado = useCase.executar(1L);
        Assertions.assertThat(resultado).isPresent();
        Assertions.assertThat(resultado.get()).isEqualTo(tipoUsuario);
    }

    @Test
    void deveLancarExceptionQuandoTipoUsuarioNaoEncontrado() {
        when(tipoUsuarioGateway.buscarPorId(anyLong())).thenReturn(Optional.empty());
        TipoUsuarioNaoEncontradoException exception = assertThrows(
        	TipoUsuarioNaoEncontradoException.class,
        	() -> useCase.executar(1L)
        );
        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getCode()).isEqualTo("tipoUsuario.tipoUsuarioNaoEncontrado");
        Assertions.assertThat(exception.getMessage()).isEqualTo("Tipo de Usuário não encontrado!");
        Assertions.assertThat(exception.getHttpStatus()).isEqualTo(404);
    }


}
