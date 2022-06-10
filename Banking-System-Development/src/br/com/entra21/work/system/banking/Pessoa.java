package br.com.entra21.work.system.banking;

import java.io.Serializable;
import java.util.ArrayList;

/*
  Esta � a classe Pessoa. Uma pessoa pode estar associada a uma ou mais
  contas banc�rias
*/

public class Pessoa implements Serializable { // pode ser salvo e lido a partir de um arquivo
	private int id; // identifica��o da pessoa. N�o pode repetir
	private String nome;
	private int idade;
	private char sexo;
	public static int contadorPessoas = 0; // auto incremento para o identificador de cada pessoa

	// construtor vazio
	public Pessoa() {

	}

	// construtor com par�metros
	public Pessoa(int id, String nome, int idade, char sexo) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	// permite obter a lista de contas banc�rias. Note que n�s percorremos todos os
	// bancos, todas
	// as suas ag�ncias e suas contas para obter as contas pertencentes a este
	// cliente
	public ArrayList<Conta> getContas(ArrayList<Banco> bancos) {
		ArrayList<Conta> contas = new ArrayList<>();

		for (int i = 0; i < bancos.size(); i++) {
			// agora percorremos as ag�ncias de cada banco
			Banco banco = bancos.get(i); // representa o banco da itera��o atual
			for (int j = 0; j < banco.getAgencias().size(); j++) {
				Agencia agencia = banco.getAgencias().get(j); // ag�ncia da itera��o atual
				for (int x = 0; x < agencia.getContas().size(); x++) {
					Conta conta = agencia.getContas().get(x); // conta da itera��o atual
					if (conta.getCliente().getId() == this.getId()) {
						contas.add(conta);
					}
				}
			}
		}

		return contas;
	}
}
