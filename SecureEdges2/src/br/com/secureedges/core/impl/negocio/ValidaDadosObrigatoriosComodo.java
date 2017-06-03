package br.com.secureedges.core.impl.negocio;

import br.com.secureedges.core.IStrategy;
import br.com.secureedges.util.FacesUtil;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Comodo;

public class ValidaDadosObrigatoriosComodo implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Comodo){
			Comodo Comodo = (Comodo)entidade;
			String descricao = Comodo.getDescricao();
		
			
			
			
			if(descricao==null){
				FacesUtil.adicionarMSGError("Todos os campos  são obrigatórios e devem ser preenchidos corretamente!");
				return  "erro";
			}
			
			if(descricao.trim().equals("")){
				FacesUtil.adicionarMSGError("Todos os campos são obrigatórios e devem ser preenchidos corretamente!");
				return  "erro";
			}
			
		}else{
			FacesUtil.adicionarMSGError("Os Dados devem ser preenchidos corretamente");
			return  "erro";
		}
		
		return null;
	}

}
