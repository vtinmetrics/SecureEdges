package br.com.secureedges.core.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.secureedges.core.IDAO;
import br.com.secureedges.core.util.factory.Conexao;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Log;
import br.com.secureedges.domain.ReportLog;
import br.com.secureedges.util.FacesUtil;

public class ReportLogDAO implements IDAO {

	public Long Salvar(EntidadeDominio entidade) throws SQLException {
		if (!(entidade instanceof ReportLog))
			return null;

		ReportLog log = new ReportLog();
		log = (ReportLog) entidade;

		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO db_secureedges.tb_report_log(id_report,data_ligado,data_desligado,intervalo,dispositivo,gasto)");

		sql.append("VALUES (?,?,?,?,?,?)");
		Connection con = Conexao.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString(),
				Statement.RETURN_GENERATED_KEYS);

		try {
			SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+HH:mm");
			String data_liga = stf.format( log.getDataLigado());
			String data_desliga = stf.format(log.getDataDesligado());
			
			Double valor ;
			
			valor = (((double) (log.getIntervalo() / 60) * 0.090) * 0.43); 
			
			
			data_liga = data_liga.replace("A", "");
			data_liga = data_liga.replace("B", "");
			data_liga = data_liga.replace("C", "");
			data_liga = data_liga.replace("D", "");
			data_liga = data_liga.replace("E", "");
			data_liga = data_liga.replace("F", "");
			data_liga = data_liga.replace("G", "");
			data_liga = data_liga.replace("H", "");
			data_liga = data_liga.replace("I", "");
			data_liga = data_liga.replace("J", "");
			data_liga = data_liga.replace("L", "");
			data_liga = data_liga.replace("M", "");
			data_liga = data_liga.replace("N", "");
			data_liga = data_liga.replace("O", "");
			data_liga = data_liga.replace("P", "");
			data_liga = data_liga.replace("Q", "");
			data_liga = data_liga.replace("R", "");
			data_liga = data_liga.replace("S", "");
			data_liga = data_liga.replace("U", "");
			data_liga = data_liga.replace("V", "");
			data_liga = data_liga.replace("X", "");
			data_liga = data_liga.replace("Z", "");
			data_liga = data_liga.replace("K", "");
			data_liga = data_liga.replace("Y", "");
			data_liga = data_liga.replace("W", "");
			
			data_desliga = data_desliga.replace("A", "");
			data_desliga = data_desliga.replace("B", "");
			data_desliga = data_desliga.replace("C", "");
			data_desliga = data_desliga.replace("D", "");
			data_desliga = data_desliga.replace("E", "");
			data_desliga = data_desliga.replace("F", "");
			data_desliga = data_desliga.replace("G", "");
			data_desliga = data_desliga.replace("H", "");
			data_desliga = data_desliga.replace("I", "");
			data_desliga = data_desliga.replace("J", "");
			data_desliga = data_desliga.replace("L", "");
			data_desliga = data_desliga.replace("M", "");
			data_desliga = data_desliga.replace("N", "");
			data_desliga = data_desliga.replace("O", "");
			data_desliga = data_desliga.replace("P", "");
			data_desliga = data_desliga.replace("Q", "");
			data_desliga = data_desliga.replace("R", "");
			data_desliga = data_desliga.replace("S", "");
			data_desliga = data_desliga.replace("U", "");
			data_desliga = data_desliga.replace("V", "");
			data_desliga = data_desliga.replace("X", "");
			data_desliga = data_desliga.replace("Z", "");
			data_desliga = data_desliga.replace("K", "");
			data_desliga = data_desliga.replace("Y", "");
			data_desliga = data_desliga.replace("W", "");
			
			
			
			int i = 0;
			pstm.setLong(++i, log.getCodigo());
			pstm.setString(++i, data_liga);
			pstm.setString(++i, data_desliga);
			pstm.setLong(++i, log.getIntervalo());
			pstm.setString(++i, log.getDispositivo());
			pstm.setDouble(++i,valor);

			pstm.executeUpdate();

			FacesUtil.adicionarMSGInfo("Inserido com Sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
			FacesUtil.adicionarMSGError(e.getMessage());
		}
		Long retorno = null;
		ResultSet rset = pstm.getGeneratedKeys();
		while (rset.next()) {
			rset.getInt(1);
			retorno = (long) rset.getInt(1);
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

	public List<EntidadeDominio> listar() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM db_secureedges.tb_report_log;");
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				

				ReportLog log = new ReportLog();
				log.setCodigo(rSet.getLong("id_report"));
				log.setDataLigado(rSet.getTimestamp("data_ligado"));
				log.setDataDesligado(rSet.getTimestamp("data_desligado"));
				log.setIntervalo(rSet.getInt("intervalo"));
				log.setDispositivo(rSet.getString("dispositivo"));
				log.setGasto(rSet.getDouble("gasto"));
				

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

}
