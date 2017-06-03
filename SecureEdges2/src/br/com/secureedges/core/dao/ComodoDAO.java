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
import br.com.secureedges.domain.Comodo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.util.FacesUtil;

public class ComodoDAO implements IDAO{

	public Long Salvar(EntidadeDominio entidade) throws SQLException {
		if(!(entidade instanceof Comodo))
			return null;
		
		Comodo comodo = new Comodo();
		comodo = (Comodo) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO db_secureedges.tb_comodo(cmdo_Codigo,cmdo_Descricao)");
		
		sql.append("VALUES (?,?)");		
		Connection con = Conexao.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
		
		try {
			
			int i=0;
			pstm.setLong(++i, comodo.getCodigo());
			pstm.setString(++i,comodo.getDescricao());
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

	
	
	public void Editar(EntidadeDominio entidade) {
		if(!(entidade instanceof Comodo))
			return;
		
		Comodo comodo = new Comodo();
		comodo = (Comodo) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE db_secureedges.tb_comodo set cmdo_Descricao = ?");	
		sql.append("WHERE cmdo_Codigo = ?");

		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			int i=0;
			pstm.setString(++i,comodo.getDescricao());
			pstm.setLong(++i,comodo.getCodigo());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
	}
	
	
public void Excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Comodo))
			return;
		
		Comodo comodo = new Comodo();
		comodo = (Comodo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from db_secureedges.tb_comodo where cmdo_Codigo = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			pstm.setLong(1, comodo.getCodigo());
			pstm.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
			FacesUtil.adicionarMSGError((e.getMessage()));
		}
		
		
	
}

public List<EntidadeDominio> listar() {
	
	StringBuffer sql = new StringBuffer(); 
	sql.append("SELECT * FROM db_secureedges.tb_comodo;");			
	List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	
	Connection con = Conexao.getConnection();
	
	try {
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
		ResultSet rSet = pstm.executeQuery();
		
		while(rSet.next()) {
			
			Comodo genero = new Comodo();
			genero.setCodigo(rSet.getLong("cmdo_Codigo"));
			genero.setDescricao(rSet.getString("cmdo_Descricao"));			
			lista.add(genero);
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
		FacesUtil.adicionarMSGError(e.getMessage());
	}
	
	return lista;
	

}

	public EntidadeDominio buscarPorCodigo(Long codigo) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM db_secureedges.tb_comodo ");
		sql.append("where cmdo_Codigo = ?");
		
		Comodo comodo = new  Comodo();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			pstm.setLong(1, codigo);
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				comodo.setCodigo(rSet.getLong("cmdo_Codigo"));	
				comodo.setDescricao(rSet.getString("cmdo_Descricao"));
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		
		return comodo;
 
	}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}