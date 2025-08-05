package br.com.fiap.postech.gestao_restaurantes.core.usecase.restaurante;

import br.com.fiap.postech.gestao_restaurantes.core.dto.EnderecoDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.RestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.dto.UsuarioRestauranteDTO;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Endereco;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Restaurante;
import br.com.fiap.postech.gestao_restaurantes.core.entities.Usuario;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IRestauranteGateway;
import br.com.fiap.postech.gestao_restaurantes.core.interfaces.gateway.IUsuarioGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarRestauranteUseCaseTest {

    @Mock
    private IRestauranteGateway restauranteGateway;

    @Mock
    private IUsuarioGateway usuarioGateway;

    @InjectMocks
    private AtualizarRestauranteUseCase useCase;

    @Test
    void deveAtualizarRestauranteComSucesso() {
        Long id = 1L;

        EnderecoDTO enderecoDTO = new EnderecoDTO(1L, "Rua B", "456", "", "Bairro", "São Paulo", "SP", "02000-000");
        UsuarioRestauranteDTO usuarioDTO = new UsuarioRestauranteDTO(1L, "Felipe");
        RestauranteDTO dto = new RestauranteDTO(id, "Restaurante Atualizado", "Japonesa", "11h-23h", usuarioDTO, enderecoDTO);

        Usuario usuario = Usuario.create(usuarioDTO.id(), usuarioDTO.nome());
        Endereco endereco = Endereco.create(enderecoDTO.logradouro(), enderecoDTO.numero(), enderecoDTO.complemento(),
                enderecoDTO.bairro(), enderecoDTO.cidade(), enderecoDTO.estado(), enderecoDTO.cep());

        Restaurante restaurante = Restaurante.create(id, dto.nome(), dto.tipoCozinha(), dto.horarioFuncionamento(), usuario, endereco);

        when(usuarioGateway.buscarPorId(usuarioDTO.id())).thenReturn(usuario);
        when(restauranteGateway.buscarPorId(id)).thenReturn(restaurante);
        doNothing().when(restauranteGateway).atualizar(eq(id), any(Restaurante.class));

        useCase.executar(id, dto);

        verify(usuarioGateway, times(2)).buscarPorId(usuarioDTO.id());
        verify(restauranteGateway).buscarPorId(id);
        verify(restauranteGateway).atualizar(eq(id), any(Restaurante.class));
    }

}
