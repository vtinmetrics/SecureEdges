package br.com.secureedges.domain;

import java.util.Date;

public class Log extends EntidadeDominio{
	
 Usuario usuario = new Usuario() ;
 Dispositivo dispositivo = new Dispositivo();
 String status;
 Date data;
public Usuario getUsuario() {
	return usuario;
}
public Dispositivo getDispositivo() {
	return dispositivo;
}
public String getStatus() {
	return status;
}
public Date getData() {
	return data;
}
public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}
public void setDispositivo(Dispositivo dispositivo) {
	this.dispositivo = dispositivo;
}
public void setStatus(String status) {
	this.status = status;
}
public void setData(Date data) {
	this.data = data;
}
 

}
