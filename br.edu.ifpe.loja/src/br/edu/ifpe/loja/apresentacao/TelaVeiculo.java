package br.edu.ifpe.loja.apresentacao;

import br.edu.ifpe.loja.entidades.ArCondicionado;
import br.edu.ifpe.loja.entidades.BancoDeCouro;
import br.edu.ifpe.loja.entidades.CambioAutomatico;
import br.edu.ifpe.loja.entidades.Carro;
import br.edu.ifpe.loja.entidades.IVeiculo;
import br.edu.ifpe.loja.entidades.Moto;
import br.edu.ifpe.loja.entidades.Roda;
import br.edu.ifpe.loja.entidades.Veiculo;
import br.edu.ifpe.loja.excecao.ExcecaoNegocio;
import br.edu.ifpe.loja.log.LogLojaVeiculos;
import br.edu.ifpe.loja.negocio.FabricaControlador;
import br.edu.ifpe.loja.negocio.Facade;
import br.edu.ifpe.loja.negocio.IControladorVeiculo;

import java.util.List;
import java.util.Scanner;

public class TelaVeiculo {
    Scanner scanner = new Scanner(System.in);
    Facade facade = new Facade();

    
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

            switch (opcao) {
                case 1:
                    this.inserir();
                    break;
                case 2:
                    this.editar();
                    break;
                case 3:
                    this.remover();
                    break;
                case 4:
                    this.consultar();
                    break;
                case 5:
                    this.listarTodos();
                    break;
                default:
                    break;
            }

        }
        System.out.println("Até a próxima");
    }



    private void listarTodos() {
        List<Veiculo> veiculos = facade.listarTodos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            LogLojaVeiculos.registrarMovimentacao("Erro ao listar todos os veiculos, Nenhum veiculo cadastrado.");
        } else {
            System.out.println("============================");
            for (Veiculo veiculo : veiculos) {
                exibirInformacoesVeiculo(veiculo);
                System.out.println("============================");
            }
            LogLojaVeiculos.registrarMovimentacao("Listar todos os veiculos com sucesso.");
        }
    }



    private void inserir() {
        int tipoVeiculo = 0;
        boolean entradaValida = false;
        
        // Loop para garantir que o usuário insira uma opção válida para o tipo de veículo
        while (!entradaValida) {
            try {
                System.out.println("Escolha o tipo de veículo:");
                System.out.println("1. Carro");
                System.out.println("2. Moto");
                tipoVeiculo = Integer.parseInt(scanner.nextLine());

                if (tipoVeiculo < 1 || tipoVeiculo > 2) {
                    throw new ExcecaoNegocio("Tipo de veículo inválido. Escolha 1 para Carro ou 2 para Moto.");
                }
                
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira 1 para Carro ou 2 para Moto.");
            } catch (ExcecaoNegocio e) {
                System.out.println(e.getMessage());
            }
        }

        String modelo = this.lerString("modelo");
        String marca = this.lerString("marca");
        int anoFabricacao = this.lerInt("ano de fabricação");
        int anoModelo;

        do {
            anoModelo = this.lerInt("ano do modelo");
            if (anoModelo < anoFabricacao || anoModelo > anoFabricacao + 1) {
                System.out.println("Ano do modelo deve ser igual ao ano de fabricação ou, no máximo, um ano a mais. Por favor, insira novamente.");
            }
        } while (anoModelo < anoFabricacao || anoModelo > anoFabricacao + 1);

        String placa = this.lerString("placa");
        double preco = this.lerDouble("preço");

        Veiculo veiculo;
        if (tipoVeiculo == 1) {
            veiculo = new Carro(modelo, marca, anoFabricacao, anoModelo, placa, preco);
        } else {
            veiculo = new Moto(modelo, marca, anoFabricacao, anoModelo, placa, preco);
        }

        IVeiculo veiculoDecorado = adicionarAcessorios(veiculo);
        veiculo.setPreco(veiculoDecorado.getPreco());

        try {
            facade.inserir(veiculo);
            System.out.println("Veículo cadastrado com sucesso!");
            System.out.println("ID do veículo: " + veiculo.getId());
            System.out.println("Preço final do veículo com acessórios: " + veiculo.getPreco());
            LogLojaVeiculos.registrarMovimentacao(String.format("Veiculo cadastrado com sucesso. ID: %d, Modelo: %s, Marca: %s, Preço: %s", veiculo.getId(), veiculo.getModelo(), veiculo.getMarca(), veiculo.getPreco()));
        } catch (ExcecaoNegocio e) {
            System.out.println(e.getMessage());
            LogLojaVeiculos.registrarMovimentacao("Erro ao cadastrar Veículo:" + e.getMessage());
        }
    }



    
    private void editar() {
        Long id = this.lerLong("ID do veículo");

        try {
            Veiculo veiculoExistente = facade.consultar(id);
            if (veiculoExistente == null) {
                System.out.println("Esse veículo não está cadastrado!");
                return;
            }

            System.out.println("============================");
            String novoModelo = lerString("novo modelo");
            String novaMarca = lerString("nova marca");
            int novoAnoFabricacao = this.lerInt("novo ano de fabricação");
            int novoAnoModelo;

            do {
                novoAnoModelo = this.lerInt("novo ano do modelo");
                if (novoAnoModelo < novoAnoFabricacao || novoAnoModelo > novoAnoFabricacao + 1) {
                    System.out.println("Ano do modelo deve ser igual ao ano de fabricação ou, no máximo, um ano a mais. Por favor, insira novamente.");
                }
            } while (novoAnoModelo < novoAnoFabricacao || novoAnoModelo > novoAnoFabricacao + 1);

 
            double novoPreco = this.lerDouble("novo preço");
            System.out.println("============================");

            veiculoExistente.setModelo(novoModelo);
            veiculoExistente.setMarca(novaMarca);
            veiculoExistente.setAnoFabricacao(novoAnoFabricacao);
            veiculoExistente.setAnoModelo(novoAnoModelo);
            veiculoExistente.setPreco(novoPreco);

            IVeiculo veiculoDecorado = adicionarAcessorios(veiculoExistente);
            veiculoExistente.setPreco(veiculoDecorado.getPreco());

            facade.editar(veiculoExistente);
            System.out.println("Veículo editado com sucesso!");
            LogLojaVeiculos.registrarMovimentacao("Veículo editado com sucesso. ID: " + veiculoExistente.getId());
        } catch (ExcecaoNegocio e) {
            System.out.println("Erro ao editar veículo: " + e.getMessage());
            LogLojaVeiculos.registrarMovimentacao("Erro ao editar veículo: " + e.getMessage());
        }
    }

    private void remover() {
        Long id = null;
        try {
            id = Long.parseLong(lerString("ID do veículo"));
        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Por favor, insira um número.");
            LogLojaVeiculos.registrarMovimentacao("Tentativa de remoção falhou devido a ID inválido.");
            return;
        }

        try {
            facade.remover(id);
            System.out.println("Veículo removido com sucesso!");
            LogLojaVeiculos.registrarMovimentacao("Veículo removido com sucesso. ID: " + id);
        } catch (ExcecaoNegocio e) {
            System.out.println(e.getMessage());
            LogLojaVeiculos.registrarMovimentacao("Erro ao remover veículo com ID: " + id + " - " + e.getMessage());
        }
    }

    private void consultar() {
        try {
            Long id = Long.parseLong(lerString("ID do veículo"));

            try {
                Veiculo veiculo = facade.consultar(id);
                if (veiculo == null) {
                    System.out.println("Nenhum veículo encontrado com o ID informado.");
                    LogLojaVeiculos.registrarMovimentacao("Veiculo com ID: " + id + " não encontrado");
                } else {
                    System.out.println("============================");
                    exibirInformacoesVeiculo(veiculo);
                    System.out.println("============================");
                    LogLojaVeiculos.registrarMovimentacao("Veiculo com ID: " + id + " encontrado");
                }
            } catch (ExcecaoNegocio e) {
                System.out.println("Erro ao consultar veículo: " + e.getMessage());
                LogLojaVeiculos.registrarMovimentacao("Erro ao consultar veiculo com ID: " + id + " - " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido! Por favor, digite um número.");
            LogLojaVeiculos.registrarMovimentacao("Erro: ID inválido digitado");
        }
    }


    private void exibirInformacoesVeiculo(Veiculo veiculo) {
        System.out.println("ID: " + veiculo.getId());
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Marca: " + veiculo.getMarca());
        System.out.println("Ano: " + veiculo.getAnoModelo());
        System.out.println("Preço: " + veiculo.getPreco());

        if (veiculo instanceof Carro) {
            System.out.println("Tipo: Carro");
            // Exibe informações específicas do carro, se houver
        } else if (veiculo instanceof Moto) {
            System.out.println("Tipo: Moto");
            // Exibe informações específicas da moto, se houver
        }

        System.out.println("Método de Preparação: " + veiculo.prepararVeiculo());
        
        }



    private String lerString(String nomeAtributo) {
        String entrada = "";

        while (entrada.trim().isEmpty()) {
            System.out.println("Informe: " + nomeAtributo + " do veículo: ");
            entrada = scanner.nextLine();
        }

        return entrada;
    }



    private int lerInt(String nomeAtributo) {
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.println("Informe: " + nomeAtributo + " do veículo: ");
                numero = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Por favor, insira um número inteiro.");
            }
        }

        return numero;
    }



    private double lerDouble(String nomeAtributo) {
        double numero = 0.0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.println("Informe: " + nomeAtributo + " do veículo: ");
                numero = Double.parseDouble(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Por favor, insira um número decimal.");
            }
        }

        return numero;
    }



    private Long lerLong(String nomeAtributo) {
        Long numero = 0L;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.println("Informe: " + nomeAtributo + " do veículo: ");
                numero = Long.parseLong(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Por favor, insira um número.");
            }
        }

        return numero;
    }



    private IVeiculo adicionarAcessorios(IVeiculo veiculo) {
        while (true) {
            try {
                System.out.println("Escolha o acessório:");
                System.out.println("1. Banco de Couro: R$ 1500");
                System.out.println("2. Ar Condicionado: R$ 2000");
                System.out.println("3. Cambio Automatico: R$ 5000");
                System.out.println("4. Roda de liga leve: R$ 1000");
                System.out.println("0. Finalizar");
                int escolha = Integer.parseInt(scanner.nextLine());
    
                switch (escolha) {
                    case 0:
                        System.out.println("Finalizado a adição de comportamentos.");
                        return veiculo;
                    case 1:
                        veiculo = new BancoDeCouro(veiculo);
                        break;
                    case 2:
                        veiculo = new ArCondicionado(veiculo);
                        break;
                    case 3:
                        veiculo = new CambioAutomatico(veiculo);
                        break;
                    case 4:
                        veiculo = new Roda(veiculo);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }
        }
    }
    

}