package com.apijccp.dao.spring.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {
		"com.apijccp.service.*",
		"com.apijccp.dao.impl"
		})
//Escaneo de mappers MYSQL mybatis
@MapperScan(
		basePackages="com.apijccp.dao.mysql.mapper",
		sqlSessionTemplateRef="sqlSession"
)
public class SpringConfigurationDao implements WebMvcConfigurer{
	
	@Bean 
	public String beanPruebaService(){
		return "miPrimerBean";
	}
	
	// --------- ACCESO A DATOS! --------- // 
	
	/***************************
	 *     MAPPERS LOCATION    *
	 ***************************/
	@Bean 
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		 SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		 factoryBean.setDataSource(dataSourceMysql());
		 factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/apijccp/dao/mysql/mapper/*Mapper.xml"));
		 return factoryBean.getObject();
	}
	
	/***************************
	 * CONFIG SESSION TEMPLATE *
	 ***************************/
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
	  return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	/***************************
	 *    CONFIG TRANSACTION   *
	 ***************************/
	@Bean
	public DataSourceTransactionManager transactionManager() throws NamingException {
	  return new DataSourceTransactionManager(dataSourceMysql());
	}
	
	/***************************
	 *    CONEXIONES A BDD     *
	 ***************************/
	private DataSource dataSourceMysql() throws NamingException {
		Context ctx =  new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MysqlDB");
		return ds;
	}
	
	// --------- FIN ACCESO A DATOS! --------- // 
}
