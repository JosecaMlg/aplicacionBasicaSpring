package com.apijccp.service.spring.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private List<CustomAuthority> authorities;
	private final Integer idUsuario;
	private final  String password;
	private final  String username;
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	private CustomUserDetails(List<CustomAuthority> authorities, Integer idUsuario, String password, String username,
			boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
		super();
		this.authorities = authorities;
		this.idUsuario = idUsuario;
		this.password = password;
		this.username = username;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}
	
	public static class CustomUserDetailsBuilder {
		private List<CustomAuthority> authorities;
		private Integer idUsuario;
		private String password;
		private String username;
		private boolean accountNonExpired;
		private boolean accountNonLocked;
		private boolean credentialsNonExpired;
		private boolean enabled;
		
		public CustomUserDetailsBuilder(Integer idUsuario, String password, String username) {
			super();
			this.idUsuario = idUsuario;
			this.password = password;
			this.username = username;
		}

		public CustomUserDetailsBuilder setAuthorities(List<CustomAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}
		public CustomUserDetailsBuilder setAccountNonExpired(boolean accountNonExpired) {
			this.accountNonExpired = accountNonExpired;
			return this;
		}
		public CustomUserDetailsBuilder setAccountNonLocked(boolean accountNonLocked) {
			this.accountNonLocked = accountNonLocked;
			return this;
		}
		public CustomUserDetailsBuilder setCredentialsNonExpired(boolean credentialsNonExpired) {
			this.credentialsNonExpired = credentialsNonExpired;
			return this;
		}
		public CustomUserDetailsBuilder setEnabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}
		
		public CustomUserDetails build() {
			//Aqui se harían las comprobaciones para ver que todos los atributos son correctos
			return new CustomUserDetails(authorities, idUsuario, password, username, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
		}
		
	}
	
	@Override
	public List<CustomAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	
	
	@Override
	public String toString() {
		return "CustomUserDetails [authorities=" + authorities + ", password=" + password + ", username=" + username
				+ ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + "]";
	}

}
