package com.apijccp.controller;

import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.apijccp.dao.UsuarioWeb.TipEstado;
import com.apijccp.exception.UsuarioException;
import com.apijccp.model.Usuario;
import com.apijccp.service.spring.security.CustomUserDetails;
import com.apijccp.service.usuarios.UsuariosService;
import com.apijccp.vo.RespuestaPrueba;
import com.apijccp.vo.alta.usuario.RegistroEntrada;
import com.apijccp.vo.alta.usuario.RegistroSalida;

@RestController
public class HolaController {

	private static final Logger LOGGER = LogManager.getLogger(HolaController.class);
	private static final Logger LOGGER_F = LogManager.getFormatterLogger(HolaController.class);
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@Autowired
	private UsuariosService usuarios;
	
	@Value("${custom.message.pattern}")
	String mensajePersonalizado;

	@Value("${javax.validation.constraints.Pattern.message}")
	String defaultMessage;

	@RequestMapping(path= "/greet",method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE) 
    public RespuestaPrueba greet(ModelMap model){
		LOGGER.error("Entrando en HolaController Err....");
		LOGGER.info("Entrando en HolaController....");
		LOGGER_F.fatal("Este esta formateado param1 %s, param2 %s, param3 %s", "1", "2", "3");
        String greet =" Hello !!! JOPUTA! How are You?";
        
        
        
        model.addAttribute("greet", greet);
        System.out.println(greet);
        RespuestaPrueba respuest =  new RespuestaPrueba();
        respuest.setTexto(greet);
        respuest.setTitulo("Titulo de mierda");
        
        usuarios.holaMundo();
        
        return respuest;
    }
	
	@RequestMapping(path= "/me",method=RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE) 
    public CustomUserDetails me(){
		
		LOGGER.info("Entrando en servicio para recuperar el usuario Logado");
		CustomUserDetails cud = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOGGER.info("Respuesta usuario logado :: "+cud);
		
        return cud;
    }
	
	
	@Secured("ROL_ADMINISTRATOR")
	@RequestMapping(path= "/registro/alta",
			method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE) 
    public RegistroSalida altaUsuario(@RequestBody @Valid RegistroEntrada entrada) throws UsuarioException {
		
		Usuario usuario = new Usuario.UsuarioBuilder(entrada.getCod_usuario(), entrada.getPassword())
									.withIdEstado(TipEstado.ALTA)
									.withIdUsuario(9)
									.withTipRolUsuario(entrada.getRol())
									.build();
		
		usuarios.registraUsuario(usuario);
		
        return null;
    }
	
	public Map<RequestMappingInfo, HandlerMethod> getHandlerMethods() {
		return requestMappingHandlerMapping.getHandlerMethods();
	}
}
