package com.apijccp.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apijccp.dao.UsuarioWebFake;
import com.apijccp.dao.mysql.mapper.UsuarioWebMapper;

@Service
public class UsuarioWebImpl implements UsuarioWebFake{
	
	@Autowired
	UsuarioWebMapper mapper;

	public void getUsuarioMofa() {
		System.out.println("Todooooo OOOOOOOOOOOOK!");
	//	UsuarioWebMapper mapper = sesionFactory.getConfiguration().getMapper(UsuarioWebMapper.class, sesionFactory.openSession());
		
		System.out.println(mapper.selectByPrimaryKey(1));
	}

}
