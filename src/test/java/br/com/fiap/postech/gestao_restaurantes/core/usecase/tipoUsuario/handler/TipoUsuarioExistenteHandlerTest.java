package br.com.fiap.postech.gestao_restaurantes.core.usecase.tipoUsuario.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.fiap.postech.gestao_restaurantes.core.entities.TipoUsuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.ITipoUsuarioGateway;

public class TipoUsuarioExistenteHandlerTest {

    private ITipoUsuarioGateway tipoUsuarioGateway;
    private TipoUsuarioExistenteHandler handler;
    private TipoUsuarioHandler nextHandler;

    @BeforeEach
    void setUp() {
        tipoUsuarioGateway = mock(ITipoUsuarioGateway.class);
        handler = new TipoUsuarioExistenteHandler(tipoUsuarioGateway);
        nextHandler = mock(TipoUsuarioHandler.class);
        handler.setNext(nextHandler);
    }

    @Test
    void deveChamarNextHandlerQuandoNextNaoEhNull() {
        TipoUsuario tipoUsuario = TipoUsuario.create(1L, "ADMIN");
        when(tipoUsuarioGateway.buscarPorId(anyLong())).thenReturn(Optional.of(tipoUsuario));
        when(nextHandler.handle(tipoUsuario)).thenReturn(false);

        boolean result = handler.handle(tipoUsuario);

        assertThat(result).isFalse();
        verify(nextHandler, times(1)).handle(tipoUsuario);
    }
}
