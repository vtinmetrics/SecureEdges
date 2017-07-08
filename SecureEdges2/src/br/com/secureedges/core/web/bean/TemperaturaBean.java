package br.com.secureedges.core.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.zu.ardulink.Link;
import org.zu.ardulink.event.AnalogReadChangeEvent;
import org.zu.ardulink.event.AnalogReadChangeListener;

import br.com.secureedges.core.ClasseListener;
import br.com.secureedges.core.InputTest;
import br.com.secureedges.core.verificaTemperatura;
import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.core.impl.negocio.ValidadorCpf;
import br.com.secureedges.domain.Dispositivo;

@ViewScoped
@ManagedBean
public class TemperaturaBean{
	public String umidade;
	public String temperatura;
	InputTest orquestrador =  new InputTest();
	

	DispositivoBean dispositivoBean = new DispositivoBean();
	DispositivoDAO dao = new DispositivoDAO();
	Dispositivo dispositivo =  (Dispositivo) dao.buscarPorCodigo(2L);
	
//	public  TemperaturaBean() {
//		orquestrador.contextInitialized(null);
//		umidade = orquestrador.umidade;
//		temperatura = orquestrador.temperatura;
	
//	}
	
	public String getTemperatura() {
		return temperatura;
	}
	public String getUmidade() {
		return umidade;
	}
	
	
	public void pegarvalor() {
		verificaTemperatura verificaTemperatura =  new verificaTemperatura();
		verificaTemperatura.run();
		umidade = verificaTemperatura.umidade;
		temperatura =  verificaTemperatura.temperatura;
		
		
	}
	

	

}
