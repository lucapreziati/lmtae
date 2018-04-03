package it.lucapreziati.lmtae.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.text.NumberFormatter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import it.lucapreziati.lmtae.Constant;

public class InputData implements Serializable {

	private static final long serialVersionUID = -8345431880967367374L;

	private Integer number;
	private boolean imported;
	private boolean exempt;
	private String description;
	private double price;

	public InputData(Integer number, boolean imported, boolean exempt, String description, double price) {
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OutputData mapper() {
		OutputData od = new OutputData();
		od.setDescription(this.getDescription());
		od.setPrice(this.getPrice());
		od.setNumber(this.getNumber());

		double basePrice = Constant.perc0;
		if (this.isImported()) {
			if (this.isExempt()) {
				basePrice = this.getPrice() * Constant.perc5;
				// basePrice = basePrice.scaleByPowerOfTen(-2);
			} else {
				basePrice = this.getPrice() * Constant.perc15;
				// basePrice = basePrice.scaleByPowerOfTen(-2);
				// basePrice.add(this.getPrice().multiply(Constant.double_5)).scaleByPowerOfTen(-2);
				// basePrice = basePrice.scaleByPowerOfTen(-2);
			}
		} else {
			if (this.isExempt()) {
				basePrice = Constant.perc0;
			} else {
				basePrice = this.getPrice() * Constant.perc10;
				// basePrice = basePrice.scaleByPowerOfTen(-2);
			}
		}
		// Rounding
		// basePrice = Math.round(basePrice * 20.0) / 20.0;
		// basePrice = basePrice.multiply(new double(20)).round(new MathContext(2,
		// RoundingMode.UP))
		// .divide(new double(20));
		basePrice = rounding(basePrice);
		od.setTaxes(basePrice);
		return od;
	}

	private double rounding(double basePrice) {
		if (basePrice == 0d) {
			return basePrice;
		}
		System.out.println(basePrice);
		int value = (int) (basePrice * 1000);
		// NumberFormat nf = new NumberFormatter("###########0");
		// String bpValue = nf.format(basePrice);
		int mod = value % 100;
		if (mod == 0 || mod == 50) {
			System.out.println(basePrice + " " + value);
			return value / 1000D;
		} else {
			int diff = 0;
			if (mod < 50) {
				diff = 50 - mod;
			} else {
				diff = 100 - mod;
			}
			double res = (value + diff) / 1000D;
			System.out.println(basePrice + " " + res);
			return res;
		}
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}

}
