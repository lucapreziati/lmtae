package it.lucapreziati.lmtae.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import it.lucapreziati.lmtae.Constant;

public class InputData implements Serializable {

	private static final long serialVersionUID = -8345431880967367374L;

	private Integer number;
	private boolean imported;
	private boolean exempt;
	private String description;
	private BigDecimal price;

	public InputData(Integer number, boolean imported, boolean exempt, String description, BigDecimal price) {
		super();

		this.number = number;
		this.imported = imported;
		this.exempt = exempt;
		this.description = description;
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public boolean isExempt() {
		return exempt;
	}

	public void setExempt(boolean exempt) {
		this.exempt = exempt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public OutputData mapper() {
		OutputData od = new OutputData();
		od.setDescription(this.getDescription());
		od.setPrice(this.getPrice());
		od.setNumber(this.getNumber());

		BigDecimal basePrice = Constant.BIGDECIMAL_0;
		if (this.isImported()) {
			if (this.isExempt()) {
				basePrice = this.getPrice().multiply(Constant.BIGDECIMAL_5);
				basePrice = basePrice.scaleByPowerOfTen(-2);
			} else {
				basePrice = this.getPrice().multiply(Constant.BIGDECIMAL_15);
				basePrice = basePrice.scaleByPowerOfTen(-2);
			}
		} else {
			if (this.isExempt()) {
				basePrice = Constant.BIGDECIMAL_0;
			} else {
				basePrice = this.getPrice().multiply(Constant.BIGDECIMAL_10);
				basePrice = basePrice.scaleByPowerOfTen(-2);
			}
		}
		// Rounding
		basePrice = basePrice.multiply(new BigDecimal(20)).round(new MathContext(3)).divide(new BigDecimal(20));

		od.setTaxes(basePrice);
		return od;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

}
