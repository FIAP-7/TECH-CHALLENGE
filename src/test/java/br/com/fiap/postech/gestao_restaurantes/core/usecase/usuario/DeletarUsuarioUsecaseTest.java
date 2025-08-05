package br.com.fiap.postech.gestao_restaurantes.core.usecase.usuario;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;

class DeletarUsuarioUsecaseTest {
    private static final Long ID_EXISTENTE = 1L;
    private static final Long ID_INEXISTENTE = 2L;
    @Mock
    private IUsuarioGateway usuarioGateway;
    private DeletarUsuarioUsecase useCase;
    private AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        useCase = DeletarUsuarioUsecase.create(usuarioGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        when(usuarioGateway.buscarPorId(ID_EXISTENTE)).thenReturn(new Usuario());
        useCase.executar(ID_EXISTENTE);
        verify(usuarioGateway).deletar(ID_EXISTENTE);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioGateway.buscarPorId(ID_INEXISTENTE)).thenReturn(null);
        assertThrows(UsuarioNaoEncontradoException.class, () -> useCase.executar(ID_INEXISTENTE));
    }
}
