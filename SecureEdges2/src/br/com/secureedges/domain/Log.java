package br.com.secureedges.domain;

import java.util.Date;

public class Log extends EntidadeDominio{
	
 Long usuario ;
 Long dispositivo;
 String status;
 Date data;
public Long getUsuario() {
	return usuario;
}
public Long getDispositivo() {
	return dispositivo;
}
public String getStatus() {
	return status;
}
public Date getData() {
	return data;
}
public void setUsuario(Long usuario) {
	this.usuario = usuario;
}
public void setDispositivo(Long dispositivo) {
	this.dispositivo = dispositivo;
}
public void setStatus(String status) {
	this.status = status;
}
public void setData(Date data) {
	this.data = data;
}
 

}
