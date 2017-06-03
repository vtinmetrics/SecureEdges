package br.com.secureedges.core;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Resultado;



public interface IFachada {

	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	
	
	
}