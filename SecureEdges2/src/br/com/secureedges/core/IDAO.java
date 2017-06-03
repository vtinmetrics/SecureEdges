package br.com.secureedges.core;
import java.sql.SQLException;
import java.util.List;

import br.com.secureedges.domain.EntidadeDominio;

public interface IDAO {

	public Long Salvar(EntidadeDominio entidade) throws SQLException;
	public void Editar(EntidadeDominio entidade)throws SQLException;
	public void Excluir(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> listar();
	public EntidadeDominio buscarPorCodigo(Long codigo);
	EntidadeDominio consultar(EntidadeDominio entidade);
	
	
	
}