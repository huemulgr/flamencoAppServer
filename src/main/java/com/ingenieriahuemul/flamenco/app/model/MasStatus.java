package com.ingenieriahuemul.flamenco.app.model;

public class MasStatus {
	
	private String nombre;
	private String valor;
	private String fechaHoraActualizado;
	private String orden;
	private String idEmpresa;
	private String status;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getFechaHoraActualizado() {
		return fechaHoraActualizado;
	}
	public void setFechaHoraActualizado(String fechaHoraActualizado) {
		this.fechaHoraActualizado = fechaHoraActualizado;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "MasStatus [nombre=" + nombre + ", valor=" + valor + ", fechaHoraActualizado=" + fechaHoraActualizado
				+ ", orden=" + orden + ", idEmpresa=" + idEmpresa + ", status=" + status + "]";
	}
	
	
	
}
