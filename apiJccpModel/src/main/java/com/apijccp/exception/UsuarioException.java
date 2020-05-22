package com.apijccp.exception;


public class UsuarioException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum TipException{
		PARAMETROS_INCORRECTOS("UsuarioException Ha ocurrido un error con los parametros de entrada INCORRECTOS");
		
		private String mensaje;

		private TipException(String mensaje) {
			this.mensaje = mensaje;
		}

		public String getMensaje() {
			return mensaje;
		}
		
	}
	private TipException tipoExcepcion;
	
	public UsuarioException(TipException tipoExcepcion) {
		this(tipoExcepcion, null);
	}
	

	public UsuarioException(TipException tipoExcepcion, String msgAmpliado) {
		this(tipoExcepcion, msgAmpliado, null);
	}
	
	public UsuarioException(TipException tipoExcepcion, String msgAmpliado, Throwable e) {
		super(getMsgAmpliado(tipoExcepcion, msgAmpliado), e);
		this.tipoExcepcion = tipoExcepcion;
	}

	public TipException getTipoExcepcion() {
		return tipoExcepcion;
	}

	@Override
	public String toString() {
		return "UsuarioException [tipoExcepcion=" + tipoExcepcion + "]";
	}


	private static String getMsgAmpliado(TipException tipoExcepcion2, String msgAmpliado) {
		if (msgAmpliado == null) {
			return tipoExcepcion2.getMensaje();
		}
		
		return tipoExcepcion2.getMensaje().concat(" :: "+msgAmpliado);
	}
	
	
	
}
