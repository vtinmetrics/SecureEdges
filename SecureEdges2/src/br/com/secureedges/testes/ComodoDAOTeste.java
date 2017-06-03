package br.com.secureedges.testes;

import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.secureedges.core.dao.ComodoDAO;
import br.com.secureedges.domain.Comodo;
import br.com.secureedges.domain.EntidadeDominio;

public class ComodoDAOTeste {

	@Ignore
	@Test
	public void salvar() throws SQLException {
		Comodo comodo = new Comodo();
		comodo.setDescricao("Sala");
		ComodoDAO dao = new ComodoDAO();
		dao.Salvar(comodo);

	}

	@Test
	public void listar() {
		ComodoDAO dao = new ComodoDAO();
		List<EntidadeDominio> comodos = dao.listar();
		System.out.println("\n");
		System.out.println(comodos);
		System.out.println("\n");
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		ComodoDAO dao = new ComodoDAO();
		Comodo f1 = (Comodo) dao.buscarPorCodigo(1L);
		System.out.println(f1);

	}

	@Test
	@Ignore
	public void Excluir() {
		ComodoDAO dao = new ComodoDAO();
		Comodo comodo = (Comodo) dao.buscarPorCodigo(1L);
		System.out.println(comodo.getCodigo());
		if (comodo != null)
			dao.Excluir(comodo);
	}
//
//	/*
//	 * @Test
//	 * 
//	 * @Ignore public void ExcluirPorCodigo(Long codigo) { ComodoDAO dao = new
//	 * ComodoDAO(); dao.Excluir(3L);
//	 * 
//	 * }
//	 */
//
	@Test
	@Ignore
	public void Editar() {
		ComodoDAO dao = new ComodoDAO();
		Comodo comodo = (Comodo) dao.buscarPorCodigo(1L);
		comodo.setDescricao("DescriacaoX");
	if (comodo != null)
			dao.Editar(comodo);
	}

}
