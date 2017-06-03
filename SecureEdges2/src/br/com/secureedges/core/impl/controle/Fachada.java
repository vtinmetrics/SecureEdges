package br.com.secureedges.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.secureedges.core.IDAO;
import br.com.secureedges.core.IFachada;
import br.com.secureedges.core.dao.ComodoDAO;
import br.com.secureedges.core.dao.DispositivoDAO;
import br.com.secureedges.core.dao.SolicitacaoDAO;
import br.com.secureedges.core.dao.Tipo_DispositivoDAO;
import br.com.secureedges.core.dao.UsuarioDAO;
import br.com.secureedges.core.impl.negocio.ValidaDadosObrigatoriosComodo;
import br.com.secureedges.core.impl.negocio.ValidaDadosObrigatoriosDispositivo;
import br.com.secureedges.core.impl.negocio.ValidaDadosObrigatoriosTipo_Dispositivo;
import br.com.secureedges.core.impl.negocio.ValidaDadosObrigatoriosUsuario;
import br.com.secureedges.core.impl.negocio.ValidadorCpf;
import br.com.secureedges.core.impl.negocio.ValidadorDadosObrigatoriosSolicitacao;
import br.com.secureedges.domain.Comodo;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Resultado;
import br.com.secureedges.domain.Solicitacao;
import br.com.secureedges.domain.Tipo_Dispositivo;
import br.com.secureedges.domain.Usuario;
import br.com.secureedges.util.FacesUtil;
import br.com.secureedges.core.*;

public class Fachada implements IFachada {

	/**
	 * Mapa de DAOS, será indexado pelo nome da entidade O valor é uma instância
	 * do DAO para uma dada entidade;
	 */
	private static Map<String, IDAO> daos;

	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade; O
	 * valor é um mapa que de regras de negócio indexado pela operação
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;

	private Resultado resultado = new Resultado();

	public Fachada() {
		/* Intânciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Intânciando o Map de Regras de Negócio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();

		/* Criando instâncias dos DAOs a serem utilizados */
		ComodoDAO comodoDAO = new ComodoDAO();
		DispositivoDAO dispositivoDAO = new DispositivoDAO();
		SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
		Tipo_DispositivoDAO tipo_DispositivoDAO = new Tipo_DispositivoDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		daos.put(Comodo.class.getName(), (IDAO) comodoDAO);
		daos.put(Dispositivo.class.getName(), (IDAO) dispositivoDAO);
		daos.put(Solicitacao.class.getName(), (IDAO) solicitacaoDAO);
		daos.put(Tipo_Dispositivo.class.getName(), (IDAO) tipo_DispositivoDAO);
		daos.put(Usuario.class.getName(), (IDAO) usuarioDAO);

		/* Criando instâncias de regras de negócio a serem utilizados */
		ValidaDadosObrigatoriosComodo dadosObrigatoriosComodo = new ValidaDadosObrigatoriosComodo();
		ValidadorDadosObrigatoriosSolicitacao dadosObrigatoriosSolicitacao = new ValidadorDadosObrigatoriosSolicitacao();
		ValidadorCpf validadorCpf = new ValidadorCpf();
		ValidaDadosObrigatoriosUsuario dadosObrigatoriosUsuario = new ValidaDadosObrigatoriosUsuario();
		ValidaDadosObrigatoriosTipo_Dispositivo dadosObrigatoriosTipo_Dispositivo = new ValidaDadosObrigatoriosTipo_Dispositivo();
		ValidaDadosObrigatoriosDispositivo dadosObrigatoriosDispositivo = new ValidaDadosObrigatoriosDispositivo();

		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor
		 * quando a operação for Salvar
		 */
		List<IStrategy> rnsSalvarTipoDispositivo = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarUsuario = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarComodo = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarDispositivo = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarSolicitacao = new ArrayList<IStrategy>();

		// Tipo dispositivo
		rnsSalvarTipoDispositivo.add(dadosObrigatoriosTipo_Dispositivo);

		/*
		 * Adicionando as regras a serem utilizadas na operação Salvar do
		 * Solicitacao
		 */

		rnsSalvarUsuario.add(dadosObrigatoriosUsuario);
		rnsSalvarUsuario.add(validadorCpf);

		/*
		 * Adicionando as regras a serem utilizadas na operação Salvar do Comodo
		 */
		rnsSalvarComodo.add(dadosObrigatoriosComodo);

		// Dipositivo

		rnsSalvarDispositivo.add(dadosObrigatoriosDispositivo);

		// solicitacao
		rnsSalvarSolicitacao.add(dadosObrigatoriosSolicitacao);
		Map<String, List<IStrategy>> rnsTipoDisposito = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsUsuario = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsSolicitacao = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsComodo = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsDispositivo = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação Salvar no mapa do fornecedor
		 * (lista criada na linha 70)
		 */
		rnsTipoDisposito.put("Salvar", rnsSalvarTipoDispositivo);
		// // usa a mesma rns por ser as mesmas regras de negocio
		rnsTipoDisposito.put("Editar", rnsSalvarTipoDispositivo);
		//
		rnsSolicitacao.put("Salvar", rnsSalvarSolicitacao);
		rnsSolicitacao.put("Editar", rnsSalvarSolicitacao);
		//
		// // rns usuario
		rnsUsuario.put("Salvar", rnsSalvarUsuario);
		rnsUsuario.put("Editar", rnsSalvarUsuario);
		//
		// // rnsComodo
		rnsComodo.put("Salvar", rnsSalvarComodo);
		rnsComodo.put("Editar", rnsSalvarComodo);
		//
		// // rnsDispositivo
		//
		rnsDispositivo.put("Salvar", rnsSalvarDispositivo);
		rnsDispositivo.put("Editar", rnsSalvarDispositivo);
		//
		// /*
		// * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas
		// * operações no mapa geral indexado pelo nome da entidade
		// */
		rns.put(Tipo_Dispositivo.class.getName(), rnsTipoDisposito);
		rns.put(Usuario.class.getName(), rnsUsuario);
		rns.put(Solicitacao.class.getName(), rnsSolicitacao);
		rns.put(Comodo.class.getName(), rnsComodo);
		rns.put(Dispositivo.class.getName(), rnsDispositivo);

		System.out.println(rns.get(Tipo_Dispositivo.class.getName()));

	}

	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "Salvar");
		System.out.println(entidade.getClass().getName());

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			System.out.println("dao" + dao);
			try {
				dao.Salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
				FacesUtil.adicionarMSGInfo("Salvo com sucesso");
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "Editar");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.Editar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);

				FacesUtil.adicionarMSGInfo("Editado com sucesso");
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "Excluir");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.Excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);

				FacesUtil.adicionarMSGInfo("Excluido com sucesso");
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

	public List<EntidadeDominio> listar(EntidadeDominio entidade) {
		resultado = new Resultado();
		List<EntidadeDominio> lista = null;
		String nmClasse = entidade.getClass().getName();

		IDAO dao = daos.get(nmClasse);
		try {

			lista = (dao.listar());
		} catch (RuntimeException e) {
			e.printStackTrace();
			resultado.setMsg("Não foi possível realizar a listagem!");

		}

		return lista;

	}

	public static EntidadeDominio buscarGenerico(Long codigo, EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nmClasse);
		EntidadeDominio entidadeRetorno = null;
		entidadeRetorno = dao.buscarPorCodigo(codigo);
		return entidadeRetorno;

	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nmClasse);
		Usuario usuario = (Usuario) dao.consultar(entidade);
		String msg = executarRegras(entidade, "Consultar");
		if (msg == null) {
			if (usuario.getCPF() == null) {
				resultado.setMsg("Informação válida");
				FacesUtil.adicionarMSGInfo("Informação válida");
				return resultado;
			}
		} else {
			FacesUtil.adicionarMSGError("Informação inválida");
			resultado.setMsg("Informação invalida");
			return resultado;
		}
		return resultado;

	}

	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);

		if (regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (IStrategy s : regras) {
					String m = s.processar(entidade);

					if (m != null) {
						msg.append(m);
						msg.append("\n");
					}
				}
			}

		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}

	public Usuario autenticar(String cpf, String senha) {
		UsuarioDAO dao = new UsuarioDAO();

		return dao.autenticar(cpf, senha);

	}

}