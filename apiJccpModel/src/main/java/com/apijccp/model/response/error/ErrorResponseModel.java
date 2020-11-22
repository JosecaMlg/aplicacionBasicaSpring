package com.apijccp.model.response.error;

import java.util.Optional;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ErrorResponseModel {
	
	public abstract String getCodError();
	public abstract Optional<String> getTextoDesc();
	public abstract Optional<String> getTituloDesc();
	
    public static Builder builder() {
        return new AutoValue_ErrorResponseModel.Builder();
      }
	
	 @AutoValue.Builder
	 public abstract static class Builder {
	    	public abstract Builder codError(String codError);
	    	public abstract Builder textoDesc(String textoDesc);
	    	public abstract Builder tituloDesc(String tituloDesc);
	        public abstract ErrorResponseModel build();  
	    }

}