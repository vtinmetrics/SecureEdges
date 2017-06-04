package br.com.secureedges.testes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.secureedges.core.dao.UsuarioDAO;
import br.com.secureedges.domain.Endereco;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Usuario;

public class UsuarioDAOTeste {

	Usuario usuario = new Usuario();
	UsuarioDAO dao = new UsuarioDAO();


	@Test
	public void testeExcluir() throws SQLException {
		Usuario usuario = new Usuario();
		usuario = dao.buscarPorCodigo(18L);
		usuario.setNome("teste");
		UsuarioDAO dao = new UsuarioDAO();
		dao.Excluir(usuario);
	}





}
