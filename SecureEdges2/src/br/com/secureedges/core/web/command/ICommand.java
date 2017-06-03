package br.com.secureedges.core.web.command;

import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Resultado;

public interface ICommand  {
	public Resultado execute(EntidadeDominio entidade);

}