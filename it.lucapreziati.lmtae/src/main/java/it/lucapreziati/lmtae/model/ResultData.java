package it.lucapreziati.lmtae.model;

import java.util.ArrayList;
import java.util.List;

public class ResultData {

	private List<OutputData> resultList = new ArrayList<OutputData>();

	private double totalTaxes;
	private double total;

	public ResultData(List<OutputData> resultList, double totalTaxes, double total) {
		super();
		this.resultList = resultList;
		this.totalTaxes = totalTaxes;
		this.total = total;
	}

	public List<OutputData> getResultList() {
		return resultList;
	}

	public void setResultList(List<OutputData> resultList) {
		this.resultList = resultList;
	}

	public double getTotalTaxes() {
		return totalTaxes;
	}

	public void setTotalTaxes(double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
