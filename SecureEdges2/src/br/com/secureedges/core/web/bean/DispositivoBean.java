package br.com.secureedges.core.web.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.secureedges.core.ClasseListener;
import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.core.dao.LogDAO;
import br.com.secureedges.core.dao.SolicitacaoDAO;
import br.com.secureedges.core.impl.controle.Fachada;
import br.com.secureedges.core.web.command.ICommand;
import br.com.secureedges.core.web.impl.AlterarCommand;
import br.com.secureedges.core.web.impl.ExcluirCommand;
import br.com.secureedges.core.web.impl.SalvarCommand;
import br.com.secureedges.domain.Comodo;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Log;
import br.com.secureedges.domain.ReportLog;
import br.com.secureedges.domain.Solicitacao;
import br.com.secureedges.domain.Tipo_Dispositivo;
import br.com.secureedges.util.FacesUtil;

@ManagedBean
@ViewScoped
public class DispositivoBean extends EntidadeDominio {
	private Dispositivo DispositivoCadastro;
	List<EntidadeDominio> listaDispositivos = new ArrayList<>();
	List<Dispositivo> listaDispositivosFiltrados;
	List<EntidadeDominio> listaComodos;
	List<EntidadeDominio> listaTipo_Dispositivos;
	Fachada Fachada = new Fachada();
	private String acao;
	private Long codigo;
	private static Map<String, ICommand> commands;
	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean = new AutenticacaoBean();

	public DispositivoBean() {
		/*
		 * Utilizando o command para chamar a fachada e indexando cada command pela
		 * operação garantimos que esta servelt atenderá qualquer operação
		 */
		commands = new HashMap<String, ICommand>();
		commands.put("Salvar", new SalvarCommand());
		commands.put("Excluir", new ExcluirCommand());
		commands.put("Editar", new AlterarCommand());
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public List<EntidadeDominio> getListaTipo_Dispositivos() {
		return listaTipo_Dispositivos;
	}

	public void setListaTipo_Dispositivos(List<EntidadeDominio> listaTipo_Dispositivos) {
		this.listaTipo_Dispositivos = listaTipo_Dispositivos;
	}

	public static Map<String, ICommand> getCommands() {
		return commands;
	}

	public static void setCommands(Map<String, ICommand> commands) {
		DispositivoBean.commands = commands;
	}

	public List<EntidadeDominio> getListaComodos() {
		return listaComodos;
	}

	public void setListaComodos(List<EntidadeDominio> listaComodos) {
		this.listaComodos = listaComodos;
	}

	public Dispositivo getDispositivoCadastro() {
		if (DispositivoCadastro == null)
			DispositivoCadastro = new Dispositivo();

		return DispositivoCadastro;
	}

	public void setDispositivoCadastro(Dispositivo DispositivoCadastro) {
		this.DispositivoCadastro = DispositivoCadastro;
	}

	public List<EntidadeDominio> getListaDispositivos() {
		if (listaDispositivos == null) {
			listaDispositivos = new ArrayList<>();
		}
		return listaDispositivos;
	}

	public void setListaDispositivos(List<EntidadeDominio> listaDispositivos) {
		this.listaDispositivos = listaDispositivos;
	}

	public List<Dispositivo> getListaDispositivosFiltrados() {
		return listaDispositivosFiltrados;
	}

	public void setListaDispositivosFiltrados(List<Dispositivo> listaDispositivosFiltrados) {
		this.listaDispositivosFiltrados = listaDispositivosFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void novo() {
		DispositivoCadastro = new Dispositivo();
	}

	public void salvar() {
		DispositivoCadastro.setStatus("Ativo");
		try {
			// Obtêm o command para executar a respectiva operação
			ICommand command = commands.get(acao);
			/*
			 * Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro
			 * ou entidades de retorno
			 */
			command.execute(DispositivoCadastro);
			DispositivoCadastro = new Dispositivo();

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Dispositivo:" + ex.getMessage());

		}

	}

	public void excluir() {
		try {

			// Obtêm o command para executar a respectiva operação
			ICommand command = commands.get(acao);
			/*
			 * Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro
			 * ou entidades de retorno
			 */
			command.execute(DispositivoCadastro);

			DispositivoCadastro = new Dispositivo();

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMSGError("Erro ao tentar Excluir Dispositivo:" + ex.getMessage());

		}

	}

	public void editar() {
		try {
			// Obtêm o command para executar a respectiva operação
			ICommand command = commands.get(acao);
			/*
			 * Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro
			 * ou entidades de retorno
			 */
			command.execute(DispositivoCadastro);
			DispositivoCadastro = new Dispositivo();

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Dispositivo:" + ex.getMessage());

		}

	}

	public void manipular() {
		String statusPro;
		Log log = new Log();
		if (DispositivoCadastro.getStatus().equals("Ativo")) {
			statusPro = "Desativado";

		} else {
			statusPro = "Ativo";
			log.setStatus("Aativado");
		}
		try {
			DispositivoCadastro.setStatus(statusPro);
			// Obtêm o command para executar a respectiva operação
			ICommand command = commands.get("Editar");
			/*
			 * Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro
			 * ou entidades de retorno
			 */
			command.execute(DispositivoCadastro);

			DispositivoCadastro = new Dispositivo();

		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Dispositivo:" + ex.getMessage());

		}

	}

	public void carregarCadastro() {
		try {
			if (codigo != null) {
				DispositivoCadastro = (Dispositivo) Fachada.buscarGenerico(codigo, new Dispositivo());

			}

		} catch (RuntimeException ex) {

		}
	}

	public void carregarPesquisa() {
		try {

			listaDispositivos = Fachada.listar(new Dispositivo());
		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Dispositivoes:" + ex.getMessage());

		}
	}

	public void carregarPesquisaComodo() {
		try {

			listaComodos = Fachada.listar(new Comodo());

		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Comodoes:" + ex.getMessage());

		}
	}

	public void carregarPesquisaTipo_Dispositivos() {
		try {

			listaTipo_Dispositivos = Fachada.listar(new Tipo_Dispositivo());
		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Tipo_Dispositivos:" + ex.getMessage());

		}
	}

	public boolean manipular(Dispositivo dispositivo) {

		Log log = new Log();
		ClasseListener objArduino = new ClasseListener();
		try {
			int power = 0;
			if (dispositivo.getDisp_status() == 0) {
				power = 1;
				log.setStatus("Ativado");
				dispositivo.setDisp_status(1);
				
				
				
				System.out.println("o status agora é:" + dispositivo.getDisp_status());

				String aux = dispositivo.getInterface_Arduino().toString();
				int teste = Integer.parseInt(aux);

				System.out.println("Send power:" + power + "\n Interface: " + teste);
				objArduino.getLink().sendPowerPinSwitch(teste, power); // Send
																		// energy to
				// the right pin
				// of your
				// sensor
				ICommand command = commands.get("Editar");
				/*
				 * Executa o command que chamará a fachada para executar a operação requisitada
				 * o retorno é uma instância da classe resultado que pode conter mensagens derro
				 * ou entidades de retorno
				 */
				command.execute(dispositivo);
				log.setDispositivo(dispositivo);
				log.setData(new Date());
				log.setUsuario(autenticacaoBean.getUsuarioLogado());
				command = commands.get("Salvar");
				command.execute(log);
				return true;
				
				
				
			} 
			
			
			
			else if (dispositivo.getDisp_status() == 1) {
				power = 0;
				log.setStatus("Desativado");
				dispositivo.setDisp_status(0);
				
				
				
				
				
				System.out.println("o status agora é:" + dispositivo.getDisp_status());

				String aux = dispositivo.getInterface_Arduino().toString();
				int teste = Integer.parseInt(aux);

				System.out.println("Send power:" + power + "\n Interface: " + teste);
				objArduino.getLink().sendPowerPinSwitch(teste, power); // Send
																		// energy to
				// the right pin
				// of your
				// sensor
				ICommand command = commands.get("Editar");
				/*
				 * Executa o command que chamará a fachada para executar a operação requisitada
				 * o retorno é uma instância da classe resultado que pode conter mensagens derro
				 * ou entidades de retorno
				 */
				command.execute(dispositivo);
				log.setDispositivo(dispositivo);
				log.setData(new Date());
				log.setUsuario(autenticacaoBean.getUsuarioLogado());
				command = commands.get("Salvar");
				command.execute(log);
				LogDAO dao =  new LogDAO();
				log = (Log) dao.pegarultimo(dispositivo.getCodigo());
				ReportLog reportLog = new ReportLog();
				
				SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				Date atualaux = new Date();
				reportLog.setDataDesligado(new Timestamp( atualaux.getTime()));
				Date dataLiga =  dao.pegarData(dispositivo.getCodigo());
				reportLog.setDataLigado((Timestamp) dataLiga);
				System.out.println("Data ligado: " + dataLiga);
				System.out.println("Data desligado: " + reportLog.getDataDesligado());
				long intervalo = getDateDiff(dataLiga, reportLog.getDataDesligado(), TimeUnit.MINUTES);
				System.out.println("intervalo: " + intervalo);
				reportLog.setIntervalo((int) intervalo);
				reportLog.setDispositivo(dispositivo.getDescricao());
				command = commands.get("Salvar");
				command.execute(reportLog);
				return true;
				
				
				
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public void carregarPesquisaDetalhada() {
		try {
			SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
			List<EntidadeDominio> solicitacoes = solicitacaoDAO.listar();
			DispositivoDAO dispositivoDAO = new DispositivoDAO();
			List<Long> ids = new ArrayList<>();
			for (EntidadeDominio solicitacao : solicitacoes) {
				if (((Solicitacao) solicitacao).getStatus().equals("aprovada")) {
					if (solicitacao instanceof Solicitacao) {
						if (((Solicitacao) solicitacao).getUsuario().getCodigo() == autenticacaoBean.getUsuarioLogado()
								.getCodigo()) {
							ids.add(((Solicitacao) solicitacao).getDispositivo().getCodigo());

						}

					}
				}
			}

			for (int i = 0; i < ids.size(); i++) {

				listaDispositivos.add(dispositivoDAO.buscarPorCodigo(ids.get(i)));
			}

		} catch (

		RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Dispositivoes:" + ex.getMessage());

		}
	}

	public boolean manipular2(Dispositivo dispositivo) {

		Log log = new Log();
		ClasseListener objArduino = new ClasseListener();
		try {
			int power = 0;
			if (dispositivo.getDisp_status() == 0) {
				power = 1;
				log.setStatus("Ativado");
				dispositivo.setDisp_status(1);
				String aux = dispositivo.getInterface_Arduino().toString();
				int teste = Integer.parseInt(aux);

				System.out.println("Send power:" + power + "\n Interface: " + teste);
				objArduino.getLink().sendPowerPinSwitch(teste, power); // Send

				System.out.println("o status agora é:" + dispositivo.getDisp_status());

				// energy to
				// the right pin
				// of your
				// sensor
				ICommand command = commands.get("Editar");
				/*
				 * Executa o command que chamará a fachada para executar a operação requisitada
				 * o retorno é uma instância da classe resultado que pode conter mensagens derro
				 * ou entidades de retorno
				 */
				command.execute(dispositivo);
				log.setDispositivo(dispositivo);
				log.setData(new Date());
				log.setUsuario(autenticacaoBean.getUsuarioLogado());
				command = commands.get("Salvar");
				command.execute(log);
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

}