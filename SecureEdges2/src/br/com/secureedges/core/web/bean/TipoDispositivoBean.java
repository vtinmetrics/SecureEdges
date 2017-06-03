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
import br.com.secureedges.domain.Tipo_Dispositivo;;

@ManagedBean
@ViewScoped
public class TipoDispositivoBean extends EntidadeDominio {
	private Tipo_Dispositivo Tipo_DispositivoCadastro;
	List<EntidadeDominio> listaTipo_Dispositivos;
	List<Tipo_Dispositivo> listaTipo_DispositivosFiltrados;
	private String acao;
	private Long codigo;
	private static Map<String, ICommand> commands;
	
	public TipoDispositivoBean() {
		
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
		
	
	public Tipo_Dispositivo getTipo_DispositivoCadastro() {
		if(Tipo_DispositivoCadastro ==null)
			Tipo_DispositivoCadastro= new Tipo_Dispositivo();
		
		return Tipo_DispositivoCadastro;
	}
	public void setTipo_DispositivoCadastro(Tipo_Dispositivo Tipo_DispositivoCadastro) {
		this.Tipo_DispositivoCadastro = Tipo_DispositivoCadastro;
	}
	
	public List<EntidadeDominio> getListaTipo_Dispositivos() {
		return listaTipo_Dispositivos;
	}
	
	public void setListaTipo_Dispositivos(List<EntidadeDominio> listaTipo_Dispositivos) {
		this.listaTipo_Dispositivos = listaTipo_Dispositivos;
	}
	
	public List<Tipo_Dispositivo> getListaTipo_DispositivosFiltrados() {
		return listaTipo_DispositivosFiltrados;
	}
	public void setListaTipo_DispositivosFiltrados(List<Tipo_Dispositivo> listaTipo_DispositivosFiltrados) {
		this.listaTipo_DispositivosFiltrados = listaTipo_DispositivosFiltrados;
	}
	
	public void novo()
	{
		Tipo_DispositivoCadastro.setDescricao(null);
	}
	
	public void salvar()
	{
		try
		{//Obtêm o command para executar a respectiva operação
			ICommand command = commands.get(acao);
			/*Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			command.execute(Tipo_DispositivoCadastro);
			Tipo_DispositivoCadastro = new Tipo_Dispositivo();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Tipo_Dispositivo:"+ex.getMessage());
			
		}
		
	}
	
	public void carregarPesquisa()
	{
		try
		{
			Fachada fachada =  new Fachada();
			listaTipo_Dispositivos = fachada.listar(new Tipo_Dispositivo());			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Tipo_Dispositivos:"+ex.getMessage());
			
		}
	}
	
	public void carregarCadastro(){
		try{
			String valor = FacesUtil.getParam("tpCod");
			if(valor != null)
			{
				Long codigo = Long.parseLong(valor);
				Tipo_DispositivoCadastro=(Tipo_Dispositivo) Fachada.buscarGenerico(codigo, new Tipo_Dispositivo());
				System.out.println(Tipo_DispositivoCadastro.getDescricao());
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
			command.execute(Tipo_DispositivoCadastro);
			Tipo_DispositivoCadastro = new Tipo_Dispositivo();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar remover Tipo_Dispositivo:"+ex.getMessage());
			
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
			command.execute(Tipo_DispositivoCadastro);
			Tipo_DispositivoCadastro = new Tipo_Dispositivo();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar editar Tipo_Dispositivo:"+ex.getMessage());
			
		}
		
	}

}