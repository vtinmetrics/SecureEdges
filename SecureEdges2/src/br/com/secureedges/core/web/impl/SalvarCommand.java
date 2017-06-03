
package br.com.secureedges.core.web.impl;

import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Resultado;


public class SalvarCommand extends AbstractCommand{

	
	public Resultado execute(EntidadeDominio entidade) {
		
		return fachada.salvar(entidade);
	}

}
