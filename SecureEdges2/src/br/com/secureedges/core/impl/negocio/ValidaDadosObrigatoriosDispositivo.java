package br.com.secureedges.core.impl.negocio;

import java.math.BigDecimal;

import br.com.secureedges.core.IStrategy;
import br.com.secureedges.util.FacesUtil;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Tipo_Dispositivo;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.Comodo;

public class ValidaDadosObrigatoriosDispositivo implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Dispositivo){
			Dispositivo Dispositivo = (Dispositivo)entidade;
			String descricao = Dispositivo.getDescricao();
			Long codigoComodo = Dispositivo.getComodo().getCodigo();
			Long codigoTP = Dispositivo.getTP_Dispositivo().getCodigo();
		
			// realizado esse processo para verificar a quantidade
			
			if(descricao==null || codigoComodo==null
			|| codigoTP==null) {
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
