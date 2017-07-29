package br.com.secureedges.core.web.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.zu.ardulink.Link;
import org.zu.ardulink.event.AnalogReadChangeEvent;
import org.zu.ardulink.event.AnalogReadChangeListener;

import br.com.secureedges.core.ClasseListener;
import br.com.secureedges.core.InputTest;
import br.com.secureedges.core.verificaTemperatura;
import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.core.impl.negocio.ValidadorCpf;
import br.com.secureedges.core.web.command.ICommand;
import br.com.secureedges.core.web.impl.AlterarCommand;
import br.com.secureedges.core.web.impl.ExcluirCommand;
import br.com.secureedges.core.web.impl.SalvarCommand;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.Log;

@ViewScoped
@ManagedBean
public class TemperaturaBean{
	public String umidade;
	public String temperatura;
	verificaTemperatura orquestrador =  new verificaTemperatura();
	
	

	DispositivoBean dispositivoBean = new DispositivoBean();
	DispositivoDAO dao = new DispositivoDAO();
	Dispositivo dispositivo =  (Dispositivo) dao.buscarPorCodigo(2L);
	private static Map<String, ICommand> commands;
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean = new AutenticacaoBean();
	
	public  TemperaturaBean() {
		orquestrador.run();
		umidade = orquestrador.umidade;
		temperatura = orquestrador.temperatura;
		commands = new HashMap<String, ICommand>();
		commands.put("Salvar", new SalvarCommand());
		commands.put("Excluir", new ExcluirCommand());
		commands.put("Editar", new AlterarCommand());
	
	}
	
	public String getTemperatura() {
		return temperatura;
	}
	public String getUmidade() {
		return umidade;
	}
	
	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}
	
	
	
	public void pegarvalor() {
		verificaTemperatura verificaTemperatura =  new verificaTemperatura();
		verificaTemperatura.run();
		System.out.println(verificaTemperatura);
		umidade = verificaTemperatura.umidade;
		temperatura =  verificaTemperatura.temperatura;
		int temperaturaint =  Integer.parseInt(temperatura);
		
		if (autenticacaoBean.getUsuarioLogado().getCPF() != null && autenticacaoBean.getUsuarioLogado().getTemperaturamax() <= temperaturaint) {
			DispositivoDAO dao = new DispositivoDAO();
			Dispositivo dispositivo =  (Dispositivo) dao.buscarPorCodigo(2L);
			DispositivoBean bean  =  new DispositivoBean();
			bean.manipular2(dispositivo);
		}
		
		
	}
	

	

}
