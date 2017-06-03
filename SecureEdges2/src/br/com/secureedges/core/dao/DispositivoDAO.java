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
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.util.FacesUtil;

public class DispositivoDAO implements IDAO {

	public Long Salvar(EntidadeDominio entidade) throws SQLException {
		if(!(entidade instanceof Dispositivo))
			return null;
		
		Dispositivo dispositivo = new Dispositivo();
		dispositivo = (Dispositivo) entidade;
		Long retorno = null ;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO db_secureedges.tb_dispositivo(disp_codigo,disp_descricao,cmdo_Codigo,tp_disp_Codigo,interface_Arduino,disp_status)");
		sql.append(" VALUES (?,?,?,?,?,?)");
		
		Connection con = Conexao.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
		
		try {
			
			int i=0;
			pstm.setLong(++i, dispositivo.getCodigo());
			pstm.setString(++i, dispositivo.getDescricao());
			pstm.setLong(++i, dispositivo.getComodo().getCodigo());
			pstm.setLong(++i, dispositivo.getTP_Dispositivo().getCodigo());
			pstm.setLong(++i, dispositivo.getInterface_Arduino());
			pstm.setInt(++i, dispositivo.getDisp_status());
			
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
	

public void Editar(EntidadeDominio entidade) throws SQLException {
		
		if(!(entidade instanceof Dispositivo))
			return;
		
		Dispositivo dispositivo = new Dispositivo();
		dispositivo = (Dispositivo) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE db_secureedges.tb_dispositivo set disp_Descricao = ?,cmdo_codigo = ?,tp_disp_Codigo = ?, disp_status = ? ");	
		sql.append(" WHERE disp_codigo=?");

		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			int i=0;
			pstm.setString(++i,dispositivo.getDescricao());
			pstm.setLong(++i,dispositivo.getComodo().getCodigo());
			pstm.setLong(++i,dispositivo.getTP_Dispositivo().getCodigo());
			pstm.setInt(++i, dispositivo.getDisp_status());
			pstm.setLong(++i,dispositivo.getCodigo());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		
	}

public void Excluir (EntidadeDominio entidade)	{
	
	 {				
			if(!(entidade instanceof Dispositivo))
				return;
			
			Dispositivo dispositivo = new Dispositivo();
			dispositivo = (Dispositivo) entidade;
			
			StringBuffer sql = new StringBuffer();
			sql.append("delete from db_secureedges.tb_dispositivo where disp_codigo = ?");
			
			Connection con = Conexao.getConnection();
			
			try {
				PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
				pstm.setLong(1, dispositivo.getCodigo());
				pstm.executeUpdate();
				
			} catch (Exception e){
				e.printStackTrace();
				FacesUtil.adicionarMSGError((e.getMessage()));
			}
			
			
		
	 }
	
}

public  List<EntidadeDominio> listar() {
	StringBuffer sql = new StringBuffer(); 
	sql.append("SELECT * FROM db_secureedges.tb_dispositivo;");			
	List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	
	Connection con = Conexao.getConnection();
	
	try {
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
		ResultSet rSet = pstm.executeQuery();
		
		while(rSet.next()) {
			
			Dispositivo dispositivo = new Dispositivo();
			dispositivo.setCodigo(rSet.getLong("disp_Codigo"));
			dispositivo.setDescricao(rSet.getString("disp_Descricao"));
			dispositivo.getComodo().setCodigo(rSet.getLong("cmdo_Codigo"));
			dispositivo.getTP_Dispositivo().setCodigo(rSet.getLong("tp_disp_Codigo"));
			dispositivo.setInterface_Arduino(rSet.getLong("interface_Arduino"));
			dispositivo.setDisp_status(rSet.getInt("disp_status"));
			
			lista.add(dispositivo);
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		FacesUtil.adicionarMSGError(e.getMessage());
	}
	
	return lista;

}

public EntidadeDominio buscarPorCodigo(Long codigo) {
	StringBuffer sql = new StringBuffer(); 
	sql.append("SELECT * FROM db_secureedges.tb_dispositivo");
	sql.append(" where tb_dispositivo.disp_codigo = ?");
	
	Dispositivo dispositivo = new Dispositivo();
	Connection con = Conexao.getConnection();
	try {
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
		pstm.setLong(1, codigo);
		ResultSet rSet = pstm.executeQuery();
		
		while(rSet.next()){
			
			dispositivo.setCodigo(rSet.getLong("disp_Codigo"));
			dispositivo.getTP_Dispositivo().setCodigo(rSet.getLong("tp_disp_Codigo"));
			dispositivo.getComodo().setCodigo(rSet.getLong("cmdo_Codigo"));
			dispositivo.setDescricao(rSet.getString("disp_descricao"));
			dispositivo.setInterface_Arduino(rSet.getLong("interface_Arduino"));
			dispositivo.setDisp_status(rSet.getInt("disp_status"));
				
			
		}
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		FacesUtil.adicionarMSGError(e.getMessage());
	}
	
	return dispositivo;
}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}