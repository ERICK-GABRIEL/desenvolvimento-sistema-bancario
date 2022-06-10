package br.com.entra21.work.system.banking;

import java.util.ArrayList;

/*
  Esta � a classe Banco. A classe Sistema possui uma ArrayList de bancos
  e cada banco possui uma ArrayList de ag�ncias
*/

public class Banco {
	private ArrayList<Agencia> agencias = new ArrayList<>(); // lista de ag�ncias
	private int id; // identifica��o do banco no sistema. N�o pode repetir
	private String nome; // nome do banco
	private String numero; // n�mero do banco (104 Caixa Econ�mica, 001 Banco do Brasil, etc
	public static int contadorBancos = 0; // auto incremento para o identificador de cada banco

	// construtor vazio
	public Banco() {

	}

	public Banco(int id, String nome, String numero) {
		this.id = id;
		this.nome = nome;
		this.numero = numero;
	}

	// permite obter a lista de ag�ncias
	public ArrayList<Agencia> getAgencias() {
		return agencias;
	}

	// define a lista de ag�ncias. Raramente usado
	public void setAgencias(ArrayList<Agencia> agencias) {
		this.agencias = agencias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}