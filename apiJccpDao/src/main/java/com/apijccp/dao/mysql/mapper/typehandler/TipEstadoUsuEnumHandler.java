package com.apijccp.dao.mysql.mapper.typehandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.apijccp.dao.UsuarioWeb.TipEstado;

public class TipEstadoUsuEnumHandler extends EnumTypeHandler<TipEstado> {

	public TipEstadoUsuEnumHandler(Class<TipEstado> type) {
		super(type);
	}

	
	  @Override
	  public void setNonNullParameter(PreparedStatement ps, int i, TipEstado parameter, JdbcType jdbcType) throws SQLException {
	    if (jdbcType == null) {
	      ps.setString(i, parameter.getEstado());
	    } else {
	      ps.setObject(i, parameter.getEstado(), jdbcType.TYPE_CODE); 
	    }
	  }

	  @Override
	  public TipEstado getNullableResult(ResultSet rs, String columnName) throws SQLException {
	    String s = rs.getString(columnName);
	    return s == null ? null : TipEstado.getTipEstado(s);
	  }

	  @Override
	  public TipEstado getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	    String s = rs.getString(columnIndex);
	    return s == null ? null : TipEstado.getTipEstado(s);
	  }
}
