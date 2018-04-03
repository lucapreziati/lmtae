package it.lucapreziati.lmtae.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import it.lucapreziati.lmtae.Constant;

public class OutputData implements Serializable {

	private static final long serialVersionUID = -2941059038111267297L;

	private String description;
	private Integer number;
	private double price;
	private double taxes = Constant.perc0;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

	public double getTotalAmout() {
		return getTotalPrice() + getTotalTaxes();
	}

	public double getTotalPrice() {
		return this.getPrice() * (this.getNumber());
	}

	public double getTotalTaxes() {
		return this.getTaxes() * this.getNumber();
	}

	public String toPrint() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getNumber()).append(" ").append(this.getDescription()).append(": ");
		DecimalFormat df = new DecimalFormat("#,###.00");
		sb.append(df.format(getTotalAmout()));
		return sb.toString();
	}

}
