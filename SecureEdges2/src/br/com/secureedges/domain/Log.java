package br.com.secureedges.domain;

import java.util.Date;

public class Log extends EntidadeDominio{
	
 Usuario usuario ;
 Dispositivo dispositivo;
 String status;
 Date data;
 
public Usuario getUsuario() {
	if (usuario == null)
		usuario =  new Usuario();
	return usuario;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public Dispositivo getDispositivo() {
	if (dispositivo == null)
		dispositivo =  new Dispositivo();
	return dispositivo;
}
public void setDispositivo(Dispositivo dispositivo) {
	this.dispositivo = dispositivo;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Date getData() {
	return data;
}
public void setData(Date data) {
	this.data = data;
}
 
 
}
