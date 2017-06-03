package br.com.secureedges.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_Dispositivo")
@NamedQueries
({
@NamedQuery(name="Dispositivo.listar",query="SELECT dispositivo FROM Dispositivo dispositivo"),
@NamedQuery(name="Dispositivo.buscarPorCodigo",query="SELECT dispositivo FROM Dispositivo dispositivo WHERE dispositivo.Codigo=:codigo")
})
public class Dispositivo extends EntidadeDominio {
	
	
	@Column(name="disp_Descricao",nullable=false,length=50)
	String descricao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_Comodo_cmdo_Codigo", referencedColumnName = "cmdo_Codigo", nullable = false)
	private Comodo Comodo;
	
	@OneToOne
	@JoinColumn(name = "tb_TP_Dispositivo_tp_disp_Codigo", referencedColumnName = "tp_disp_Codigo", nullable = false)
	private Tipo_Dispositivo TP_Dispositivo;

	private String status;
	
	private Long interface_Arduino;
	
	private int disp_status;
	
	public int getDisp_status() {
		return disp_status;
	}
	
	public void setDisp_status(int disp_status) {
		this.disp_status = disp_status;
	}
	
	public Long getInterface_Arduino() {
		if (interface_Arduino == null)
			interface_Arduino = new Long("1");
		return interface_Arduino;
	}
	
	public void setInterface_Arduino(Long interface_Arduino) {
		this.interface_Arduino = interface_Arduino;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getDescricao() {
		return descricao;
	}

	
	public Comodo getComodo() {
		if (Comodo ==null)
				Comodo =  new Comodo();
		return Comodo;
	}

	public void setComodo(Comodo comodo) {
		Comodo = comodo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
		
	}


	public Tipo_Dispositivo getTP_Dispositivo() {
		if (TP_Dispositivo ==null)
			TP_Dispositivo =  new Tipo_Dispositivo();
		return TP_Dispositivo;
	}

	public void setTP_Dispositivo(Tipo_Dispositivo tP_Dispositivo) {
		TP_Dispositivo = tP_Dispositivo;
	}



	
	
	

}
