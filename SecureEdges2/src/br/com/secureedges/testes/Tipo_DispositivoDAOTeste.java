package br.com.secureedges.testes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.secureedges.core.dao.Tipo_DispositivoDAO;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Tipo_Dispositivo;

public class Tipo_DispositivoDAOTeste {

	@Test
	public void salvar() throws SQLException {
		Tipo_Dispositivo tipo_Dispositivo = new Tipo_Dispositivo();
		tipo_Dispositivo.setDescricao("iluminação");
		Tipo_DispositivoDAO dao = new Tipo_DispositivoDAO();
		dao.Salvar(tipo_Dispositivo);

	}

	@Test
	@Ignore
	public void editar() throws SQLException {
		Tipo_Dispositivo tipo_Dispositivo = new Tipo_Dispositivo();
		tipo_Dispositivo.setDescricao("Temperatura");
		tipo_Dispositivo.setCodigo(4L);
		Tipo_DispositivoDAO dao = new Tipo_DispositivoDAO();
		dao.Editar(tipo_Dispositivo);

	}

	@Test
	public void listar() {
		List<EntidadeDominio> lista = new ArrayList<>();
		Tipo_DispositivoDAO dao = new Tipo_DispositivoDAO();
		lista = dao.listar();
		System.out.println(lista);

	}

	public void testeBusca() {
		Tipo_DispositivoDAO dao = new Tipo_DispositivoDAO();
		Tipo_Dispositivo tipo = (Tipo_Dispositivo) dao.buscarPorCodigo(4L);
		System.out.println(tipo.getDescricao());
	}



	@Test
	public void excluir() {
	
		Tipo_DispositivoDAO dao = new Tipo_DispositivoDAO();
		Tipo_Dispositivo tipo_Dispositivo = (Tipo_Dispositivo) dao.buscarPorCodigo(4L);
		dao.Excluir(tipo_Dispositivo);
		
	}

}
