package com.ingenieriahuemul.flamenco.app.model.dto;

import java.sql.Time;

public class EmpresaDTO {
	
	//tabla
	private String id;
	private String razonSocial;
	private String encabezado;
	private Time primerRegistro;
	private Integer periodo;
	private Integer columnasImpresion;
	private String qr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonsocial) {
		this.razonSocial = razonsocial;
	}
	public String getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(String encabezadoinforme) {
		this.encabezado = encabezadoinforme;
	}
	public Time getPrimerRegistro() {
		return primerRegistro;
	}
	public void setPrimerRegistro(Time horaprimerregistro) {
		this.primerRegistro = horaprimerregistro;
	}
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodoimpresion) {
		this.periodo = periodoimpresion;
	}
	public Integer getColumnasImpresion() {
		return columnasImpresion;
	}
	public void setColumnasImpresion(Integer columnasImpresion) {
		this.columnasImpresion = columnasImpresion;
	}
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
	}
	
	@Override
	public String toString() {
		return "EmpresaDTO [id=" + id + ", razonSocial=" + razonSocial + ", encabezado=" + encabezado
				+ ", primerRegistro=" + primerRegistro + ", periodo=" + periodo + ", columnasImpresion="
				+ columnasImpresion + ", qr=" + qr + "]";
	}
	
}
