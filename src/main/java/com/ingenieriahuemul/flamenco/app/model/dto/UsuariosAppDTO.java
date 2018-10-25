package com.ingenieriahuemul.flamenco.app.model.dto;

public class UsuariosAppDTO {
	private String idUsuario;
	private String email;
	private String empresa;
	
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "UsuariosAppDTO [email=" + email + ", empresa=" + empresa + "]";
	}
	
	
}
