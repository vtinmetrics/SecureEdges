package br.com.secureedges.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.core.dao.RelatorioDAO;

public class Grafico extends EntidadeDominio {

	private RelatorioDAO relatorioDAO = new RelatorioDAO();
	public List<EntidadeDominio> relatorios = relatorioDAO.listar();
	private DispositivoDAO dispositivoDAO = new DispositivoDAO();
	private List<EntidadeDominio> dispositivosaux = dispositivoDAO.listar();
	public List<EntidadeDominio> dispositivos= new ArrayList<>();

	
	public Grafico() {
		for(EntidadeDominio dispositivo :dispositivosaux){
			
			if(dispositivo instanceof Dispositivo);
			dispositivos.add((Dispositivo)dispositivo);
		}
			
		}
	

}