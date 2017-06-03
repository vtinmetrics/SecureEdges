package br.com.secureedges.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_Endereco")
@NamedQueries
({
@NamedQuery(name="Endereco.listar",query="SELECT endereco FROM Endereco endereco"),
@NamedQuery(name="Endereco.buscarPorCodigo",query="SELECT endereco FROM Endereco endereco WHERE endereco.codigo=:codigo")
})
public class Endereco  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "end_Codigo",unique=true)
	private Long codigo;
	@Column(name = "end_Rua", length = 50, nullable = false)
	private String Rua;
	@Column(name = "end_Numero", length = 50, nullable = false)
	private Integer Numero;
	@Column(name = "end_Bairro", length = 50, nullable = false)
	private String Bairro;
	@Column(name = "end_Estado", length = 50, nullable = false)
	private String Estado;
	
	@Column(name = "end_Cidadea", length = 50, nullable = false)
	private String Cidade;
	
	@Column(name = "end_CEP", length = 50, nullable = false)
	private String CEP;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		codigo = codigo;
	}

	public String getRua() {
		return Rua;
	}

	public void setRua(String rua) {
		Rua = rua;
	}

	public Integer getNumero() {
		return Numero;
	}

	public void setNumero(Integer numero) {
		Numero = numero;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	@Override
	public String toString() {
		return "Endereco [Codigo=" + codigo + ", Rua=" + Rua + ", Numero=" + Numero + ", Bairro=" + Bairro + ", Estado="
				+ Estado + ", Cidade=" + Cidade + ", CEP=" + CEP + "]";
	}
	
	
	

}
