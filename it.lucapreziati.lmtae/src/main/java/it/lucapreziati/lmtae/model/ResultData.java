package it.lucapreziati.lmtae.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResultData {

	private List<OutputData> resultList = new ArrayList<OutputData>();

	private BigDecimal totalTaxes;
	private BigDecimal total;

	public ResultData(List<OutputData> resultList, BigDecimal totalTaxes, BigDecimal total) {
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

	public BigDecimal getTotalTaxes() {
		return totalTaxes;
	}

	public void setTotalTaxes(BigDecimal totalTaxes) {
		this.totalTaxes = totalTaxes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
