package com.apijccp.vo;

import java.util.Optional;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_AutoValueMoney extends AutoValueMoney {

  private final Optional<String> currency;

  private final long amount;

  private AutoValue_AutoValueMoney(
      Optional<String> currency,
      long amount) {
    this.currency = currency;
    this.amount = amount;
  }

  @Override
  public Optional<String> getCurrency() {
    return currency;
  }

  @Override
  public long getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return "AutoValueMoney{"
         + "currency=" + currency + ", "
         + "amount=" + amount
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof AutoValueMoney) {
      AutoValueMoney that = (AutoValueMoney) o;
      return this.currency.equals(that.getCurrency())
          && this.amount == that.getAmount();
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= currency.hashCode();
    h$ *= 1000003;
    h$ ^= (int) ((amount >>> 32) ^ amount);
    return h$;
  }

  static final class Builder extends AutoValueMoney.Builder {
    private Optional<String> currency = Optional.empty();
    private Long amount;
    Builder() {
    }
    @Override
    public AutoValueMoney.Builder currency(String currency) {
      this.currency = Optional.of(currency);
      return this;
    }
    @Override
    public AutoValueMoney.Builder amount(long amount) {
      this.amount = amount;
      return this;
    }
    @Override
    public AutoValueMoney build() {
      String missing = "";
      if (this.amount == null) {
        missing += " amount";
      }
      if (!missing.isEmpty()) {
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_AutoValueMoney(
          this.currency,
          this.amount);
    }
  }

}
