package com.apijccp.model;

import com.apijccp.dao.UsuarioWeb.TipEstado;
import com.apijccp.exception.UsuarioException;

public class Usuario {
	
	private final Integer idUsuario;
	private final String codUsuario;
	private final String password;
	private final TipEstado estado;
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public String getPassword() {
		return password;
	}
	public TipEstado getEstado() {
		return estado;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", cod_usuario=" + codUsuario + ", password=" + password + "]";
	}
	
	public static class UsuarioBuilder {
		private Integer idUsuario;
		private String codUsuario;
		private String password;
		private TipEstado estado;
		
		public UsuarioBuilder (String codUsuario, String password) {
			this.codUsuario = codUsuario;
			this.password = password;
		}
		
		public UsuarioBuilder withIdUsuario(Integer idUsuario) {
			this.idUsuario = idUsuario;
			return this;
		}
		
		public UsuarioBuilder withIdEstado(TipEstado estado) {
			this.estado = estado;
			return this;
		}
		
		public Usuario build() throws UsuarioException {
			/**
			 * Check params
			 */
			if (estado == null) {
				throw new UsuarioException (UsuarioException.TipException.PARAMETROS_INCORRECTOS, "estado is null");
			}
			if (password == null) {
				throw new UsuarioException (UsuarioException.TipException.PARAMETROS_INCORRECTOS, "password is null");
			}
			if (codUsuario == null) {
				throw new UsuarioException (UsuarioException.TipException.PARAMETROS_INCORRECTOS, "codUsuario is null");
			}
			
			return new Usuario(this);
		}
		
	}
	private Usuario(UsuarioBuilder builder) throws UsuarioException {
		this.idUsuario = builder.idUsuario;
		this.codUsuario = builder.codUsuario;
		this.password = builder.password;
		this.estado = builder.estado;
	}
	
}
