package br.com.secureedges.core.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.secureedges.core.IDAO;
import br.com.secureedges.core.util.factory.Conexao;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Tipo_Dispositivo;
import br.com.secureedges.util.FacesUtil;

public class Tipo_DispositivoDAO implements IDAO{

	@Override
	public Long Salvar(EntidadeDominio entidade) throws SQLException {
		if(!(entidade instanceof Tipo_Dispositivo))
			return null;
		
		Tipo_Dispositivo tipo_Dispositivo = new Tipo_Dispositivo();
		tipo_Dispositivo = (Tipo_Dispositivo) entidade;
		Long retorno = null ;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO db_secureedges.tb_tp_dispositivo(tp_disp_codigo,tipo_descricao)");
		sql.append(" VALUES (?,?)");
		
		Connection con = Conexao.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
		
		try {
			
			int i=0;
			pstm.setLong(++i, tipo_Dispositivo.getCodigo());
			pstm.setString(++i, tipo_Dispositivo.getDescricao());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}

		ResultSet rset =  pstm.getGeneratedKeys();
		while(rset.next()){
			rset.getInt(1);
			retorno=(long) rset.getInt(1);
		}
		return retorno;
	}

	@Override
public void Editar(EntidadeDominio entidade) throws SQLException {
		
		if(!(entidade instanceof Tipo_Dispositivo))
			return;
		
		Tipo_Dispositivo tipo_Dispositivo = new Tipo_Dispositivo();
		tipo_Dispositivo = (Tipo_Dispositivo) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE db_secureedges.tb_tp_dispositivo set tipo_descricao = ? ");
		sql.append(" WHERE tp_disp_Codigo =?");

		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			int i=0;
			pstm.setString(++i,tipo_Dispositivo.getDescricao());
			pstm.setLong(++i,tipo_Dispositivo.getCodigo());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		
	}



	public void Excluir (EntidadeDominio entidade)	{
		
		 {				
				if(!(entidade instanceof Tipo_Dispositivo))
					return;
				
				Tipo_Dispositivo tipo_Dispositivo = new Tipo_Dispositivo();
				tipo_Dispositivo = (Tipo_Dispositivo) entidade;
				
				StringBuffer sql = new StringBuffer();
				sql.append("delete from db_secureedges.tb_tp_dispositivo where tp_disp_Codigo = ?");
				
				Connection con = Conexao.getConnection();
				
				try {
					PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
					pstm.setLong(1, tipo_Dispositivo.getCodigo());
					pstm.executeUpdate();
					
				} catch (Exception e){
					e.printStackTrace();
					FacesUtil.adicionarMSGError((e.getMessage()));
				}
				
				
			
		 }
		
	}



	public  List<EntidadeDominio> listar() {
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM db_secureedges.tb_tp_dispositivo;");			
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Tipo_Dispositivo Tipo_Dispositivo = new Tipo_Dispositivo();
				
				Tipo_Dispositivo.setCodigo(rSet.getLong("tp_disp_Codigo"));
				Tipo_Dispositivo.setDescricao(rSet.getString("tipo_descricao"));
				lista.add(Tipo_Dispositivo);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		
		return lista;
	
	}

	public EntidadeDominio buscarPorCodigo(Long codigo) {
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM db_secureedges.tb_tp_dispositivo");
		sql.append(" where tb_tp_dispositivo.tp_disp_codigo = ?");
		
		Tipo_Dispositivo tipo_Dispositivo = new Tipo_Dispositivo();
		Connection con = Conexao.getConnection();
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			pstm.setLong(1, codigo);
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				
				tipo_Dispositivo.setCodigo(rSet.getLong("tp_disp_Codigo"));
				tipo_Dispositivo.setDescricao(rSet.getString("tipo_descricao"));
					
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		
		return tipo_Dispositivo;
	}
	

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
