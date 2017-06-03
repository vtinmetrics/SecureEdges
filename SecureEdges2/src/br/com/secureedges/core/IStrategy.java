package br.com.secureedges.core;

import br.com.secureedges.domain.EntidadeDominio;

public interface IStrategy {
	
	public String processar(EntidadeDominio entidade);
	


}