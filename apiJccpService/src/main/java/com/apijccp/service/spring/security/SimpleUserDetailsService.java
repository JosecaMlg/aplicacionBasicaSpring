package com.apijccp.service.spring.security;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.apijccp.dao.UsuarioWeb;
import com.apijccp.dao.UsuarioWeb.TipEstado;
import com.apijccp.service.usuarios.UsuariosService;

public class SimpleUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsuariosService usuariosService;
	
	@Autowired
	SeguridadService seguridadService;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioWeb uw = usuariosService.getUsuariobyUsername(username);
		if (uw == null)
			throw new UsernameNotFoundException(String.format("El usuario %s no existe en la tabla USUARIO_WEB.", username));
		
		List<CustomAuthority> authorities = seguridadService.getAuthoritiesByIDUser(uw.getIdUsuario());
		
		CustomUserDetails udtails = new CustomUserDetails.
				CustomUserDetailsBuilder(uw.getIdUsuario(), uw.getPassword(), uw.getCodUsuario())
				.setAccountNonExpired(true)
				.setCredentialsNonExpired(isCredentialsNonExpired(uw.getFecCaducidadPass()))
				.setAccountNonLocked(true)
				.setEnabled(uw.getTipEstado()!=TipEstado.BAJA) //Si es distinto de Baja es que esta el usuario Activo
				.setAuthorities(authorities)
				.build();
		
		return udtails;
	}

	private boolean isCredentialsNonExpired(Date fecCaducidadPass) {
		return fecCaducidadPass.toInstant().isAfter(Instant.now());
	}
}
