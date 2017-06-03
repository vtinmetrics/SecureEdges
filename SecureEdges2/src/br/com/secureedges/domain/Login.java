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
@Table(name = "tb_Login")
@NamedQueries
({
@NamedQuery(name="Login.listar",query="SELECT login FROM Login login"),
@NamedQuery(name="Login.buscarPorCodigo",query="SELECT login FROM Login login WHERE login.codigo=:codigo")
})
public class Login {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "login_Codigo",unique=true)
	private Long codigo;
	@Column(name = "usr_Login", length = 50, nullable = false)
	private String Login;
	@Column(name = "usr_Email", length = 50, nullable = false)
	private String Email;
	@Column(name = "usr_Senha", length = 50, nullable = false)
	private String Senha;
	
	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	

	

	@Override
	public String toString() {
		return "Login [codigo=" + codigo + ", Login=" + Login + ", Email=" + Email + ", Senha=" + Senha + ", Usuario="
				+  "]";
	}

	
	

}
