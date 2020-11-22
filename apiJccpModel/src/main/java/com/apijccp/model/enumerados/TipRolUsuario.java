package com.apijccp.model.enumerados;

public enum TipRolUsuario {

	COMMON_USER ("ROL_AUTENTICADO"),
	ADMINISTRATOR ("ROL_AUTENTICADO", "ROL_ADMINISTRATOR");

	private String[] roles;
	
	TipRolUsuario(String... roles) {
		this.roles = roles;
	}

	public String[] getRoles() {
		return roles;
	}
}
