package com.apijccp.vo;

import java.util.Optional;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AutoValueMoney  {
	
    public abstract Optional<String> getCurrency();
    public abstract long getAmount();
    
    public static Builder builder() {
        return new AutoValue_AutoValueMoney.Builder();
      }

    
    @AutoValue.Builder
    public abstract static class Builder {
    	public abstract Builder currency(String currency);
        public abstract Builder amount(long amount);
        public abstract AutoValueMoney build();  
    }
}