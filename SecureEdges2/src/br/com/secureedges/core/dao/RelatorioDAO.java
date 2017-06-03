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
import br.com.secureedges.domain.Mes;
import br.com.secureedges.domain.RelatorioSolicitacao;
import br.com.secureedges.domain.Solicitacao;
import br.com.secureedges.util.FacesUtil;

public class RelatorioDAO implements IDAO {

	public Long Salvar(EntidadeDominio entidade) throws SQLException {

		return null;

	}

	public void Editar(EntidadeDominio entidade) throws SQLException {

	}

	public void Excluir(EntidadeDominio entidade) {

	}

	public List<EntidadeDominio> listar() {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT sol.sol_Status as solicitacao_descricao, sol.sol_Codigo as solicitacao"
				+ ",disp.disp_codigo as dispositivo"
				+ ",disp.disp_descricao as descricao"
				+ ",MONTH(sol.sol_data) as data"
				+ ",count(*) as quantidade"
				+ " from (tb_solicitacao as sol) "
				+ "inner join (tb_dispositivo as disp) "
				+ "on sol.tb_Dispositivo_disp_Codigo = disp.disp_codigo "
				+ "group by(disp.disp_codigo)"
				+ "order by MONTH(sol.sol_data),disp.disp_codigo;");
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			RelatorioSolicitacao relatorio = new RelatorioSolicitacao();
			
			while (rSet.next()) {

				Mes mes = relatorio.getListMes().get(rSet.getInt("data"));
				Solicitacao solic = new Solicitacao();
				SolicitacaoDAO solicAux = new SolicitacaoDAO();
				solic = (Solicitacao) solicAux.buscarPorCodigo(rSet.getLong("solicitacao"));
				solic.setQtde(rSet.getInt("quantidade"));
				solic.setStatus(rSet.getString("solicitacao_descricao"));
				mes.addSolicitacao(solic);
				
			}
			lista.add(relatorio);
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		return lista;

	}

	public EntidadeDominio buscarPorCodigo(Long codigo) {

		return null;
	}

	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	public List<EntidadeDominio> listar2() {
		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT sol.sol_Codigo as solicitacao"
				+ ",disp.disp_codigo as dispositivo"
				+ ",disp.disp_descricao as descricao"
				+ ",MONTH(sol.sol_data) as data"
				+ ",count(*) as quantidade"
				+ " from (tb_solicitacao as sol) "
				+ "inner join (tb_dispositivo as disp) "
				+ "on sol.tb_Dispositivo_disp_Codigo = disp.disp_codigo "
				+ "group by(disp.disp_codigo)"
				+ "order by MONTH(sol.sol_data),disp.disp_codigo;");
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			RelatorioSolicitacao relatorio = new RelatorioSolicitacao();
			
			while (rSet.next()) {

				Mes mes = relatorio.getListMes().get(rSet.getInt("data"));
				Solicitacao solic = new Solicitacao();
				SolicitacaoDAO solicAux = new SolicitacaoDAO();
				solic = (Solicitacao) solicAux.buscarPorCodigo(rSet.getLong("solicitacao"));
				solic.setQtde(rSet.getInt("quantidade"));
				mes.addSolicitacao(solic);
				
			}
			lista.add(relatorio);
			
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		return lista;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

