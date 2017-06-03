
package br.com.secureedges.core.web.impl;

import br.com.secureedges.core.web.command.ICommand;
import br.com.secureedges.core.IFachada;
import br.com.secureedges.core.impl.controle.Fachada;



public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada = new Fachada();

}
