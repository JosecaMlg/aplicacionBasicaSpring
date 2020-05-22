package com.apijccp.vo;

public class RespLogin {
	
	private String jwtToken;
	private String idUser;

	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	@Override
	public String toString() {
		return "RespLogin [jwtToken=" + jwtToken + ", idUser=" + idUser + "]";
	}
}
