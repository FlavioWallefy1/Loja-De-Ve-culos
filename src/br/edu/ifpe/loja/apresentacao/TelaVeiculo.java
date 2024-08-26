package br.edu.ifpe.loja.apresentacao;

import br.com.caelum.stella.validation.InvalidStateException;
import br.com.caelum.stella.validation.RenavamValidator;
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
import br.edu.ifpe.loja.util.ValidadorPlaca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TelaVeiculo {
	Scanner scanner = new Scanner(System.in);
	Facade facade = Facade.getInstancia();


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
	    int anoFabricacao;

	    do {
	        anoFabricacao = this.lerInt("ano de fabricação");
	        if (String.valueOf(anoFabricacao).length() != 4) {
	            System.out.println("Ano de fabricação deve ter 4 dígitos. Por favor, insira novamente.");
	        } else if (anoFabricacao > LocalDate.now().getYear()) {
	            System.out.println("Ano de fabricação não pode ser maior que o ano atual. Por favor, insira novamente.");
	        }
	    } while (String.valueOf(anoFabricacao).length() != 4 || anoFabricacao > LocalDate.now().getYear());

	    int anoModelo;
	    do {
	        anoModelo = this.lerInt("ano do modelo");
	        if (anoModelo < anoFabricacao || anoModelo > anoFabricacao + 1) {
	            System.out.println("Ano do modelo deve ser igual ao ano de fabricação ou, no máximo, um ano a mais. Por favor, insira novamente.");
	        }
	    } while (anoModelo < anoFabricacao || anoModelo > anoFabricacao + 1);

	    String placa;
	    do {
	        placa = this.lerString("placa").toUpperCase().replaceAll("[^A-Z0-9]", "");

	        if (placa.matches("^[A-Z]{3}\\d{4}$")) {
	            placa = placa.substring(0, 3) + "-" + placa.substring(3);
	        }

	        if (!ValidadorPlaca.validarPlaca(placa)) {
	            System.out.println("Placa inválida! Por favor, insira uma placa no formato correto (Antigo: AAA-1234 ou Mercosul: AAA1A23).");
	        }

	    } while (!ValidadorPlaca.validarPlaca(placa));

	    double preco = this.lerDouble("preço");

	    boolean entrada = false;
	    int teveSinistro = 0;
	    String dataSinistro = null;
	    while (!entrada) {
	        try {
	            System.out.println("O veículo tem algum sinistro?");
	            System.out.println("1. Sim");
	            System.out.println("2. Não");
	            teveSinistro = Integer.parseInt(scanner.nextLine());

	            if (teveSinistro == 1) {
	                dataSinistro = lerDataSinistro(anoFabricacao);
	            }

	            entrada = true;
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada inválida! Por favor, insira 1 para Sim ou 2 para Não.");
	        }
	    }

	    String renavam = "";
	    boolean renavamValido = false;
	    while (!renavamValido) {
	        renavam = this.lerString("RENAVAM");
	        try {
	            RenavamValidator validator = new RenavamValidator();
	            validator.assertValid(renavam);
	            renavamValido = true;
	        } catch (InvalidStateException e) {
	            System.out.println("Renavam inválido: " + e.getMessage() + ". Por favor, insira novamente.");
	        }
	    }

	    Veiculo veiculo;
	    if (tipoVeiculo == 1) {
	        veiculo = new Carro(modelo, marca, anoFabricacao, anoModelo, placa, preco, dataSinistro, renavam);
	    } else {
	        veiculo = new Moto(modelo, marca, anoFabricacao, anoModelo, placa, preco, dataSinistro, renavam);
	    }

	    IVeiculo veiculoDecorado = adicionarAcessorios(veiculo);
	    veiculo.setPreco(veiculoDecorado.getPreco());

	    try {
	        facade.inserir(veiculo);
	        System.out.println("Veículo cadastrado com sucesso!");
	        System.out.println("ID do veículo: " + veiculo.getId());
	        System.out.println("Preço final do veículo: " + veiculo.getPreco());
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
		
		int novoAnoFabricacao;
		do {
			novoAnoFabricacao = this.lerInt("novo ano de fabricação");
			if (String.valueOf(novoAnoFabricacao).length() != 4) {
                System.out.println("Ano de fabricação deve ter 4 dígitos. Por favor, insira novamente.");
            } else if (novoAnoFabricacao > LocalDate.now().getYear()) {
				System.out.println("Ano de fabricação não pode ser maior que o ano atual. Por favor, insira novamente.");
			}
		} while (novoAnoFabricacao > LocalDate.now().getYear());
		
		
		int novoAnoModelo;
		do {
			novoAnoModelo = this.lerInt("novo ano do modelo");
			if (novoAnoModelo < novoAnoFabricacao || novoAnoModelo > novoAnoFabricacao + 1) {
				System.out.println("Ano do modelo deve ser igual ao ano de fabricação ou, no máximo, um ano a mais. Por favor, insira novamente.");
			}
		} while (String.valueOf(novoAnoFabricacao).length() != 4 || novoAnoFabricacao > LocalDate.now().getYear());


		double novoPreco = this.lerDouble("novo preço");
		
		
		boolean entrada = false;
        int teveSinistro = 0;
        String novaDataSinistro = null;
        while (!entrada) {
            try {
                System.out.println("O veículo tem algum sinistro?");
                System.out.println("1. Sim");
                System.out.println("2. Não");
                teveSinistro = Integer.parseInt(scanner.nextLine());

                if (teveSinistro == 1) {
                    novaDataSinistro = lerDataSinistro(novoAnoFabricacao);
                }

                entrada = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, insira 1 para Sim ou 2 para Não.");
            }
        }
		
		
		System.out.println("============================");

		veiculoExistente.setModelo(novoModelo);
		veiculoExistente.setMarca(novaMarca);
		veiculoExistente.setAnoFabricacao(novoAnoFabricacao);
		veiculoExistente.setAnoModelo(novoAnoModelo);
		veiculoExistente.setPreco(novoPreco);
		veiculoExistente.setDataSinistro(novaDataSinistro);
		


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
    System.out.println("Informações do Veículo:");
    System.out.println("Modelo: " + veiculo.getModelo());
    System.out.println("Marca: " + veiculo.getMarca());
    System.out.println("Ano de Fabricação: " + veiculo.getAnoFabricacao());
    System.out.println("Ano do Modelo: " + veiculo.getAnoModelo());
    System.out.println("Placa: " + veiculo.getPlaca());
    System.out.println("Preço: " + veiculo.getPreco());
    System.out.println("Renavam: " + veiculo.getRenavam());
    System.out.println("---------------------------");

	if (veiculo instanceof Carro) {
		System.out.println("Tipo: Carro");

	} else if (veiculo instanceof Moto) {
		System.out.println("Tipo: Moto");

	}

	if (veiculo.getDataSinistro() != null) {
		System.out.println("Data do sinistro: " + veiculo.getDataSinistro());
		System.out.println("Data do sinistro em extenço: " + veiculo.getDataSinistroExtenso());

	}  else {
		System.out.println("Data do Sinistro: Não houve sinistro");
	}

	System.out.println("Documentação: " + veiculo.verificarDocumentacao());
    System.out.println("Preparando Veiculo: " + veiculo.prepararVeiculo());
    
    
}

private String lerString(String nomeAtributo) {
	String entrada = "";

	while (entrada.trim().isEmpty()) {
		System.out.println("Informe: " + nomeAtributo);
		entrada = scanner.nextLine();
	}

	return entrada;
}



private int lerInt(String nomeAtributo) {
	int numero = 0;
	boolean valido = false;

	while (!valido) {
		try {
			System.out.println("Informe: " + nomeAtributo);
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
			System.out.println("Informe: " + nomeAtributo);
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
			System.out.println("Informe: " + nomeAtributo);
			numero = Long.parseLong(scanner.nextLine());
			valido = true;
		} catch (NumberFormatException e) {
			System.out.println("Valor inválido! Por favor, insira um número.");
		}
	}

	return numero;
}



private String lerDataSinistro(int anoFabricacao) {
	String data = null;
	boolean valido = false;

	while (!valido) {
		System.out.println("Digite a data do sinistro (no formato Ano-Mês-Dia): ");
		String input = scanner.nextLine();
		
		try {
			LocalDate dataSinistro = LocalDate.parse(input);
			int anoSinistro = dataSinistro.getYear();
			int anoAtual = LocalDate.now().getYear();
			

			if (anoSinistro > anoAtual) {
				System.out.println("O ano do sisnitro não pode ser maior que o ano atual.");
			} else if (anoSinistro < anoFabricacao) {
                System.out.println("A data do sinistro não pode ser menor que o ano de fabricação do veículo.");
            } else {
				data = input;
				valido = true;
			}
		} catch (Exception ex) {
			System.out.println("Data inválida! Use o formato Ano-Mês-Dia (por exemplo, 2020-01-31).");
		}
	}
	return data;
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
