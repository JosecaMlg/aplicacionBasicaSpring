package com.apijccp.vo.alta.usuario;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.apijccp.model.enumerados.TipRolUsuario;

public class RegistroEntrada {
	
	@Pattern(regexp = "^([A-Z]|[a-z]|[0-9]){3,12}$")
	private String cod_usuario;
	
	@Pattern(regexp = "^(?=(.{0,}[A-Z].{0,}))(?=(.{0,}[0-9].{0,}))(?!.{0,}\\s.{0,}).{8,16}$", message = "{custom.message.pattern}")
	private String password;
	
	@NotNull
	private TipRolUsuario rol;
	
	
	public TipRolUsuario getRol() {
		return rol;
	}
	public void setRol(TipRolUsuario rol) {
		this.rol = rol;
	}
	public String getPassword() {
		return password;
	}
	public void setCod_usuario(String cod_usuario) {
		this.cod_usuario = cod_usuario;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCod_usuario() {
		return cod_usuario;
	}
	
	@Override
	public String toString() {
		return "RegistroEntrada [cod_usuario=" + cod_usuario + ", password=" + password + ", rol=" + rol + "]";
	}
	
}
