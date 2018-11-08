package com.ingenieriahuemul.flamenco.app.model.dto;

public class MessagePush {

	private String titulo;
	private String mensaje;
	private String idEmpresa;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Override
	public String toString() {
		return "MessagePush [titulo=" + titulo + ", mensaje=" + mensaje + ", idEmpresa=" + idEmpresa + "]";
	}

}
