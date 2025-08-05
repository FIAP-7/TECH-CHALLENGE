package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.NovoRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CriarRestauranteUseCaseTest {

    @Mock
    private IRestauranteGateway restauranteGateway;

    @Mock
    private IUsuarioGateway usuarioGateway;

    @Test
    void deveCriarRestauranteComSucesso() {
        CriarRestauranteUseCase useCase = CriarRestauranteUseCase.create(restauranteGateway, usuarioGateway);

        EnderecoDTO enderecoDTO = new EnderecoDTO(1L, "Rua A", "123", "", "Centro", "São Paulo", "SP", "01000-000");
        UsuarioRestauranteDTO usuarioDTO = new UsuarioRestauranteDTO(1L, "Felipe");
        NovoRestauranteDTO dto = new NovoRestauranteDTO("Restaurante XPTO", "Italiana", "10h-22h", usuarioDTO, enderecoDTO);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.id());
        usuario.setNome(usuarioDTO.nome());

        when(usuarioGateway.buscarPorId(usuarioDTO.id()))
                .thenReturn(usuario)
                .thenReturn(usuario);

        when(restauranteGateway.criar(any())).thenReturn(1L);

        Long idCriado = useCase.executar(dto);

        assertNotNull(idCriado);
        assertEquals(1L, idCriado);
        verify(restauranteGateway, times(1)).criar(any());
        verify(usuarioGateway, times(2)).buscarPorId(usuarioDTO.id());
    }
}
