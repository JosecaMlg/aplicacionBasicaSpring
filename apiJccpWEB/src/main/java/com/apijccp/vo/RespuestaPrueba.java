package com.apijccp.vo;

public class RespuestaPrueba {
	private String titulo;
	private String texto;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	@Override
	public String toString() {
		return "RespuestaPrueba [titulo=" + titulo + ", texto=" + texto + "]";
	}
	
}
