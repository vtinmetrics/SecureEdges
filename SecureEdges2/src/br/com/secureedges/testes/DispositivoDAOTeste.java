package br.com.secureedges.testes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;

public class DispositivoDAOTeste {

@Test
@Ignore
public  void testeSalvar() throws SQLException{
	Dispositivo dispositivo =  new Dispositivo();
	dispositivo.setCodigo(22L);
	dispositivo.getComodo().setCodigo(2L);
	dispositivo.getTP_Dispositivo().setCodigo(5L);
	dispositivo.setDescricao("Luz");
	DispositivoDAO dao =  new DispositivoDAO();
	dao.Salvar(dispositivo);
	
}

@Test
@Ignore
public  void testeEditar() throws SQLException{
	Dispositivo disp  =  new  Dispositivo();
	disp.setCodigo(1L);
	disp.setDescricao("teste");
	disp.getComodo().setCodigo(2L);
	disp.getTP_Dispositivo().setCodigo(2L);
	DispositivoDAO dao = new DispositivoDAO();
	dao.Editar(disp);
}


@Test
@Ignore
public  void excluir(){
	
	Dispositivo disp  =  new  Dispositivo();
	disp.setCodigo(1L);
	disp.setDescricao("teste");
	disp.getComodo().setCodigo(2L);
	disp.getTP_Dispositivo().setCodigo(2L);
	DispositivoDAO dao = new DispositivoDAO();
	dao.Excluir(disp);
}

@Test
@Ignore
public void listar(){
	List<EntidadeDominio> lista =  new ArrayList<>();
	DispositivoDAO dao =  new DispositivoDAO();
	lista =  dao.listar();
	System.out.println(lista);
}


@Test
public void buscarporcodigo(){
	
	DispositivoDAO dao =  new DispositivoDAO();
	Dispositivo dispositivo =  (Dispositivo) dao.buscarPorCodigo(22L);
	
	System.out.println(dispositivo);
}


}


