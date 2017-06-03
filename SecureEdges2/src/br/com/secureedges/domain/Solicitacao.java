package br.com.secureedges.domain;

import java.util.Date;

import org.hibernate.mapping.Set;

public class Solicitacao extends EntidadeDominio {

	private String status;
	

	private String descricao;
	private Comodo comodo;
	private int qtde;
	
	public Comodo getComodo() {
		if (comodo ==null)
			comodo = new Comodo();
		return comodo;
	}
	public void setComodo(Comodo comodo) {
		this.comodo = comodo;
	}
	@Override
	public Long getCodigo() {
		// TODO Auto-generated method stub
		return super.getCodigo();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private Usuario usuario;

	private Date data = new Date();

	private Dispositivo dispositivo;

	public String getStatus() {
		if (status == null)
			status = "novo";
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getData() {
		if (data == null)  
				data = new Date() ;
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario  = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Dispositivo getDispositivo() {
		if (dispositivo == null)
			dispositivo = new Dispositivo();
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

}
