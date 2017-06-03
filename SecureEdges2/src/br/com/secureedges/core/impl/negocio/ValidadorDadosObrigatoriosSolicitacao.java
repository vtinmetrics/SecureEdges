package br.com.secureedges.core.impl.negocio;

import java.math.BigDecimal;
import java.util.Date;

import br.com.secureedges.core.IStrategy;
import br.com.secureedges.util.FacesUtil;
import br.com.secureedges.domain.Dispositivo;
import br.com.secureedges.domain.EntidadeDominio;
import br.com.secureedges.domain.Tipo_Dispositivo;
import br.com.secureedges.domain.Usuario;
import br.com.secureedges.domain.Solicitacao;

public class ValidadorDadosObrigatoriosSolicitacao implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Solicitacao){
			Solicitacao Solicitacao = (Solicitacao)entidade;
			Usuario usuario=Solicitacao.getUsuario();
			String descricao =Solicitacao.getDescricao();
			Dispositivo dispositivo =  Solicitacao.getDispositivo();
			
			
			
			
			if(usuario==null || descricao==null || dispositivo==null){
				FacesUtil.adicionarMSGError("Todos os campos  são obrigatórios e devem ser preenchidos corretamente!");
				return  "erro";
			}
			
			
		}else{
			FacesUtil.adicionarMSGError("Os Dados devem ser preenchidos corretamente");
			return  "erro";
		}
		
		return null;
	}

}
