package br.com.fiap.postech.gestao_restaurantes.infra.repositories;

import br.com.fiap.postech.gestao_restaurantes.core.dto.*;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.entity.*;
import br.com.fiap.postech.gestao_restaurantes.infra.persistence.repository.ItemCardapioJPARepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ItemCardapioRepositoryImplTest {

    @Mock
    private ItemCardapioJPARepository itemCardapioRepository;

    @Mock
    private EntityManager entityManager;

    private ItemCardapioRepositoryImpl itemCardapioRepositoryImpl;
    private AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        itemCardapioRepositoryImpl = new ItemCardapioRepositoryImpl(itemCardapioRepository);
        itemCardapioRepositoryImpl.setEntityManager(entityManager);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void deveCriarItemCardapioComSucesso() {
        when(itemCardapioRepository.save(any(ItemCardapioEntity.class))).thenReturn(getItemCardapioEntity());

        Long id = itemCardapioRepositoryImpl.criar(getNovoItemCardapioDTO());

        assertThat(id).isEqualTo(1L);
    }

    @Test
    void deveDeletarItemCardapioComSucesso() {
        when(itemCardapioRepository.findById(anyLong())).thenReturn(Optional.of(getItemCardapioEntity()));
        doNothing().when(itemCardapioRepository).deleteById(anyLong());

        itemCardapioRepositoryImpl.deletar(1L);

        verify(itemCardapioRepository).deleteById(1L);
    }

    @Test
    void deveAtualizarItemCardapioComSucesso() {
        when(itemCardapioRepository.findById(anyLong())).thenReturn(Optional.of(getItemCardapioEntity()));
        when(itemCardapioRepository.save(any(ItemCardapioEntity.class))).thenReturn(getItemCardapioEntity());

        itemCardapioRepositoryImpl.atualizar(1L, getItemCardapioDTO());

        verify(itemCardapioRepository).save(any(ItemCardapioEntity.class));
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        when(itemCardapioRepository.findById(anyLong())).thenReturn(Optional.of(getItemCardapioEntity()));

        Optional<ItemCardapioDTO> resultado = itemCardapioRepositoryImpl.buscarPorId(1L);

        assertThat(resultado).isPresent();
    }

    @Test
    void deveRetornarEmptyQuandoBuscarPorIdNaoEncontrado() {
        when(itemCardapioRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<ItemCardapioDTO> resultado = itemCardapioRepositoryImpl.buscarPorId(1L);

        assertThat(resultado).isEmpty();
    }

    private ItemCardapioEntity getItemCardapioEntity() {
        return ItemCardapioEntity.builder()
                .id(1L)
                .nome("Item Teste")
                .descricao("Descrição Teste")
                .preco(BigDecimal.valueOf(10.99))
                .disponivelApenasNoRestaurante(true)
                .foto("foto.jpg")
                .restaurante(getRestauranteEntity())
                .build();
    }

    private NovoItemCardapioDTO getNovoItemCardapioDTO() {
        return new NovoItemCardapioDTO(
                "Item Teste",
                "Descrição Teste",
                BigDecimal.valueOf(10.99),
                true,
                "foto.jpg",
                1L
        );
    }

    private ItemCardapioDTO getItemCardapioDTO() {
        return new ItemCardapioDTO(
                1L,
                "Item Teste",
                "Descrição Teste",
                BigDecimal.valueOf(10.99),
                true,
                "foto.jpg",
                getRestauranteDTO()
        );
    }

    public EnderecoEntity getEnderecoEntity() {
        return EnderecoEntity.builder()
                .id(1L)
                .logradouro("Rua Exemplo")
                .numero("123")
                .complemento("Apto 101")
                .bairro("Centro")
                .cidade("São Paulo")
                .estado("SP")
                .cep("12345-678")
                .build();
    }

    public UsuarioEntity getUsuarioEntity() {
        return UsuarioEntity.builder()
                .id(1L)
                .cpf("123.456.789-00")
                .nome("João Silva")
                .email("joao.silva@example.com")
                .login("joaosilva")
                .senha("senha123")
                .tipoUsuario(getTipoUsuarioEntity())
                .dataUltimaAlteracao(LocalDateTime.now())
                .endereco(getEnderecoEntity())
                .build();
    }

    public RestauranteEntity getRestauranteEntity() {
        return RestauranteEntity.builder()
                .id(1L)
                .nome("Restaurante Exemplo")
                .tipoCozinha("Italiana")
                .horarioFuncionamento("10:00 - 22:00")
                .usuario(getUsuarioEntity())
                .endereco(getEnderecoEntity())
                .build();
    }

    public TipoUsuarioEntity getTipoUsuarioEntity() {
        return TipoUsuarioEntity.builder()
                .id(1L)
                .nome("Administrador")
                .build();
    }

    public UsuarioRestauranteDTO getUsuarioRestauranteDTO() {
        return new UsuarioRestauranteDTO(1L, "João Silva");
    }

    public EnderecoDTO getEnderecoDTO() {
        return new EnderecoDTO(
                1L,
                "Rua Exemplo",
                "123",
                "Apto 101",
                "Centro",
                "São Paulo",
                "SP",
                "12345-678"
        );
    }

    private RestauranteDTO getRestauranteDTO() {
        return new RestauranteDTO(
                1L,
                "Restaurante Teste",
                "Comida Brasileira",
                "08:00 - 22:00",
                getUsuarioRestauranteDTO(),
                getEnderecoDTO()
        );
    }
}