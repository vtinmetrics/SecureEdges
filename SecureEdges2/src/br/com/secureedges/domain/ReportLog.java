package br.com.secureedges.domain;

import java.sql.Timestamp;
import java.util.Date;

public class ReportLog extends EntidadeDominio{
	 Date dataLigado = new Date();
	 Date dataDesligado = new Date();
	 int intervalo ;
	 String dispositivo;
	 Double gasto;
	 
	 
	 public Double getGasto() {
		return gasto;
	}
	 public void setGasto(Double gasto) {
		this.gasto = gasto;
	}
	 
	 public String getDispositivo() {
		return dispositivo;
	}
	 public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
	public Date getDataLigado() {
		return dataLigado;
	}
	public Date getDataDesligado() {
		return dataDesligado;
	}
	public int getIntervalo() {
		return intervalo;
	}
	public void setDataLigado(Timestamp dataLigado) {
		this.dataLigado = dataLigado;
	}
	public void setDataDesligado(Timestamp dataDesligado) {
		this.dataDesligado = dataDesligado;
	}
	public void setIntervalo(int intervalo) {
		this.intervalo = intervalo;
	}
	 
	
}
