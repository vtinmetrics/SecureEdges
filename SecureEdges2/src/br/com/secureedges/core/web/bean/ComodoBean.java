package br.com.secureedges.core.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.secureedges.core.impl.controle.Fachada;
import br.com.secureedges.util.FacesUtil;
import br.com.secureedges.core.web.command.ICommand;
import br.com.secureedges.core.web.impl.AlterarCommand;
import br.com.secureedges.core.web.impl.ExcluirCommand;
import br.com.secureedges.core.web.impl.SalvarCommand;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Tipo_Dispositivo;
import br.com.secureedges.domain.Comodo;

@ManagedBean
@ViewScoped
public class ComodoBean extends EntidadeDominio {
	private Comodo ComodoCadastro;
	List<EntidadeDominio> listaComodos;
	List<Comodo> listaComodosFiltrados;
	private String acao;
	private Long codigo;
	private static Map<String, ICommand> commands;
	
	public ComodoBean() {
		
		commands = new HashMap<String, ICommand>();
		commands.put("Salvar", new SalvarCommand());
		commands.put("Excluir", new ExcluirCommand());
		commands.put("Editar", new AlterarCommand());
	}
	
	public String getAcao() {
		return acao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
		
	
	public Comodo getComodoCadastro() {
		if(ComodoCadastro ==null)
			ComodoCadastro= new Comodo();
		
		return ComodoCadastro;
	}
	public void setComodoCadastro(Comodo ComodoCadastro) {
		this.ComodoCadastro = ComodoCadastro;
	}
	
	public List<EntidadeDominio> getListaComodos() {
		return listaComodos;
	}
	
	public void setListaComodos(List<EntidadeDominio> listaComodos) {
		this.listaComodos = listaComodos;
	}
	
	public List<Comodo> getListaComodosFiltrados() {
		return listaComodosFiltrados;
	}
	public void setListaComodosFiltrados(List<Comodo> listaComodosFiltrados) {
		this.listaComodosFiltrados = listaComodosFiltrados;
	}
	
	public void novo()
	{
		ComodoCadastro.setDescricao(null);
	}
	
	public void salvar()
	{
		try
		{//Obtêm o command para executar a respectiva operação
			ICommand command = commands.get(acao);
			/*Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			command.execute(ComodoCadastro);
			ComodoCadastro = new Comodo();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Comodo:"+ex.getMessage());
			
		}
		
	}
	
	public void carregarPesquisa()
	{
		try
		{
			Fachada fachada =  new Fachada();
			listaComodos = fachada.listar(new Comodo());			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Comodos:"+ex.getMessage());
			
		}
	}
	
	public void carregarCadastro(){
		try{
			String valor = FacesUtil.getParam("cmdCod");
			if(valor != null)
			{
				Long codigo = Long.parseLong(valor);
				ComodoCadastro=(Comodo) Fachada.buscarGenerico(codigo, new Comodo());
				System.out.println(ComodoCadastro.getDescricao());
			}
		
		} catch(RuntimeException ex){
			
		}
	}
	
	public void excluir()
	{
		try
		{
			//Obtêm o command para executar a respectiva operação
			ICommand command = commands.get(acao);
			/*Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			command.execute(ComodoCadastro);
			ComodoCadastro = new Comodo();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar remover Comodo:"+ex.getMessage());
			
		}
		
	}
	
	
	public void editar()
	{
		try
		{
			//Obtêm o command para executar a respectiva operação
			ICommand command = commands.get(acao);
			/*Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			command.execute(ComodoCadastro);
			ComodoCadastro = new Comodo();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar editar Comodo:"+ex.getMessage());
			
		}
		
	}

}