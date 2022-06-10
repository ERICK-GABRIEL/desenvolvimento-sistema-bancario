package br.com.entra21.work.system.banking;

import java.util.Date;

/*
  A classe Transacao registra todas as transa��es ocorridas nas contas
  banc�rias, ou seja, os dep�sitos, saques e transfer�ncias. Veja que cada
  conta possui um ArrayList de objetos da classe Transacao
*/

public class Transacao {
	private Conta conta; // conta a qual esta transa��o pertence
	private int id; // identifica unicamente esta transa��o
	private Date data; // data da transa��o
	private String historico; // descri��o da transa��o
	private double valor;
	private char letra; // d�bito ou cr�dito
	public static int contadorTransacoes = 0; // auto incremento para o identificador de cada transa��o

	// construtor vazio
	public Transacao() {

	}

	// construtor cheio
	public Transacao(Conta conta, int id, Date data, String historico, double valor, char letra) {
		this.conta = conta;
		this.id = id;
		this.data = data;
		this.historico = historico;
		this.valor = valor;
		this.letra = letra;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}
}