package br.com.secureedges.core.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.secureedges.util.FacesUtil;
import br.com.secureedges.core.dao.UsuarioDAO;
import br.com.secureedges.domain.Usuario;

@ManagedBean
@SessionScoped  // diferença :  ele vai existir durante todo tempo de sessão
public class AutenticacaoBean {

	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null)
				usuarioLogado = new Usuario();
		return usuarioLogado;
	}
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String  autenticar(){
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado=usuarioDAO.autenticar(usuarioLogado.getCPF(), usuarioLogado.getSenha());
			
			if(usuarioLogado.getStatus().equals("desativado")){
				FacesUtil.adicionarMSGError("Usuário Desativado" );
			}
			else{
			System.out.println(usuarioLogado.getSenha());
			if(usuarioLogado == null){
				FacesUtil.adicionarMSGError("CPF ou Senha invalidos" );
				return null;
			}
			else{
					FacesUtil.adicionarMSGInfo("Usuario autenticado com sucesso");
					return "/templates/modeloGeral.xhtml?faces-redirect=true";
				}
				
			
			}
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String  sair (){
		usuarioLogado =  null;
		FacesUtil.adicionarMSGInfo("Realizado Logout");
		return "/templates/modeloGeral.xhtml?faces-redirect=true";
		
	}
	
}
