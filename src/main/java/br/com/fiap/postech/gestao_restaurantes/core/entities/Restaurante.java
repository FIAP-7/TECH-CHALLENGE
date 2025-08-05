package br.com.fiap.postech.gestao_restaurantes.core.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class Restaurante {

	@Setter
	private Long id;
	@Setter
    private String nome;
	@Setter
    private String tipoCozinha;
	@Setter
    private String horarioFuncionamento;
    @Setter
    private Usuario usuario;
    @Setter
    private Endereco endereco;
    
	public static Restaurante create(String nome, String tipoCozinha, String horarioFuncionamento, Usuario usuario, Endereco endereco) {
		validarDados(nome, tipoCozinha, horarioFuncionamento, usuario, endereco);

		Restaurante restaurante = new Restaurante();
		
		restaurante.setNome(nome);
		restaurante.setTipoCozinha(tipoCozinha);
		restaurante.setHorarioFuncionamento(horarioFuncionamento);
		restaurante.setUsuario(usuario);
		restaurante.setEndereco(endereco);
		
		return restaurante;
	}

	private static void validarDados(String nome, String tipoCozinha, String horarioFuncionamento, Usuario usuario, Endereco endereco) {
		if (nome == null || tipoCozinha == null || horarioFuncionamento == null || usuario == null || endereco == null) {
			throw new IllegalArgumentException("Faltam dados");
		}
	}

	public static Restaurante create(Long id, String nome, String tipoCozinha, String horarioFuncionamento, Usuario usuario, Endereco endereco) {
		validarDados(nome, tipoCozinha, horarioFuncionamento, usuario, endereco);
		if (id == null) {
			throw new IllegalArgumentException("Faltam dados");
		}

		Restaurante restaurante = new Restaurante();
		
		restaurante.setId(id);
		restaurante.setNome(nome);
		restaurante.setTipoCozinha(tipoCozinha);
		restaurante.setHorarioFuncionamento(horarioFuncionamento);
		restaurante.setUsuario(usuario);
		restaurante.setEndereco(endereco);
		
		return restaurante;
	}

}
