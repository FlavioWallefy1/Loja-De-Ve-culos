package  br.edu.ifpe.loja.apresentacao;

import java.util.List;
import java.util.Scanner;
import br.edu.ifpe.loja.entidades.*;
import br.edu.ifpe.loja.negocio.FabricaControlador;
import br.edu.ifpe.loja.negocio.IControladorVeiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;

public class TelaVeiculo {

    Scanner scanner = new Scanner(System.in);

    public void exibir() {
        int opcao = 0;
        while (opcao != 6) {  
            System.out.println("============================");
            System.out.println("Olá, bem vindo!");
            System.out.println("Digite 1 para cadastrar um veículo.");
            System.out.println("Digite 2 para editar um veículo.");
            System.out.println("Digite 3 para remover um veículo");
            System.out.println("Digite 4 para consultar um veículo.");
            System.out.println("Digite 5 para listar todos os veículos.");  
            System.out.println("Digite 6 para sair");  
            System.out.println("============================");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                if (opcao < 1 || opcao > 6) { 
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
                continue;
            }

            if (opcao == 1) {
                this.inserir();
            } else if (opcao == 2) {
                this.editar();
            } else if (opcao == 3) {
                this.remover();
            } else if (opcao == 4) {
                this.consultar();
            } else if (opcao == 5) {
                this.listarTodos();  
            } else if (opcao != 6) {
                System.out.println("Invalido");
            }
        }
        System.out.println("Até a próxima");
    }


    private void listarTodos() {
    	IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();
        List<Veiculo> veiculos = controlador.listarTodos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            System.out.println("============================");
            for (Veiculo veiculo : veiculos) {
                System.out.println("ID: " + veiculo.getId());
                System.out.println("Modelo: " + veiculo.getModelo());
                System.out.println("Marca: " + veiculo.getMarca());
                System.out.println("Ano de fabricação: " + veiculo.getAnoFabricacao());
                System.out.println("Ano do modelo: " + veiculo.getAnoModelo());
                System.out.println("Placa: " + veiculo.getPlaca());
                System.out.println("Preço: " + veiculo.getPreco());
                System.out.println("============================");
            }
        }
		
	}


	private void inserir() {
        Veiculo veiculo = new Veiculo.VeiculoBuilder()
                .modelo(this.lerString("modelo"))
                .marca(this.lerString("marca"))
                .anoFabricacao(Integer.parseInt(lerString("ano de fabricação")))
                .anoModelo(Integer.parseInt(lerString("ano do modelo")))
                .placa(this.lerString("placa"))
                .preco(Double.parseDouble(lerString("preço")))
                .criar();

        IVeiculo veiculoDecorado = adicionarAcessorios(veiculo);
        
        IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();
        try {
        	controlador.inserir(veiculo);
            System.out.println("Veículo cadastrado com sucesso!");
            System.out.println("ID do veículo: " + veiculo.getId());
            System.out.println("Preço final do veículo com acessórios: " + veiculoDecorado.getPreco());
        } catch (ExcecaoNegocio e) {
            System.out.println(e.getMessage());
        }
    }

	 private IVeiculo adicionarAcessorios(IVeiculo veiculo) {
	        System.out.println("Deseja adicionar algum acessório? (s/n)");
	        String resposta = scanner.nextLine();
	        IVeiculo veiculoDecorado = veiculo;

	        while (resposta.equalsIgnoreCase("s")) {
	            System.out.println("Informe a descrição do acessório:");
	            String descricao = scanner.nextLine();
	            System.out.println("Informe o preço do acessório:");
	            double preco = Double.parseDouble(scanner.nextLine());

	            veiculoDecorado = new BancoDeCouro(veiculoDecorado, preco, descricao);

	            System.out.println("Deseja adicionar outro acessório? (s/n)");
	            resposta = scanner.nextLine();
	        }

	        return veiculoDecorado;
	    }

    private void editar() {
        Long id = Long.parseLong(lerString("ID do veículo"));

        IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();
        System.out.println("============================");
        String novoModelo = lerString("novo modelo");
        String novaMarca = lerString("nova marca");
        int novoAnoFabricacao = Integer.parseInt(lerString("novo ano de fabricação"));
        int novoAnoModelo = Integer.parseInt(lerString("novo ano do modelo"));
        double novoPreco = Double.parseDouble(lerString("novo preço"));
        System.out.println("============================");

        Veiculo veiculo = new Veiculo.VeiculoBuilder()
                .id(id)
                .modelo(novoModelo)
                .marca(novaMarca)
                .anoFabricacao(novoAnoFabricacao)
                .anoModelo(novoAnoModelo)
                .preco(novoPreco)
                .placa(null)
                .criar();

        try {
            controlador.editar(veiculo);
            System.out.println("Veículo editado com sucesso!");
        } catch (ExcecaoNegocio e) {
            System.out.println(e.getMessage());
        }
    }

    private String lerString(String nomeAtributo) {
        String entrada = "";

        while (entrada.trim().length() == 0) {
            System.out.println("Informe: " + nomeAtributo + " do veículo: ");
            entrada = scanner.nextLine();
        }

        return entrada;
    }

    private void remover() {
        Long id = Long.parseLong(lerString("ID do veículo"));

        IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();

        try {
            controlador.remover(id);
            System.out.println("Veículo removido com sucesso!");
        } catch (ExcecaoNegocio e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultar() {
        Long id = Long.parseLong(lerString("ID do veículo"));

        IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();

        try {
            Veiculo veiculo = controlador.consultar(id);
            if (veiculo == null) {
                System.out.println("Nenhum veículo encontrado com o ID informado.");
            } else {
                System.out.println("============================");
                System.out.println("ID: " + veiculo.getId());
                System.out.println("Modelo: " + veiculo.getModelo());
                System.out.println("Marca: " + veiculo.getMarca());
                System.out.println("Ano de fabricação: " + veiculo.getAnoFabricacao());
                System.out.println("Ano do modelo: " + veiculo.getAnoModelo());
                System.out.println("Placa: " + veiculo.getPlaca());
                System.out.println("Preço: " + veiculo.getPreco());
                System.out.println("============================");
            }
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao consultar veículo: " + e.getMessage());
        }
        
        
    }
}
