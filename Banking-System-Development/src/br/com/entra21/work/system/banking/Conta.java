package br.com.entra21.work.system.banking;

import java.util.ArrayList;

/*
  Esta � a classe Conta. Ela est� associada a uma �nica ag�ncia
  e a um �nico cliente. Lembre-se: um banco pode ter v�rias ag�ncias
  e uma ag�ncia pode ter v�rias contas, assim como um cliente pode ter
  v�rias contas.
  Uma conta pode ter v�rias transa��es, ou seja, um ArrayList de objetos da
  classe Transacao
*/

public class Conta {
	private Agencia agencia; // ag�ncia associada a esta conta
	private Pessoa cliente; // pessoa associada a esta conta
	private ArrayList<Transacao> transacoes = new ArrayList<>(); // transa��es associadas a esta conta
	private int id; // identifica unicamente esta conta
	private String numero; // n�mero e d�gito da conta
	private double saldo;
	private double limite;
	public static int contadorContas = 0; // auto incremento para o identificador de cada conta

	// construtor vazio
	public Conta() {

	}

	// construtor cheio
	public Conta(Agencia agencia, Pessoa cliente, int id, String numero, double saldo, double limite) {
		this.agencia = agencia;
		this.cliente = cliente;
		this.id = id;
		this.numero = numero;
		this.saldo = saldo;
		this.limite = limite;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(ArrayList<Transacao> transacoes) {
		this.transacoes = transacoes;
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

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}
}
