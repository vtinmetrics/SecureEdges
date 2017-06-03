package br.com.secureedges.util;



import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
public class FacesUtil {
	public static void adicionarMSGInfo(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,mensagem);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}

	public static void adicionarMSGError(String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,mensagem);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, facesMessage);
	}
	public static String  getParam (String nome) {
		// cria um contexto pegando o atual
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// pega o contexto externo
		ExternalContext externalContext = facesContext.getExternalContext();
		// cria um mapa de paramtros 
		Map<String, String> paramtros =externalContext.getRequestParameterMap();
		// insere o nome no valor e retorna o valor
		String valor = paramtros.get(nome);
		return valor;
		
	}
	
	

}
