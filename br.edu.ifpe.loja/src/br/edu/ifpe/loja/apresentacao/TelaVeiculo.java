package br.edu.ifpe.loja.apresentacao;

import br.edu.ifpe.loja.entidades.ArCondicionado;
import br.edu.ifpe.loja.entidades.BancoDeCouro;
import br.edu.ifpe.loja.entidades.IVeiculo;
import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;
import br.edu.ifpe.loja.negocio.FabricaControlador;
import br.edu.ifpe.loja.negocio.IControladorVeiculo;
import java.util.List;
import java.util.Scanner;

public class TelaVeiculo {
    Scanner scanner = new Scanner(System.in);

    public void exibir() {
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("============================");
            System.out.println("Olá, bem vindo!");
            System.out.println("Digite 1 para cadastrar um veículo.");
            System.out.println("Digite 2 para editar um veículo.");
            System.out.println("Digite 3 para remover um veículo.");
            System.out.println("Digite 4 para consultar um veículo.");
            System.out.println("Digite 5 para listar todos os veículos.");
            System.out.println("Digite 6 para sair.");
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
        Veiculo veiculoBase = new Veiculo.VeiculoBuilder()
                .modelo(this.lerString("modelo"))
                .marca(this.lerString("marca"))
                .anoFabricacao(Integer.parseInt(lerString("ano de fabricação")))
                .anoModelo(Integer.parseInt(lerString("ano do modelo")))
                .placa(this.lerString("placa"))
                .preco(Double.parseDouble(lerString("preço")))
                .criar();

        IVeiculo veiculoDecorado = adicionarAcessorios(veiculoBase);

        IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();
        try {
            controlador.inserir(veiculoBase);
            System.out.println("Veículo cadastrado com sucesso!");
            System.out.println("ID do veículo: " + veiculoDecorado.getId());
            System.out.println("Preço final do veículo com acessórios: " + veiculoDecorado.getPreco());
        } catch (ExcecaoNegocio e) {
            System.out.println(e.getMessage());
        }
    }

    private IVeiculo adicionarAcessorios(IVeiculo veiculo) {
        while (true) {
            System.out.println("Deseja adicionar algum acessório? (s/n)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("n")) {
                break;
            }

            System.out.println("Escolha o acessório:");
            System.out.println("1. Banco de Couro");
            System.out.println("2. Ar Condicionado");
            int escolha = Integer.parseInt(scanner.nextLine());

            switch (escolha) {
                case 1:
                    veiculo = new BancoDeCouro(veiculo);
                    break;
                case 2:
                    veiculo = new ArCondicionado(veiculo);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        return veiculo;
    }

    private void editar() {
        Long id = Long.parseLong(lerString("ID do veículo"));

        IControladorVeiculo controlador = FabricaControlador.getControladorVeiculo();
        System.out.println("============================");
        String novoModelo = lerString("novo modelo");
        String novaMarca = lerString("nova marca");
        int novoAnoFabricacao = Integer.parseInt(lerString("novo ano de fabricação"));
        int novoAnoModelo = Integer.parseInt(lerString("novo ano do modelo"));
        System.out.println("============================");

        Veiculo veiculo = new Veiculo.VeiculoBuilder()
                .id(id)
                .modelo(novoModelo)
                .marca(novaMarca)
                .anoFabricacao(novoAnoFabricacao)
                .anoModelo(novoAnoModelo)
                .placa(null)
                .preco(0.0) // Preço base pode ser ajustado conforme necessário
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
