
package br.com.secureedges.core.web.impl;

import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Resultado;


public class ExcluirCommand extends AbstractCommand{

	
	public Resultado execute(EntidadeDominio entidade) {
		
		return fachada.excluir(entidade);
	}

}
