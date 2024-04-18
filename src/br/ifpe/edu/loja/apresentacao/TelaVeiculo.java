package br.ifpe.edu.loja.apresentacao;

import java.util.Scanner;
import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.negocio.FabricaControlador;

public class TelaVeiculo {

	Scanner scanner = new Scanner(System.in);

	public void exibir() {
		int opcao = 0;
		do {
			System.out.println("Olá, bem vindo!");
			System.out.println("Digite 1 para cadastrar um veículo.");
			System.out.println("Digite 2 para editar um veículo.");
			System.out.println("Digite 3 para remover um veículo");
			System.out.println("Digite 4 para consultar um veículo.");
			System.out.println("Digite 5 para sair");

			try {
				opcao = Integer.parseInt(scanner.nextLine());
			} catch (ClassCastException e) {
				System.out.println("digite um número válido!");
			}
		}
		while (opcao < 0 || opcao > 5);

		if (opcao == 1) {
			this.inserir();
		} else if(opcao == 2) {

		}else if(opcao == 3) {

		}else if(opcao == 4) {

		}else if(opcao == 5) {

		}
	}

	private void inserir() {
		Veiculo veiculo = new Veiculo();

		veiculo.setModelo(this.lerString("modelo"));
		veiculo.setMarca(this.lerString("marca"));
		veiculo.setAno(this.lerString("ano"));

		IControladorVeiculoo controlador = FabricaControlador.getControladorVeiculo ();
		try {
			controlador.inserir(veiculo);
			System.out.println("Veiculo cadastardo com sucesso!");
		} catch (ExceptionNegocio e) {
			System.out.println(e.getMessege );
		}
	}
	private String lerString(String nomeAtributo) {
		String entrada = "";

		while (entrada.trim().length() == 0) {
			System.out.println("Digite o " + nomeAtributo + "  do aluno: ");
			entrada = scanner.nextLine();
		}

		return entrada;

	}
}
