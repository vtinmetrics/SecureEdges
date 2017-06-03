package br.com.secureedges.domain;

import java.util.ArrayList;
import java.util.List;

public class Mes {
	List<Solicitacao> listSolicitacao;
	private String nome;
	private int numero;
	
	public Mes()
	{
		if(listSolicitacao == null)
		{
			listSolicitacao = new ArrayList<>();
		}
	}
	
	public void addSolicitacao(Solicitacao solicitacao)
	{
		listSolicitacao.add(solicitacao);
	}
	
	public List<Solicitacao> getListDispositivo() {
		if(listSolicitacao ==null)
			listSolicitacao = new ArrayList<>();
		return listSolicitacao;
	}
	public void setListDispositivo(List<Solicitacao> listSolicitacao) {
		this.listSolicitacao = listSolicitacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
}
