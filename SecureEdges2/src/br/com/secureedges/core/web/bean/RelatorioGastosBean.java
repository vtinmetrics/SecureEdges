package br.com.secureedges.core.web.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.secureedges.core.impl.controle.Fachada;
import br.com.secureedges.core.web.command.ICommand;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.ReportLog;
import br.com.secureedges.util.FacesUtil;

@ManagedBean
@ViewScoped
public class RelatorioGastosBean {
	public String usuarioNome;
	public List<EntidadeDominio> listagastos =  new ArrayList<>();
	List<EntidadeDominio> listagastosFiltrados;
	private static Map<String, ICommand> commands;
	// pegando o valor que esta no autenticacaoBean passando para a variavel
	// local o valor do bean
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean = new AutenticacaoBean();
	private String acao;
	private Fachada Fachada = new Fachada();
	
	public void carregarPesquisa()
	{
		try
		{
			listagastos = Fachada.listar(new ReportLog());			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Usuarios:"+ex.getMessage());
			
		}
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public List<EntidadeDominio> getListagastos() {
		return listagastos;
	}

	public List<EntidadeDominio> getListagastosFiltrados() {
		return listagastosFiltrados;
	}

	public static Map<String, ICommand> getCommands() {
		return commands;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public String getAcao() {
		return acao;
	}

	public Fachada getFachada() {
		return Fachada;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public void setListagastos(List<EntidadeDominio> listagastos) {
		this.listagastos = listagastos;
	}

	public void setListagastosFiltrados(List<EntidadeDominio> listagastosFiltrados) {
		this.listagastosFiltrados = listagastosFiltrados;
	}

	public static void setCommands(Map<String, ICommand> commands) {
		RelatorioGastosBean.commands = commands;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public void setFachada(Fachada fachada) {
		Fachada = fachada;
	}
	

}