package br.com.secureedges.core.web.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.secureedges.core.dao.ComodoDAO;
import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.core.dao.SolicitacaoDAO;
import br.com.secureedges.core.dao.Tipo_DispositivoDAO;
import br.com.secureedges.core.impl.controle.Fachada;
import br.com.secureedges.core.web.command.ICommand;
import br.com.secureedges.core.web.impl.AlterarCommand;
import br.com.secureedges.core.web.impl.SalvarCommand;
import br.com.secureedges.domain.Comodo;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Item;
import br.com.secureedges.domain.Log;
import br.com.secureedges.domain.Resultado;
import br.com.secureedges.domain.Solicitacao;
import br.com.secureedges.domain.Tipo_Dispositivo;
import br.com.secureedges.domain.Usuario;
import br.com.secureedges.util.FacesUtil;

@ManagedBean
@ViewScoped
public class SolicitacaoBean {
	public String usuarioNome;
	public List<EntidadeDominio> listaDispositivos = new ArrayList<>();
	List<Dispositivo> listaDispositivosFiltrados;
	public List<EntidadeDominio> listalog =  new ArrayList<>();
	List<EntidadeDominio> listalogFiltradas;
	private List<Item> listaItens;
	private Solicitacao solicitacaoCadastro;
	private static Map<String, ICommand> commands;
	// pegando o valor que esta no autenticacaoBean passando para a variavel
	// local o valor do bean
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean = new AutenticacaoBean();
	public List<EntidadeDominio> bkplistaDispositivos;
	private String acao;
	private Fachada Fachada = new Fachada();

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

	public List<EntidadeDominio> getListalistalogFiltradas() {
		if (listalogFiltradas == null)
			listalogFiltradas = new ArrayList<>();
		return listalogFiltradas;
	}

	public void setListalistalogFiltradas(List<EntidadeDominio> listalogFiltradas) {
		this.listalogFiltradas = listalogFiltradas;
	}

	public List<EntidadeDominio> getListasolicitacoes() {
		if (listalog == null)
			listalog = new ArrayList<>();
		return listalog;
	}

	public void setListasolicitacoes(List<EntidadeDominio> listalog) {
		this.listalog = listalog;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public List<Dispositivo> getlistaDispositivosFiltrados() {
		return listaDispositivosFiltrados;
	}

	//
	public void setlistaDispositivosFiltrados(List<Dispositivo> listaDispositivosFiltrados) {
		this.listaDispositivosFiltrados = listaDispositivosFiltrados;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public List<EntidadeDominio> getListaDispositivos() {
		if (listaDispositivos == null)
			listaDispositivos = new ArrayList<>();
		return listaDispositivos;
	}

	public void setListaDispositivos(List<EntidadeDominio> listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}

	public SolicitacaoBean() {
		/*
		 * Utilizando o command para chamar a fachada e indexando cada command
		 * pela opera��o garantimos que esta servelt atender� qualquer opera��o
		 */
		commands = new HashMap<String, ICommand>();
		commands.put("Salvar", new SalvarCommand());
		commands.put("Editar", new AlterarCommand());
		try {

		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Dispositivos:" + ex.getMessage());

		}
	}

	public void setsolicitacaoCadastro(Solicitacao solicitacaoCadastro) {
		this.solicitacaoCadastro = solicitacaoCadastro;
	}

	public Solicitacao getSolicitacaoCadastro() {
		if (solicitacaoCadastro == null)
			solicitacaoCadastro = new Solicitacao();
		return solicitacaoCadastro;
	}

	public void salvar() {
		try {
			System.out.println(solicitacaoCadastro.getDescricao());
			// Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(acao);
			/*
			 * Executa o command que chamar� a fachada para executar a opera��o
			 * requisitada o retorno � uma inst�ncia da classe resultado que
			 * pode conter mensagens derro ou entidades de retorno
			 */
			solicitacaoCadastro.setUsuario(autenticacaoBean.getUsuarioLogado());
			command.execute(solicitacaoCadastro);
			solicitacaoCadastro = new Solicitacao();

		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar incluir:" + ex.getMessage());

		}

	}

	public void carregarDispositivos() {
		List<EntidadeDominio> listaRecebe = new ArrayList<>();
		listaRecebe = Fachada.listar(new Dispositivo());
		ComodoDAO comodoDAO = new ComodoDAO();
		Tipo_DispositivoDAO tipo_DispositivoDAO = new Tipo_DispositivoDAO();
		System.out.println(listaRecebe.size());
		listaDispositivos = new ArrayList<>();
		System.out.println(autenticacaoBean.getUsuarioLogado().getNome());
		for (EntidadeDominio dispositivoList : listaRecebe) {
			if (dispositivoList instanceof Dispositivo) {
				Long codigoComodo = ((Dispositivo) dispositivoList).getComodo().getCodigo();
				Long codigoTipo = ((Dispositivo) dispositivoList).getTP_Dispositivo().getCodigo();
				Comodo auxcmd = (Comodo) comodoDAO.buscarPorCodigo(codigoComodo);
				Tipo_Dispositivo auxTP = (Tipo_Dispositivo) tipo_DispositivoDAO.buscarPorCodigo(codigoTipo);
				((Dispositivo) dispositivoList).setComodo(auxcmd);
				((Dispositivo) dispositivoList).setTP_Dispositivo(auxTP);
				listaDispositivos.add(dispositivoList);
			}

		}
	}

	public List<Item> getListaItens() {
		if (listaItens == null)
			listaItens = new ArrayList<>();
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens) {
		this.listaItens = listaItens;
	}

	public void adicionar(Dispositivo produto) {
		int posicaoEncontrada = -1; // come�a -1 pra ser nenhuma posi��o
		Dispositivo produtoTemp = null;

		// verifica se o produto que ir� ser adicionado ja esta na lista de
		// itens
		for (int pos = 0; pos < listaItens.size() && posicaoEncontrada < 0; pos++) {
			Item itemTemp = listaItens.get(pos);

			if (itemTemp.getDispositivo().equals(produto)) {
				// ja encontrou o produto, pega a posi��o dele
				posicaoEncontrada = pos;

			}

		}

		for (Item item : listaItens) {
			if (item.getDispositivo().equals(produto)) {
				produtoTemp = new Dispositivo();
				produtoTemp = item.getDispositivo();
			}
		}

		Item item = new Item();
		item.setDispositivo(produto);

		// produto novo
		if (posicaoEncontrada < 0) {

			// verifica se a quantidade vendida � viavel

			listaItens.add(item);

		}

		// produto ja inserido na lista de itens
		else {
			Item itemTemp = listaItens.get(posicaoEncontrada);
			listaItens.set(posicaoEncontrada, item);
			listaItens.set(posicaoEncontrada, item);

		}

	}

	public void carregarPesquisa() {
		try {
			listalog = Fachada.listar(new Solicitacao());
		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Usuarios:" + ex.getMessage());

		}
	}

	public void manipularSolicitacao() {
		try {
			String valor = FacesUtil.getParam("solSts");
			Long cod = Long.parseLong(FacesUtil.getParam("solCod"));
			SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
			solicitacaoCadastro = (Solicitacao) solicitacaoDAO.buscarPorCodigo(cod);
			if (valor != null) {
				ICommand command = commands.get("Editar");
				solicitacaoCadastro.setStatus(valor);
				command.execute(solicitacaoCadastro);
			}

		} catch (RuntimeException ex) {

		}
	}
	
	
	public void carregarPesquisaDetalhada() {
		try {
			List<EntidadeDominio> listarecebe = new ArrayList<>();
			listarecebe = Fachada.listar(new Solicitacao());
			for(EntidadeDominio solicitacao : listarecebe){
				if(solicitacao instanceof Solicitacao){
					if(((Solicitacao) solicitacao).getUsuario().getCodigo() == autenticacaoBean.getUsuarioLogado().getCodigo()){
						listalog.add(solicitacao);
					}
				}
			}
		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Usuarios:" + ex.getMessage());

		}
	}
	

}