package br.com.secureedges.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item extends EntidadeDominio  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Solicitacao solicitacao;
	
	private Dispositivo dispositivo;



	public Solicitacao getSolicitacao() {
		if (solicitacao == null)
			solicitacao=  new  Solicitacao();
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao Solicitacao) {
		this.solicitacao = Solicitacao;
	}

	public Dispositivo getDispositivo() {
		if(dispositivo == null)
			dispositivo =  new Dispositivo();
		return dispositivo;
	}


	public void setDispositivo(Dispositivo Dispositivo) {
		this.dispositivo = Dispositivo;
	}
	
	
	
}