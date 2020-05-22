package com.apijccp.service.spring.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.apijccp.dao.RolUsuExample;
import com.apijccp.dao.RolUsuKey;
import com.apijccp.dao.mysql.mapper.RolUsuMapper;
import com.apijccp.exception.NoUserAuthenticatedException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SeguridadService {

	@Autowired
	RolUsuMapper relUsuMapper;
	
	public List<CustomAuthority> getAuthoritiesByIDUser(Integer idUser) {
		RolUsuExample filtro = new RolUsuExample();
		filtro.createCriteria()
		.andIdUsuarioEqualTo(idUser);
		
		List<RolUsuKey> rolesUsuario = relUsuMapper.selectByExample(filtro);
		
		if (rolesUsuario == null || rolesUsuario.isEmpty()) {
			return null;
		}
		
		List<CustomAuthority> authorities =  new ArrayList<>();
		
		rolesUsuario.forEach(ru -> authorities.add(new CustomAuthority(ru.getCodRol())));
		
		return authorities;
	}
	
	public  String getJWTTokenForCurrentAuth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null) {
			throw new NoUserAuthenticatedException("No existe usuario authenticado en el sistema.");
		}
		
		String username =  null;
		String idUsuario = null;
		List<? extends GrantedAuthority> grantedAuthorities = null;
		if (auth.getPrincipal() instanceof CustomUserDetails) {
			CustomUserDetails cwd = (CustomUserDetails) auth.getPrincipal();
			username = cwd.getUsername();
			idUsuario = cwd.getIdUsuario().toString();
			grantedAuthorities = cwd.getAuthorities();
		}
		
		String secretKey = "mySecretKey";
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.claim("username", username)
				.claim("idUsuario", idUsuario)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS256,
						secretKey.getBytes()).compact();

		return token;
	}
	
}
