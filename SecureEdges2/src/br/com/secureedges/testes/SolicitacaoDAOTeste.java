package br.com.secureedges.testes;

import java.sql.SQLException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.secureedges.core.dao.ComodoDAO;
import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.core.dao.SolicitacaoDAO;
import br.com.secureedges.core.dao.UsuarioDAO;
import br.com.secureedges.domain.Comodo;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Solicitacao;
import br.com.secureedges.domain.Usuario;

public class SolicitacaoDAOTeste {
	
	Solicitacao solicitacao = new Solicitacao();
	SolicitacaoDAO dao =  new SolicitacaoDAO();
	UsuarioDAO usuarioDAO  = new UsuarioDAO();
	DispositivoDAO DispositivoDAO =  new DispositivoDAO();
	ComodoDAO comodoDAO =  new ComodoDAO();
	Usuario user =  (Usuario) usuarioDAO.buscarPorCodigo(2L);
	Comodo comodo =  (Comodo) comodoDAO.buscarPorCodigo(2L);
	Dispositivo dispositivo =  (Dispositivo) DispositivoDAO.buscarPorCodigo(2L);
	

	
	@Test
	public void listarSolicitao () throws SQLException{
		List<EntidadeDominio> listasolicitacoes =  dao.listar();
		}
	
	

}
