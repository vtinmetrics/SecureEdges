package br.com.secureedges.core.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.secureedges.core.InputTest;
import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.domain.Dispositivo;

@ViewScoped
@ManagedBean
public class TemperaturaBean{

	InputTest test = new InputTest();
	DispositivoBean dispositivoBean = new DispositivoBean();
	DispositivoDAO dao = new DispositivoDAO();
	Dispositivo dispositivo =  (Dispositivo) dao.buscarPorCodigo(2L);
	public InputTest getTest() throws InterruptedException {
		return test;
	}
	public TemperaturaBean() throws InterruptedException {
		test.contextInitialized(null);
		int aux = Integer.parseInt(test.getTemperatura()); 
		if (aux > 22) {
			dispositivoBean.manipular(dispositivo);
		}
		else {
		//	dispositivoBean.manipular(dispositivo);
		}
	}
	

}
