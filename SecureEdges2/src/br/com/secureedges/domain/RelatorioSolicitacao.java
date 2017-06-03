package br.com.secureedges.domain;

import java.util.ArrayList;
import java.util.List;

public class RelatorioSolicitacao extends EntidadeDominio {
	private List<Mes> meses;
	private String titulo;
	private String[] meses_nome = {"Janeiro","Fevereiro","Marco","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
	
	public RelatorioSolicitacao(){
		meses = new ArrayList<>();
		
		for(int i = 0; i < 12; i++)
		{
			Mes mes = new Mes();
			mes.setNumero(i+1);
			mes.setNome(meses_nome[i]);
			meses.add(mes);
		}
	}
	
	public List<Mes> getListMes() {
		if(meses ==null)
			meses = new ArrayList<>();
		return meses;
	}
	public void setListMes(List<Mes> meses) {
		this.meses = meses;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
