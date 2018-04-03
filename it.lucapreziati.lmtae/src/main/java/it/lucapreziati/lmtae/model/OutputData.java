package it.lucapreziati.lmtae.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import it.lucapreziati.lmtae.Constant;

public class OutputData implements Serializable {

	private static final long serialVersionUID = -2941059038111267297L;

	private String description;
	private Integer number;
	private BigDecimal price;
	private BigDecimal taxes = Constant.BIGDECIMAL_0;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	public BigDecimal getTotalAmout() {
		return getTotalPrice().add(getTotalTaxes());
	}

	public BigDecimal getTotalPrice() {
		return this.getPrice().multiply(new BigDecimal(this.getNumber()));
	}

	public BigDecimal getTotalTaxes() {
		return this.getTaxes().multiply(new BigDecimal(this.getNumber()));
	}

	public String toPrint() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getNumber()).append(" ").append(this.getDescription()).append(": ");
		DecimalFormat df = new DecimalFormat("#,###.00");
		sb.append(df.format(getTotalAmout()));
		return sb.toString();
	}

}
