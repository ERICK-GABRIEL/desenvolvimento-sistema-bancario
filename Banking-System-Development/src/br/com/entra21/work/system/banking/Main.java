package br.com.entra21.work.system.banking;

import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/*
  Esta é a classe principal do sistema. Ela possui ArrayList de objetos
  Pessoa e objetos da classe Banco. É nessa classe que temos
  o menu que nos permite criar os bancos e as pessoas. Note que uma pessoa
  só se torna um cliente de uma agência a partir do momento que ela é associada
  a uma conta bancária
*/

public class Main {
	private ArrayList<Pessoa> pessoas = new ArrayList<>(); // ArrayList de pessoas
	private ArrayList<Banco> bancos = new ArrayList<>(); // ArrayList de bancos
	Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		// chama o menu principal do sistema
		Main m = new Main();
		m.menuPrincipal();
	}

	// método que exibe o menu principal do sistema
	public void menuPrincipal() {
		while (true) {
			System.out.println("\n:: S I S T E M A   B A N C Á R I O ::\n");
			System.out.println("Bem-vindo(a) ao sistem. Escolha a opção desejada");
			System.out.println("1 - Administrar o Sistema");
			System.out.println("2 - Acessar como Cliente");
			System.out.println("3 - Sair");
			System.out.print("Sua opção: ");
			int opcao = Integer.parseInt(entrada.nextLine()); // lê a opção do usuário

			switch (opcao) {
			case 1:
				menuAdministrarSistema(); // chama o menu de administração
				break;
			case 2:
				menuCliente(); // chama o menu do cliente
				break;
			case 3:
				System.out.println("\nObrigado por usar o Sistema Bancário\n");
				System.exit(0);
			}
		}
	}

	// menu que permite administrar o sistema
	public int menuAdministrarSistema() {
		while (true) { // exibe continuamente o menu de opções
			System.out.println("\n:: A D M I N I S T R A Ç Ã O  D O  S I S T E M A ::\n");
			System.out.println("Escolha a opção desejada");
			System.out.println("1 - Gerenciar Bancos");
			System.out.println("2 - Gerenciar Pessoas");
			System.out.println("3 - Gerenciar Agências");
			System.out.println("4 - Gerenciar Contas");
			System.out.println("5 - Voltar Menu Anterior");
			System.out.print("Sua opção: ");
			int opcao = Integer.parseInt(entrada.nextLine()); // lê a opção do usuário

			switch (opcao) {
			case 1: // vamos gerenciar os bancos
				menuGerenciarBancos();
				break;
			case 2: // vamos gerenciar as pessoas (que serão futuros clientes)
				menuGerenciarPessoas();
				break;
			case 3: // vamos gerenciar as agências de um determinado banco
				menuGerenciarAgencias();
				break;
			case 4: // vamos gerenciar as contas de uma determinada agência de um determinado banco
				menuGerenciarContas();
				break;
			case 5:
				return 1; // volta para o menu principal
			}
		}
	}

	// menu para cadatrar, listar, excluir e atualizar os bancos
	public int menuGerenciarBancos() {
		Banco temp; // serve para várias operações neste menu
		String pesquisaBanco; // serve para as pesquisas dos bancos

		while (true) { // mostra o menu de forma repetitiva até o usuário usar a opção de sair
			System.out.println("\n:: G E R E N C I A R   B A N C O S ::\n");
			System.out.println("Escolha a opção desejada");
			System.out.println("1 - Novo Banco");
			System.out.println("2 - Listar Bancos");
			System.out.println("3 - Pesquisar Banco");
			System.out.println("4 - Excluir Banco");
			System.out.println("5 - Atualizar Banco");
			System.out.println("6 - Voltar Menu Anterior");
			System.out.print("Sua opção: ");
			int opcao = Integer.parseInt(entrada.nextLine()); // lê a opção do usuário

			switch (opcao) {
			case 1: // vamos cadastrar um novo banco
				System.out.print("\nNúmero do Banco: ");
				String numeroBanco = entrada.nextLine();
				System.out.print("Nome do Banco: ");
				String nomeBanco = entrada.nextLine();

				// vamos incrementar o contador de bancos
				Banco.contadorBancos++;

				// agora vamos criar um novo objeto da classe Banco
				Banco b = new Banco(Banco.contadorBancos, nomeBanco, numeroBanco);
				// e o adicionamos no ArrayList de bancos
				bancos.add(b);

				// e finalmente mostramos uma mensagem de sucesso.
				System.out.println("\nO banco foi criado com sucesso");

				break;

			case 2: // vamos listar os bancos cadastrados
				if (bancos.isEmpty()) {
					System.out.println("\nNão há nenhum banco cadastrado.");
				} else {
					for (int i = 0; i < bancos.size(); i++) {
						temp = bancos.get(i); // obtém o banco da iteração atual
						System.out.println("\nId: " + temp.getId());
						System.out.println("Número: " + temp.getNumero());
						System.out.println("Nome: " + temp.getNome());
						System.out.println("Quant Agências: " + temp.getAgencias().size());
					}
				}

				break;

			case 3: // vamos pesquisar um banco
				System.out.print("\nInforme o id, número ou nome do Banco: ");
				pesquisaBanco = entrada.nextLine();
				// chamamos o método que pesquisa o banco
				temp = pesquisarBanco(pesquisaBanco);
				if (temp == null) { // banco não encotrado
					System.out.println("\nO banco não foi encontrado.");
				} else {
					// mostra o banco encontrado
					System.out.println("\nId: " + temp.getId());
					System.out.println("Número: " + temp.getNumero());
					System.out.println("Nome: " + temp.getNome());
					System.out.println("Quant Agências: " + temp.getAgencias().size());
				}

				break;

			case 4: // vamos excluir um banco
				System.out.print("\nInforme o id, número ou nome do Banco a ser excluído: ");
				pesquisaBanco = entrada.nextLine();
				// chamamos o método que pesquisa o banco
				temp = pesquisarBanco(pesquisaBanco);
				if (temp == null) { // banco não encotrado
					System.out.println("\nO banco não foi encontrado.");
				} else {
					// vamos excluir este banco. Atenção. Ao excluir um banco, todas
					// suas agências e contas serão excluídas também
					bancos.remove(temp);
					System.out.println("\nBanco excluído com sucesso.");
				}

				break;

			case 5: // vamos atualizar um banco
				System.out.print("\nInforme o id, número ou nome do Banco a ser atualizado: ");
				pesquisaBanco = entrada.nextLine();
				// chamamos o método que pesquisa o banco
				temp = pesquisarBanco(pesquisaBanco);
				if (temp == null) { // banco não encotrado
					System.out.println("\nO banco não foi encontrado.");
				} else {
					// mostra o banco encontrado
					System.out.println("\nDados atuais deste banco:");
					System.out.println("\nId: " + temp.getId());
					System.out.println("Número: " + temp.getNumero());
					System.out.println("Nome: " + temp.getNome());
					System.out.println("Quant Agências: " + temp.getAgencias().size());

					System.out.println("\nInforme os novos dados:");
					System.out.print("\nNovo Número do Banco: ");
					String novoNumeroBanco = entrada.nextLine();
					System.out.print("Novo Nome do Banco: ");
					String novoNomeBanco = entrada.nextLine();

					// vamos atualizar os dados deste banco no ArrayList
					temp.setNome(novoNomeBanco);
					temp.setNumero(novoNumeroBanco);
					System.out.println("\nBanco atualizado com sucesso.");
				}

				break;

			case 6:
				return 0; // volta para o menu principal
			}
		}
	}

	// método que pesquisa um banco pelo id, número ou nome e retorna um objeto da
	// classe Banco
	public Banco pesquisarBanco(String pesquisaBanco) {
		Banco b = null;

		// este banco existe?
		for (int i = 0; i < bancos.size(); i++) {
			// pesquisa pelo id
			if (Integer.toString(bancos.get(i).getId()).equals(pesquisaBanco)) {
				return bancos.get(i);
			}
			// pesquisar por nome
			else if (bancos.get(i).getNome().contains(pesquisaBanco)) {
				return bancos.get(i);
			}
			// pesquisar pelo número
			else if (bancos.get(i).getNumero().contains(pesquisaBanco)) {
				return bancos.get(i);
			}
		}

		return b;
	}

	// menu para cadatrar, listar, pesquisar, excluir e atualizar as pessoas
	// (futuros clientes)
	public int menuGerenciarPessoas() {
		Pessoa temp; // serve para várias operações neste menu
		String pesquisaPessoa; // serve para as pesquisas das pessoas

		while (true) { // mostra o menu de forma repetitiva até o usuário usar a opção de sair
			System.out.println("\n:: G E R E N C I A R   P E S S O A S ::\n");
			System.out.println("Escolha a opção desejada");
			System.out.println("1 - Nova Pessoa (Futuro Cliente)");
			System.out.println("2 - Listar Pessoas");
			System.out.println("3 - Pesquisar Pessoa");
			System.out.println("4 - Excluir Pessoa");
			System.out.println("5 - Atualizar Pessoa");
			System.out.println("6 - Voltar Menu Anterior");
			System.out.print("Sua opção: ");
			int opcao = Integer.parseInt(entrada.nextLine()); // lê a opção do usuário

			switch (opcao) {
			case 1: // vamos cadastrar uma nova pessoa
				System.out.print("\nNome: ");
				String nomePessoa = entrada.nextLine();
				System.out.print("Idade: ");
				int idadePessoa = Integer.parseInt(entrada.nextLine());
				System.out.print("Sexo: ");
				char sexoPessoa = entrada.nextLine().charAt(0);

				// vamos incrementar o contador de pessoas
				Pessoa.contadorPessoas++;

				// agora vamos criar um novo objeto da classe Pessoa
				Pessoa p = new Pessoa(Pessoa.contadorPessoas, nomePessoa, idadePessoa, sexoPessoa);
				// e o adicionamos no ArrayList de pessoas
				pessoas.add(p);

				// e finalmente mostramos uma mensagem de sucesso.
				System.out.println("\nA pessoa foi criada com sucesso");

				break;

			case 2: // vamos listar as pessoas cadastradas
				if (pessoas.isEmpty()) {
					System.out.println("\nNão há nenhuma pessoa cadastrada.");
				} else {
					for (int i = 0; i < pessoas.size(); i++) {
						temp = pessoas.get(i); // obtém a pessoa da iteração atual
						System.out.println("\nId: " + temp.getId());
						System.out.println("Nome: " + temp.getNome());
						System.out.println("Idade: " + temp.getIdade());
						System.out.println("Sexo: " + temp.getSexo());
						System.out.println("Quant Contas Bancárias: " + temp.getContas(bancos).size());
					}
				}
				break;

			case 3: // vamos pesquisar uma pessoa
				System.out.print("\nInforme o id ou nome da pessoa: ");
				pesquisaPessoa = entrada.nextLine();
				// chamamos o método que pesquisa a pessoa
				temp = pesquisarPessoa(pesquisaPessoa);
				if (temp == null) { // pessoa não encotrado
					System.out.println("\nA pessoa não foi encontrada.");
				} else {
					// mostra a pessoa encontrada
					System.out.println("\nId: " + temp.getId());
					System.out.println("Nome: " + temp.getNome());
					System.out.println("Idade: " + temp.getIdade());
					System.out.println("Sexo: " + temp.getSexo());
					System.out.println("Quant Contas Bancárias: " + temp.getContas(bancos).size());
				}

				break;

			case 4: // vamos excluir uma pessoa
				System.out.print("\nInforme o id ou nome da pessoa a ser excluída: ");
				pesquisaPessoa = entrada.nextLine();
				// chamamos o método que pesquisa a pessoa
				temp = pesquisarPessoa(pesquisaPessoa);
				if (temp == null) { // pessoa não encontrada
					System.out.println("\nA pessoa não foi encontrada.");
				} else {
					// vamos excluir esta pessoa. Atenção: Se houver contas bancárias relacionadas
					// a esta pessoa, então a exclusão da conta bancária deverá ser feita primeiro
					if (temp.getContas(bancos).size() > 0) {
						System.out.println("\nOps! Esta pessoa possui contas bancárias. Exclua as contas primeiro.");
					} else {
						pessoas.remove(temp);
						System.out.println("\nPessoa excluída com sucesso.");
					}
				}

				break;

			case 5: // vamos atualizar uma pessoa
				System.out.print("\nInforme o id ou nome da pessoa a ser atualizada: ");
				pesquisaPessoa = entrada.nextLine();
				// chamamos o método que pesquisa a pessoa
				temp = pesquisarPessoa(pesquisaPessoa);
				if (temp == null) { // pessoa não encotrado
					System.out.println("\nA pessoa não foi encontrada.");
				} else {
					// mostra a pessoa encontrada
					System.out.println("\nDados atuais desta pessoa:");
					System.out.println("\nId: " + temp.getId());
					System.out.println("Nome: " + temp.getNome());
					System.out.println("Idade: " + temp.getIdade());
					System.out.println("Sexo: " + temp.getSexo());
					System.out.println("Quant Contas Bancárias: " + temp.getContas(bancos).size());

					System.out.println("\nInforme os novos dados:");
					System.out.print("\nNovo Nome: ");
					String novoNomePessoa = entrada.nextLine();
					System.out.print("Nova Idade: ");
					int novaIdadePessoa = Integer.parseInt(entrada.nextLine());
					System.out.print("Novo Sexo: ");
					char novoSexoPessoa = entrada.nextLine().charAt(0);

					// vamos atualizar os dados desta pessoa no ArrayList
					temp.setNome(novoNomePessoa);
					temp.setIdade(novaIdadePessoa);
					temp.setSexo(novoSexoPessoa);
					System.out.println("\nPessoa atualizada com sucesso.");
				}

				break;

			case 6:
				return 0; // volta para o menu principal
			}
		}
	}

	// método que pesquisa uma pessoa pelo id, número ou nome e retorna um objeto da
	// classe Pessoa
	public Pessoa pesquisarPessoa(String pesquisaPessoa) {
		Pessoa p = null;

		// esta pessoa existe?
		for (int i = 0; i < pessoas.size(); i++) {
			// pesquisa pelo id
			if (Integer.toString(pessoas.get(i).getId()).equals(pesquisaPessoa)) {
				return pessoas.get(i);
			}
			// pesquisar por nome
			else if (pessoas.get(i).getNome().contains(pesquisaPessoa)) {
				return pessoas.get(i);
			}
		}

		return p;
	}

	// menu para cadatrar, listar, pesquisar, excluir e atualizar as agências
	public int menuGerenciarAgencias() {
		Agencia temp; // serve para várias operações neste menu
		String pesquisaAgencia; // serve para as pesquisas das agências
		Banco bancoAtual = null; // guarda o banco atual

		// para gerenciar uma agência nós precisamos de um banco
		while (bancoAtual == null) {
			System.out.print("\nInforme o id, número ou nome do banco: ");
			String pesquisaBanco = entrada.nextLine();
			// chamamos o método que pesquisa o banco
			Banco b = pesquisarBanco(pesquisaBanco);
			if (b == null) { // banco não encotrado
				System.out.print(
						"\nO banco não foi encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
				int opcao = Integer.parseInt(entrada.nextLine());
				if (opcao == 2) {
					return 1; // saímos daqui e voltamos para o menu anterior
				}
			} else {
				// banco encontrado. Vamos prosseguir com as agências
				bancoAtual = b;
			}
		}

		// Atenção: o menu abaixo deverá ser exibido somente se um banco for selecionado

		while (true) { // mostra o menu de forma repetitiva até o usuário usar a opção de sair
			System.out.println("\n:: G E R E N C I A R   A G Ê N C I A S ::\n");
			System.out.println("Banco selecionado: " + bancoAtual.getNome() + "\n");

			System.out.println("Escolha a opção desejada");
			System.out.println("1 - Nova Agência");
			System.out.println("2 - Listar Agências");
			System.out.println("3 - Pesquisar Agência");
			System.out.println("4 - Excluir Agência");
			System.out.println("5 - Atualizar Agência");
			System.out.println("6 - Voltar Menu Anterior");
			System.out.print("Sua opção: ");
			int opcao = Integer.parseInt(entrada.nextLine()); // lê a opção do usuário

			switch (opcao) {
			case 1: // vamos cadastrar uma nova agência
				System.out.print("\nNúmero da Agência: ");
				String numeroAgencia = entrada.nextLine();
				System.out.print("Cidade/Estado da Agência: ");
				String cidadeAgencia = entrada.nextLine();

				// vamos incrementar o contador de agências
				Agencia.contadorAgencias++;

				// agora vamos criar um novo objeto da classe Agencia
				Agencia a = new Agencia(bancoAtual, Agencia.contadorAgencias, numeroAgencia, cidadeAgencia);
				// e o adicionamos no ArrayList de agencias do banco selecionado
				bancoAtual.getAgencias().add(a);

				// e finalmente mostramos uma mensagem de sucesso.
				System.out.println("\nA Agência foi criada com sucesso");

				break;

			case 2: // vamos listar as agências cadastradas no banco selecionado
				if (bancoAtual.getAgencias().isEmpty()) {
					System.out.println("\nNão há nenhuma agência cadastrada neste banco.");
				} else {
					for (int i = 0; i < bancoAtual.getAgencias().size(); i++) {
						temp = bancoAtual.getAgencias().get(i); // obtém a agência da iteração atual
						System.out.println("\nId: " + temp.getId());
						System.out.println("Número: " + temp.getNumero());
						System.out.println("Cidade/Estado: " + temp.getCidade());
						System.out.println("Quant Contas Bancárias: " + temp.getContas().size());
					}
				}

				break;

			case 3: // vamos pesquisar uma agência
				System.out.print("\nInforme o id, número ou cidade da agência: ");
				pesquisaAgencia = entrada.nextLine();
				// chamamos o método que pesquisa a agência
				temp = pesquisarAgencia(bancoAtual, pesquisaAgencia);
				if (temp == null) { // agência não encotrada
					System.out.println("\nA agência não foi encontrada.");
				} else {
					// mostra a agência encontrada
					System.out.println("\nId: " + temp.getId());
					System.out.println("Número: " + temp.getNumero());
					System.out.println("Cidade/Estado: " + temp.getCidade());
					System.out.println("Quant Contas Bancárias: " + temp.getContas().size());
				}

				break;

			case 4: // vamos excluir uma agência
				System.out.print("\nInforme o id, número ou cidade da agência: ");
				pesquisaAgencia = entrada.nextLine();
				// chamamos o método que pesquisa a agência
				temp = pesquisarAgencia(bancoAtual, pesquisaAgencia);
				if (temp == null) { // agência não encotrada
					System.out.println("\nA agência não foi encontrada.");
				} else {
					// vamos excluir esta agência. Atenção. Ao excluir uma agência, todas
					// suas contas serão excluídas também
					bancoAtual.getAgencias().remove(temp);
					System.out.println("\nAgência excluída com sucesso.");
				}

				break;

			case 5: // vamos atualizar uma agência
				System.out.print("\nInforme o id, número ou cidade da agência: ");
				pesquisaAgencia = entrada.nextLine();
				// chamamos o método que pesquisa a agência
				temp = pesquisarAgencia(bancoAtual, pesquisaAgencia);
				if (temp == null) { // agência não encotrada
					System.out.println("\nA agência não foi encontrada.");
				} else {
					// mostra a agência encontrada
					System.out.println("\nDados atuais desta agência:");
					System.out.println("\nId: " + temp.getId());
					System.out.println("Número: " + temp.getNumero());
					System.out.println("Cidade/Estado: " + temp.getCidade());
					System.out.println("Quant Contas Bancárias: " + temp.getContas().size());

					System.out.println("\nInforme os novos dados:");
					System.out.print("\nNovo Número da Agência: ");
					String novoNumeroAgencia = entrada.nextLine();
					System.out.print("Nova Cidade/Estado da Agência: ");
					String novaCidadeAgencia = entrada.nextLine();

					// vamos atualizar os dados desta agência no ArrayList do banco atual
					temp.setNumero(novoNumeroAgencia);
					temp.setCidade(novaCidadeAgencia);
					System.out.println("\nAgência atualizada com sucesso.");
				}

				break;

			case 6:
				return 0; // volta para o menu principal
			}
		}
	}

	// método que pesquisa uma agência pelo id, número ou cidade e retorna um objeto
	// da classe Agencia
	public Agencia pesquisarAgencia(Banco b, String pesquisaAgencia) {
		Agencia a = null;

		// esta agência existe?
		for (int i = 0; i < b.getAgencias().size(); i++) {
			// pesquisa a agência pelo id
			if (Integer.toString(b.getAgencias().get(i).getId()).equals(pesquisaAgencia)) {
				return b.getAgencias().get(i);
			}
			// pesquisar por número da agência
			else if (b.getAgencias().get(i).getNumero().contains(pesquisaAgencia)) {
				return b.getAgencias().get(i);
			}
			// pesquisar pela cidade da agência
			else if (b.getAgencias().get(i).getCidade().contains(pesquisaAgencia)) {
				return b.getAgencias().get(i);
			}
		}

		return a;
	}

	// menu para cadatrar, listar, pesquisar, excluir e atualizar as contas
	public int menuGerenciarContas() {
		Conta temp; // serve para várias operações neste menu
		String pesquisaConta; // serve para as pesquisas das contas
		Banco bancoAtual = null; // guarda o banco atual
		Agencia agenciaAtual = null; // guarda a agência atual

		// para gerenciar uma conta nós precisamos primeiro de um banco
		while (bancoAtual == null) {
			System.out.print("\nInforme o id, número ou nome do banco: ");
			String pesquisaBanco = entrada.nextLine();
			// chamamos o método que pesquisa o banco
			Banco b = pesquisarBanco(pesquisaBanco);
			if (b == null) { // banco não encotrado
				System.out.print(
						"\nO banco não foi encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
				int opcao = Integer.parseInt(entrada.nextLine());
				if (opcao == 2) {
					return 1; // saímos daqui e voltamos para o menu anterior
				}
			} else {
				// banco encontrado. Vamos prosseguir com as agências
				bancoAtual = b;
			}
		}

		// agora que já temos o banco, vamos selecionar a agência
		while (agenciaAtual == null) {
			System.out.print("\nInforme o id, número ou cidade da agência: ");
			String pesquisaAgencia = entrada.nextLine();
			// chamamos o método que pesquisa a agência
			Agencia a = pesquisarAgencia(bancoAtual, pesquisaAgencia);
			if (a == null) { // agência não encotrada
				System.out.print(
						"\nA Agência não foi encontrada.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
				int opcao = Integer.parseInt(entrada.nextLine());
				if (opcao == 2) {
					return 1; // saímos daqui e voltamos para o menu anterior
				}
			} else {
				// agência encontrada. Vamos prosseguir com as contas
				agenciaAtual = a;
			}
		}

		// Atenção: o menu abaixo deverá ser exibido somente se um banco e uma agência
		// forem selecionados

		while (true) { // mostra o menu de forma repetitiva até o usuário usar a opção de sair
			System.out.println("\n:: G E R E N C I A R   C O N T A S ::\n");
			System.out.println("Banco selecionado: " + bancoAtual.getNome());
			System.out.println(
					"Agência selecionada: " + agenciaAtual.getNumero() + " - " + agenciaAtual.getCidade() + "\n");

			System.out.println("Escolha a opção desejada");
			System.out.println("1 - Abertura de Nova Conta");
			System.out.println("2 - Listar Contas");
			System.out.println("3 - Pesquisar Conta");
			System.out.println("4 - Excluir Conta");
			System.out.println("5 - Atualizar Conta");
			System.out.println("6 - Voltar Menu Anterior");
			System.out.print("Sua opção: ");
			int opcao = Integer.parseInt(entrada.nextLine()); // lê a opção do usuário

			switch (opcao) {
			case 1: // vamos abrir/cadastrar uma nova conta
				System.out.print("\nNúmero da Conta: ");
				String numeroConta = entrada.nextLine();
				System.out.print("Limite da Conta: ");
				double limiteConta = Double.parseDouble(entrada.nextLine());

				// para abrir uma nova conta nós precisamos de um cliente
				Pessoa cliente = null; // o cliente para o qual uma nova conta será aberta
				while (cliente == null) {
					System.out.print("Informe o id ou nome do cliente: ");
					String pesquisaPessoa = entrada.nextLine();
					// chamamos o método que pesquisa a pessoa
					cliente = pesquisarPessoa(pesquisaPessoa);
					if (cliente == null) { // pessoa não encotrado
						System.out.print(
								"\nCliente não encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
						int opcaoTemp = Integer.parseInt(entrada.nextLine());
						if (opcaoTemp == 2) {
							return 1; // saímos daqui e voltamos para o menu anterior
						}
					}
				}

				// vamos incrementar o contador de contas
				Conta.contadorContas++;

				// agora vamos criar um novo objeto da classe Conta
				Conta c = new Conta(agenciaAtual, cliente, Conta.contadorContas, numeroConta, 0.0, limiteConta);
				// e o adicionamos no ArrayList da agência selecionada do banco selecionado
				agenciaAtual.getContas().add(c);

				// e finalmente mostramos uma mensagem de sucesso.
				System.out.println("\nUma nova conta foi criada para o cliente: " + cliente.getNome()
						+ "\ncom saldo inicial de R$ 0,00 e limite inicial de R$ " + limiteConta);

				break;

			case 2: // vamos listar as contas cadastradas para a agência e banco selecionado
				if (agenciaAtual.getContas().isEmpty()) {
					System.out.println("\nNão há nenhuma conta cadastrada nesta agência.");
				} else {
					for (int i = 0; i < agenciaAtual.getContas().size(); i++) {
						temp = agenciaAtual.getContas().get(i); // obtém a conta da iteração atual
						System.out.println("\nId da conta bancária: " + temp.getId());
						System.out.println("Número da conta: " + temp.getNumero());
						System.out.println("Cliente: " + temp.getCliente().getNome());
						System.out.println("Agência: " + agenciaAtual.getNumero() + " - " + agenciaAtual.getCidade());
						System.out.println("Banco: " + bancoAtual.getNumero() + " - " + bancoAtual.getNome());
						System.out.println("Saldo atual: " + temp.getSaldo());
						System.out.println("Limite atual: " + temp.getLimite());
					}
				}

				break;

			case 3: // vamos pesquisar uma conta
				System.out.print("\nInforme o id, número ou nome do cliente da conta: ");
				pesquisaConta = entrada.nextLine();
				// chamamos o método que pesquisa a conta
				temp = pesquisarConta(agenciaAtual, pesquisaConta);
				if (temp == null) { // conta não encotrada
					System.out.println("\nA conta não foi encontrada nesta agência.");
				} else {
					// mostra a conta encontrada
					System.out.println("\nId da conta bancária: " + temp.getId());
					System.out.println("Número da conta: " + temp.getNumero());
					System.out.println("Cliente: " + temp.getCliente().getNome());
					System.out.println("Agência: " + agenciaAtual.getNumero() + " - " + agenciaAtual.getCidade());
					System.out.println("Banco: " + bancoAtual.getNumero() + " - " + bancoAtual.getNome());
					System.out.println("Saldo atual: " + temp.getSaldo());
					System.out.println("Limite atual: " + temp.getLimite());
				}

				break;

			case 4: // vamos excluir uma conta
				System.out.print("\nInforme o id, número ou nome do cliente da conta: ");
				pesquisaConta = entrada.nextLine();
				// chamamos o método que pesquisa a conta
				temp = pesquisarConta(agenciaAtual, pesquisaConta);
				if (temp == null) { // conta não encotrada
					System.out.println("\nA conta não foi encontrada nesta agência.");
				} else {
					// vamos excluir esta conta desta agência.
					agenciaAtual.getContas().remove(temp);
					System.out.println("\nConta excluída com sucesso.");
				}

				break;

			case 5: // vamos atualizar uma conta
				System.out.print("\nInforme o id, número ou nome do cliente da conta: ");
				pesquisaConta = entrada.nextLine();
				// chamamos o método que pesquisa a conta
				temp = pesquisarConta(agenciaAtual, pesquisaConta);
				if (temp == null) { // conta não encotrada
					System.out.println("\nA conta não foi encontrada.");
				} else {
					// mostra a conta encontrada
					System.out.println("\nDados atuais desta agência:");
					System.out.println("\nId: " + temp.getId());
					System.out.println("Número da conta: " + temp.getNumero());
					System.out.println("Cliente: " + temp.getCliente().getNome());
					System.out.println("Agência: " + agenciaAtual.getNumero() + " - " + agenciaAtual.getCidade());
					System.out.println("Banco: " + bancoAtual.getNumero() + " - " + bancoAtual.getNome());
					System.out.println("Saldo atual: " + temp.getSaldo());
					System.out.println("Limite atual: " + temp.getLimite());

					System.out.println("\nInforme os novos dados da conta:");
					System.out.print("\nNovo Número da Conta: ");
					String novoNumeroConta = entrada.nextLine();
					System.out.print("Novo Limite da Conta: ");
					double novoLimiteConta = Double.parseDouble(entrada.nextLine());

					// vamos atualizar os dados desta conta no ArrayList da agência atual
					temp.setNumero(novoNumeroConta);
					temp.setLimite(novoLimiteConta);
					System.out.println("\nConta atualizada com sucesso.");
				}

				break;

			case 6:
				return 0; // volta para o menu principal
			}
		}
	}

	// método que pesquisa uma conta pelo id, número ou nome do cliente e retorna um
	// objeto da classe Conta
	public Conta pesquisarConta(Agencia agencia, String pesquisaConta) {
		Conta c = null;

		// esta conta existe?
		for (int i = 0; i < agencia.getContas().size(); i++) {
			// pesquisa a conta pelo id
			if (Integer.toString(agencia.getContas().get(i).getId()).equals(pesquisaConta)) {
				return agencia.getContas().get(i);
			}
			// pesquisar por número da conta
			else if (agencia.getContas().get(i).getNumero().contains(pesquisaConta)) {
				return agencia.getContas().get(i);
			}
			// pesquisar pelo nome do cliente
			else if (agencia.getContas().get(i).getCliente().getNome().contains(pesquisaConta)) {
				return agencia.getContas().get(i);
			}
		}

		return c;
	}

	// método que pesquisa uma conta somente pelo id
	public Conta pesquisarContaPorId(int id) {
		Conta c = null;

		// A pesquisa por id tem que percorrer todo o sistema bancário
		for (int i = 0; i < bancos.size(); i++) {
			// agora percorremos as agências de cada banco
			Banco banco = bancos.get(i); // representa o banco da iteração atual
			for (int j = 0; j < banco.getAgencias().size(); j++) {
				Agencia agencia = banco.getAgencias().get(j); // agência da iteração atual
				for (int x = 0; x < agencia.getContas().size(); x++) {
					Conta conta = agencia.getContas().get(x); // conta da iteração atual
					if (conta.getId() == id) {
						return conta;
					}
				}
			}
		}

		return c;
	}

	public int menuCliente() {
		int idConta; // vai servir para as operações nas contas
		Conta conta; // para operações nas contas também
		Transacao transacao; // para gerar o extrato das contas
		NumberFormat nf = NumberFormat.getCurrencyInstance(); // para formatar a moeda
		Format formato = new SimpleDateFormat("dd/MM/yyyy"); // para formatar a data

		// temos que autenticar o cliente no sistema
		Pessoa cliente = null;
		while (cliente == null) {

			System.out.print("\nPrezado(a) cliente, informe seu id ou nome: ");
			String pesquisaPessoa = entrada.nextLine();
			// chamamos o método que pesquisa a pessoa
			cliente = pesquisarPessoa(pesquisaPessoa);
			if (cliente == null) { // pessoa não encotrado
				System.out.print(
						"\nCliente não encontrado.\n\nDigite 1 para pesquisar novamente ou 2 para voltar ao menu anterior: ");
				int opcaoTemp = Integer.parseInt(entrada.nextLine());
				if (opcaoTemp == 2) {
					return 1; // saímos daqui e voltamos para o menu anterior
				}
			}
		}

		while (true) { // exibe continuamente o menu de opções
			System.out.println("\n:: A C E S S O   D O   C L I E N T E ::\n");
			System.out.println("Cliente Selecionado: " + cliente.getNome() + "\n");
			System.out.println("Escolha a opção desejada");
			System.out.println("1 - Listar Minhas Contas Bancárias");
			System.out.println("2 - Efetuar Depósito");
			System.out.println("3 - Efetuar Saque");
			System.out.println("4 - Efetuar Transferência");
			System.out.println("5 - Obter Extrato");
			System.out.println("6 - Voltar Menu Anterior");
			System.out.print("Sua opção: ");
			int opcao = Integer.parseInt(entrada.nextLine()); // lê a opção do usuário

			switch (opcao) {
			case 1: // vamos listar as contas bancárias relacionaas a esta cliente
				// percorremos o ArrayLista de contas do cliente atual
				for (int i = 0; i < cliente.getContas(bancos).size(); i++) {
					Conta contaAtualTemp = cliente.getContas(bancos).get(i); // conta da iteração atual
					System.out.println("\nId: " + contaAtualTemp.getId());
					System.out.println("Número da conta: " + contaAtualTemp.getNumero());
					System.out.println("Cliente: " + contaAtualTemp.getCliente().getNome());
					System.out.println("Agência: " + contaAtualTemp.getAgencia().getNumero() + " - "
							+ contaAtualTemp.getAgencia().getCidade());
					System.out.println("Banco: " + contaAtualTemp.getAgencia().getBanco().getNumero() + " - "
							+ contaAtualTemp.getAgencia().getBanco().getNome());
					System.out.println("Saldo atual: " + contaAtualTemp.getSaldo());
					System.out.println("Limite atual: " + contaAtualTemp.getLimite());
				}

				break;
			case 2: // vamos efetuar um novo depósito em uma das contas
				System.out.print("\nInforme o Id da Conta: ");
				idConta = Integer.parseInt(entrada.nextLine());
				System.out.print("Valor do Depósito: ");
				double valorDeposito = Double.parseDouble(entrada.nextLine());

				// primeiro temos que encontrar essa conta
				conta = pesquisarContaPorId(idConta);
				if (conta == null) { // conta não enconrada
					System.out.print("\nConta não encontrada. Não foi possível efetuar o depósito.\n");
				} else {
					conta.setSaldo(conta.getSaldo() + valorDeposito);
					// vamos gerar uma transação para o extrato
					Transacao.contadorTransacoes++;
					transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date(), "DEPOSITO",
							valorDeposito, 'C');
					conta.getTransacoes().add(transacao);
					System.out.print("\nDepósito efetuado com sucesso.\n");
				}

				break;

			case 3: // vamos efetuar um novo saque em uma das contas
				System.out.print("\nInforme o Id da Conta: ");
				idConta = Integer.parseInt(entrada.nextLine());
				System.out.print("Valor do Saque: ");
				double valorSaque = Double.parseDouble(entrada.nextLine());

				// primeiro temos que encontrar essa conta
				conta = pesquisarContaPorId(idConta);
				if (conta == null) { // conta não enconrada
					System.out.print("\nConta não encontrada. Não foi possível efetuar o saque.\n");
				} else {
					// a conta possui saldo ou limite suficiente?
					if (valorSaque > (conta.getSaldo() + conta.getLimite())) {
						System.out.print("\nSaldo e limite insuficientes para este saque.\n");
					} else {
						conta.setSaldo(conta.getSaldo() - valorSaque);
						// vamos gerar uma transação para o extrato
						Transacao.contadorTransacoes++;
						transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date(), "SAQUE", valorSaque,
								'D');
						conta.getTransacoes().add(transacao);
						System.out.print("\nSaque efetuado com sucesso.\n");
					}
				}

				break;

			case 4: // vamos efetuar uma nova transferência entre contas
				System.out.print("\nInforme o Id da Conta do Débito: ");
				idConta = Integer.parseInt(entrada.nextLine());
				System.out.print("Informe o Id da Conta do Crédito: ");
				int idContaCredito = Integer.parseInt(entrada.nextLine());
				System.out.print("Valor da Transferência: ");
				double valorTransferencia = Double.parseDouble(entrada.nextLine());

				// primeiro temos que encontrar a conta do débito, de onde sairá o dinheiro
				conta = pesquisarContaPorId(idConta);
				// agora temos que encontrar a conta do crédito, para onde irá o dinheiro
				Conta contaCredito = pesquisarContaPorId(idContaCredito);

				if (conta == null) { // conta de débito não enconrada
					System.out
							.print("\nConta de débito não foi encontrada. Não foi possível efetuar a transferência.\n");
				} else if (contaCredito == null) { // conta de crédito não enconrada
					System.out.print(
							"\nConta de crédito não foi encontrada. Não foi possível efetuar a transferência.\n");
				} else if (contaCredito.getId() == conta.getId()) { // transferência para a mesma conta?
					System.out.print("\nAs contas de débito e crédito não podem ser a mesma.\n");
				} else {
					// a conta de débito possui saldo ou limite suficiente?
					if (valorTransferencia > (conta.getSaldo() + conta.getLimite())) {
						System.out.print("\nSaldo e limite insuficientes para esta transferência.\n");
					} else {
						// tiramos dinheiro da conta do débito
						conta.setSaldo(conta.getSaldo() - valorTransferencia);
						// e adicionamos na conta de crédito
						contaCredito.setSaldo(contaCredito.getSaldo() + valorTransferencia);

						// vamos gerar uma transação para o extrato - débito transferência
						Transacao.contadorTransacoes++;
						transacao = new Transacao(conta, Transacao.contadorTransacoes, new Date(), "TRANSFERENCIA",
								valorTransferencia, 'D');
						conta.getTransacoes().add(transacao);

						// vamos gerar uma transação para o extrato - crédito transferência
						Transacao.contadorTransacoes++;
						transacao = new Transacao(contaCredito, Transacao.contadorTransacoes, new Date(),
								"TRANSFERENCIA", valorTransferencia, 'C');
						contaCredito.getTransacoes().add(transacao);

						System.out.print("\nTransferência efetuada com sucesso.\n");
					}
				}

				break;

			case 5: // vamos mostrar o extrato para a conta selecionada
				System.out.print("\nInforme o Id da Conta: ");
				idConta = Integer.parseInt(entrada.nextLine());

				// primeiro temos que encontrar essa conta
				conta = pesquisarContaPorId(idConta);
				if (conta == null) { // conta não enconrada
					System.out.print("\nConta não encontrada. Não foi possível efetuar o depósito.\n");
				} else {
					System.out.println("");
					System.out.println("        E X T R A T O   B A N C Á R I O         ");
					System.out.println("------------------------------------------------");
					System.out.println("Cliente: " + conta.getCliente().getNome());
					System.out.println("Banco: " + conta.getAgencia().getBanco().getNome());
					System.out.println(
							"Agência: " + conta.getAgencia().getNumero() + " - " + conta.getAgencia().getCidade());
					System.out.println("Conta: " + conta.getNumero());
					System.out.println("------------------------------------------------");
					System.out.println("DATA        HISTORICO           DOC        VALOR");
					System.out.println("------------------------------------------------");

					for (int i = 0; i < conta.getTransacoes().size(); i++) {
						Transacao t = conta.getTransacoes().get(i); // representa a transação da iteração atual

						String valorFormatado = nf.format(t.getValor());
						System.out.println(formato.format(t.getData()) + "  " + String.format("%-20s", t.getHistorico())
								+ String.format("%1$5s", t.getId()).replace(' ', '0')
								+ String.format("%10s", valorFormatado.replace("R$ ", "")) + t.getLetra());
					}

					System.out.println("------------------------------------------------");
					String valorFormatado = nf.format(conta.getSaldo());
					System.out.println("SALDO ATUAL:" + String.format("%36s", valorFormatado.replace("R$ ", "")));
					valorFormatado = nf.format(conta.getLimite());
					System.out.println("LIMITE:" + String.format("%41s", valorFormatado.replace("R$ ", "")));
					valorFormatado = nf.format(conta.getSaldo() + conta.getLimite());
					System.out.println("SALDO + LIMITE:" + String.format("%33s", valorFormatado.replace("R$ ", "")));
					System.out.println("------------------------------------------------");
				}

				break;

			case 6:
				return 1; // volta para o menu principal
			}
		}
	}

}
