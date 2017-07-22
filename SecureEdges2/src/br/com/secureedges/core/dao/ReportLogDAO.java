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

		sql.append("INSERT INTO db_secureedges.tb_report_log(id_report,data_ligado,data_desligado,intervalo,dispositivo)");

		sql.append("VALUES (?,?,?,?,?)");
		Connection con = Conexao.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString(),
				Statement.RETURN_GENERATED_KEYS);

		try {
			SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+HH:mm");
			String data_liga = stf.format( log.getDataLigado());
			String data_desliga = stf.format(log.getDataDesligado());
			int i = 0;
			pstm.setLong(++i, log.getCodigo());
			pstm.setString(++i, data_liga);
			pstm.setString(++i, data_desliga);
			pstm.setLong(++i, log.getIntervalo());
			pstm.setString(++i, log.getDispositivo());

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
				log.setDataLigado(rSet.getTimestamp("data_desligado"));
				log.setIntervalo(rSet.getInt("intervalo"));
				log.setDispositivo(rSet.getString("dispositivo"));
				

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
