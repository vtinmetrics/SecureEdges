package br.com.secureedges.core.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.secureedges.core.IDAO;
import br.com.secureedges.core.util.factory.Conexao;
import br.com.secureedges.domain.Log;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.util.FacesUtil;

public class LogDAO implements IDAO{
	public Long Salvar(EntidadeDominio entidade) throws SQLException {
		if(!(entidade instanceof Log))
			return null;
		
		Log log = new Log();
		log = (Log) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO db_secureedges.tb_log(id_log,id_usuario,id_dispositivo,data_operacao,log_status)");
		
		sql.append("VALUES (?,?,?,?,?)");		
		Connection con = Conexao.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
		
		try {
			SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String vendaHorario = stf.format(log.getData());
			int i=0;
			pstm.setLong(++i, log.getCodigo());
			pstm.setLong(++i,log.getUsuario());
			pstm.setLong(++i,log.getDispositivo());
			pstm.setString(++i,vendaHorario);
			pstm.setString(++i,log.getStatus());
			
			pstm.executeUpdate();
			
			FacesUtil.adicionarMSGInfo("Inserido com Sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		Long retorno = null;
		ResultSet rset =  pstm.getGeneratedKeys();
		while(rset.next()){
			rset.getInt(1);
			retorno=(long) rset.getInt(1);
		}
		return retorno;

	}

	@Override
	public void Editar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Excluir(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> listar() {
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM db_secureedges.tb_log;");			
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Log log = new Log();
				log.setCodigo(rSet.getLong("id_log"));
				log.setUsuario(rSet.getLong("id_usuario"));
				log.setDispositivo(rSet.getLong("id_dispositivo"));
				log.setData(rSet.getDate("data_operacao"));
				log.setStatus(rSet.getString("log_status"));
				
				lista.add(log);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		
		return lista;
	}

	@Override
	public EntidadeDominio buscarPorCodigo(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
	public void testar() {
		listar();
	}
	
}
