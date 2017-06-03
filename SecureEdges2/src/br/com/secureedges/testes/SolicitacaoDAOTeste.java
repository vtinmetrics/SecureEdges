//package br.com.secureedges.testes;
//
//import java.util.List;
//
//import org.junit.Ignore;
//import org.junit.Test;
//
//import br.com.secureedges.dao.ComodoDAO;
//import br.com.secureedges.dao.DispositivoDAO;
//import br.com.secureedges.dao.SolicitacaoDAO;
//import br.com.secureedges.dao.UsuarioDAO;
//import br.com.secureedges.models.Solicitacao;
//
//public class SolicitacaoDAOTeste {
//	@Test
//	public void salvar (){
//		Solicitacao solicitacao = new Solicitacao();
//		SolicitacaoDAO.salvar(solicitacao);
//		}
//	
//	@Test
//	public void listar() {
//		SolicitacaoDAO dao = new SolicitacaoDAO();
//		List<Solicitacao> solicitacao = dao.listar();
//		System.out.println("\n");
//		System.out.println(solicitacao);
//		System.out.println("\n");
//	}
//
//	@Test
//	public void buscarPorCodigo() {
//		SolicitacaoDAO dao = new SolicitacaoDAO();
//		Solicitacao f1 = dao.buscarPorCodigo(1L);
//		System.out.println(f1);
//	}
//
//	@Test
//	@Ignore
//	public void Excluir() {
//		SolicitacaoDAO dao = new SolicitacaoDAO();
//		Solicitacao solicitacao = dao.buscarPorCodigo(2L);
//		if (solicitacao != null)
//			dao.excluir(solicitacao);
//	}
//
//	/*
//	 * @Test
//	 * 
//	 * @Ignore public void ExcluirPorCodigo(Long codigo) { SolicitacaoDAOdao = new
//	 * SolicitacaoDAO(); dao.Excluir(3L);
//	 * 
//	 * }
//	 */
//
//	@Test
//	public void Editar() {
//		SolicitacaoDAO dao = new SolicitacaoDAO();
//		Solicitacao solicitacao = dao.buscarPorCodigo(3L);
//		
//		if (solicitacao != null)
//			dao.editar(solicitacao);
//	}
//	
//
//}
