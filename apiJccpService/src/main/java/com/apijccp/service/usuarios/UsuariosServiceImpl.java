package com.apijccp.service.usuarios;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apijccp.dao.RolUsuKey;
import com.apijccp.dao.UsuarioWeb;
import com.apijccp.dao.UsuarioWebFake;
import com.apijccp.dao.mysql.mapper.RolUsuMapper;
import com.apijccp.dao.mysql.mapper.UsuarioWebMapper;
import com.apijccp.model.Usuario;
import com.apijccp.model.enumerados.TipRolUsuario;

@Service
public class UsuariosServiceImpl implements UsuariosService {

	@Autowired
	UsuarioWebFake usuarioWeb;
	
	@Autowired
	UsuarioWebMapper mapper;
	
	@Autowired 
	RolUsuMapper rolUsuMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void holaMundo() {
		System.out.println("VEO LA LUZ!!!!!!!!!");	
		usuarioWeb.getUsuarioMofa();
//		usuariWebMapper.selectUsuario();
	}

	
	@Override
	@Transactional
	public UsuarioWeb registraUsuario(Usuario usuario) {
		UsuarioWeb uw =new UsuarioWeb();
		uw.setIdUsuario(mapper.selectNextID());
		uw.setCodUsuario(usuario.getCodUsuario());
		String passwordEncoded = passwordEncoder.encode(usuario.getPassword());
		uw.setPassword(passwordEncoded);
		uw.setTipEstado(usuario.getEstado());
		LocalDate localDate = LocalDate.now();
		//ponemos la caducidad de la password un mes despues
		localDate = localDate.plus(1, ChronoUnit.MONTHS);
		Long epoch = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().getEpochSecond()*1000;
		uw.setFecCaducidadPass(new Date(epoch));
		
		mapper.insert(uw);
		
		insertRoles(uw.getIdUsuario(), usuario.getRol());
		
		return uw;
	}
	
	private void insertRoles(Integer idUsuario, TipRolUsuario rol) {
		for (String rolItem :  rol.getRoles()) {
			RolUsuKey ruk = new RolUsuKey();
			ruk.setCodRol(rolItem);
			ruk.setIdUsuario(idUsuario);
			rolUsuMapper.insert(ruk);
		}
		
	}


	@Override
	public UsuarioWeb getUsuariobyUsername(String username) {
		return mapper.selectByUsername(username);
	}
}
