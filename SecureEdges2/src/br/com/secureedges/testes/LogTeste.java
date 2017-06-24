package br.com.secureedges.testes;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Test;

import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.core.dao.LogDAO;
import br.com.secureedges.core.dao.UsuarioDAO;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.Log;
import br.com.secureedges.domain.Usuario;

public class LogTeste {

	@Test
	public void teste() throws SQLException {
	 Log log =  new Log();
	 LogDAO dao =  new LogDAO();
	 UsuarioDAO usuarioDAO =  new UsuarioDAO();
	 DispositivoDAO dispositivoDAO =  new DispositivoDAO();
	 log.setDispositivo((Dispositivo) dispositivoDAO.buscarPorCodigo(1L));
	 log.setUsuario((Usuario) usuarioDAO.buscarPorCodigo(2L));
	 log.setStatus("novo");
	 log.setData(new Date());
	 dao.Salvar(log);
	 
	 
	}
}

