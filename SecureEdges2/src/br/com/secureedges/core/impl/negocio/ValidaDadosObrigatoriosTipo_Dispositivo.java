package br.com.secureedges.core.impl.negocio;

import br.com.secureedges.core.IStrategy;
import br.com.secureedges.util.FacesUtil;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Tipo_Dispositivo;

public class ValidaDadosObrigatoriosTipo_Dispositivo implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Tipo_Dispositivo){
			Tipo_Dispositivo Tipo_Dispositivo = (Tipo_Dispositivo)entidade;
			String descricao = Tipo_Dispositivo.getDescricao();
		
			
			
			
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
