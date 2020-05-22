package com.apijccp.service.usuarios;

import com.apijccp.dao.UsuarioWeb;
import com.apijccp.model.Usuario;

public interface UsuariosService {

	public void holaMundo();
	
	UsuarioWeb registraUsuario(Usuario usuario);
	
	UsuarioWeb getUsuariobyUsername(String username);
}
