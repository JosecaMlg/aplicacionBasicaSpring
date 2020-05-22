package com.apijccp.vo.alta.usuario;

public class RegistroSalida {

	boolean hasError;
	String idUsuario;
	String cod_usuario;
	String password;
	
	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCod_usuario() {
		return cod_usuario;
	}
	public void setCod_usuario(String cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegistroSalida [hasError=" + hasError + ", idUsuario=" + idUsuario + ", cod_usuario=" + cod_usuario
				+ ", password=" + password + "]";
	}
	
}
