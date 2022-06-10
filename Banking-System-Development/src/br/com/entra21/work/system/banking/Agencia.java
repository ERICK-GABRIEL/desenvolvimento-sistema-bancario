package br.com.entra21.work.system.banking;

import java.util.ArrayList;

/*
  Esta � a classe Ag�ncia. Uma ag�ncia pertence a um banco somente, e possui
  uma ArrayList de objetos da classe Conta. Lembre-se de uma Conta pertece a
  somente uma ag�ncia, e uma ag�ncia pode ter in�meras contas.
*/

public class Agencia {
	private ArrayList<Conta> contas = new ArrayList<>(); // lista de contas
	private Banco banco; // o banco ao qual essa ag�ncia pertence
	private int id; // identifica��o da ag�ncia no sistema. N�o pode repetir
	private String numero; // n�mero da ag�ncia, geralmente com d�gito
	private String cidade; // cidade da ag�ncia. Voc� pode colocar mais informa��es aqui
	public static int contadorAgencias = 0; // auto incremento para o identificador de cada ag�ncia

	// construtor vazio
	public Agencia() {

	}

	// construtor personalizado
	public Agencia(Banco banco, int id, String numero, String cidade) {
		this.banco = banco;
		this.id = id;
		this.numero = numero;
		this.cidade = cidade;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
