package br.ifpe.edu.loja.apresentacao;

import java.util.List;
import java.util.Scanner;
import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;
import br.edu.ifpe.loja.negocio.ControladorVeiculo;
import br.edu.ifpe.loja.negocio.FabricaControlador;
import br.edu.ifpe.loja.negocio.IControladorVeiculo;


public class TelaVeiculo {

	Scanner scanner = new Scanner(System.in);
	

	public void exibir() {
		int opcao = 0;
		while (opcao != 5) { 
			System.out.println("Olá, bem vindo!");
			System.out.println("Digite 1 para cadastrar um veículo.");
			System.out.println("Digite 2 para editar um veículo.");
			System.out.println("Digite 3 para remover um veículo");
			System.out.println("Digite 4 para consultar um veículo.");
			System.out.println("Digite 5 para sair");

			try {
				opcao = Integer.parseInt(scanner.nextLine());
				if (opcao < 1 || opcao > 5) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				System.out.println("Digite um número válido!");
			}

			while (opcao < 0 || opcao > 5);

			if (opcao == 1) {
				try {
					this.inserir();
				} catch (ExcecaoNegocio e) {
					System.out.println("Erro ao inserir veículo: " + e.getMessage());
				}
			}
			//		} else if(opcao == 2) {
			//
			//		}else if(opcao == 3) {
			//		}
			else if(opcao == 4) {
				this.consultar();
			}

			else if(opcao != 5) {
				System.out.println("Invalido");
			}
		}
		System.out.println("Até a proxima");
	}

	private void inserir() throws ExcecaoNegocio {
		Veiculo veiculo = new Veiculo(this.lerString("modelo"), this.lerString("marca"), this.lerString("ano"));

		IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();
		controlador.inserir(veiculo);
		System.out.println("Veículo cadastrado com sucesso!");
	}

	private void consultar() {
        System.out.println("Digite o modelo do veículo que deseja consultar:");
        String modelo = scanner.nextLine();

        IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();
        
        try {
        	List<Veiculo> veiculosEncontrados = controlador.consultar(modelo);
            if (veiculosEncontrados.isEmpty()) {
                System.out.println("Nenhum veículo encontrado com o modelo informado.");
            } else {
                System.out.println("Veículos encontrados:");
                for (Veiculo veiculo : veiculosEncontrados) {
                    System.out.println(veiculo); 
                }
            }
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao consultar veículo: " + e.getMessage());
        }
    }



	private String lerString(String nomeAtributo) {
		String entrada = "";

		while (entrada.trim().length() == 0) {
			System.out.println("Infome: " + nomeAtributo + " do veículo: ");
			entrada = scanner.nextLine();
		}

		return entrada;

	}
}
