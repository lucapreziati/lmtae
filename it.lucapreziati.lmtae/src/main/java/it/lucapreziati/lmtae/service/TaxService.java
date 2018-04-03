package it.lucapreziati.lmtae.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import it.lucapreziati.lmtae.model.InputData;
import it.lucapreziati.lmtae.model.OutputData;
import it.lucapreziati.lmtae.model.ResultData;

public class TaxService {

	public ResultData madeOperation(List<InputData> inputDataList) {
		List<OutputData> odList = prepareAmount(inputDataList);
		BigDecimal totalPreTaxes = totalPreTaxes(odList);
		BigDecimal totalTaxes = totalTaxes(odList);
		BigDecimal total = total(totalPreTaxes, totalTaxes);
		ResultData rs = new ResultData(odList, totalTaxes, total);
		return rs;
	}

	public List<OutputData> prepareAmount(List<InputData> inputDataList) {
		List<OutputData> result = inputDataList.stream().map(x -> x.mapper()).collect(Collectors.toList());
		return result;
	}

	public BigDecimal totalPreTaxes(List<OutputData> result) {
		BigDecimal totalAmount = new BigDecimal(0);
		for (OutputData od : result) {
			totalAmount = totalAmount.add(od.getTotalPrice());
		}
		return totalAmount;
	}

	public BigDecimal totalTaxes(List<OutputData> result) {
		BigDecimal totalTaxes = new BigDecimal(0);
		for (OutputData od : result) {
			totalTaxes = totalTaxes.add(od.getTotalTaxes());
		}
		return totalTaxes;
	}

	public BigDecimal total(BigDecimal totalAmount, BigDecimal totalTaxes) {
		return totalAmount.add(totalTaxes);
	}

	public void printResult(ResultData rd) {
		if (rd == null)
			return;
		StringBuilder sb = new StringBuilder();
		for (OutputData od : rd.getResultList()) {
			sb.append(od.toPrint());
			sb.append("\n");
		}

		DecimalFormat df = new DecimalFormat("#,##.00");
		sb.append("Sales Taxes: ");
		sb.append(df.format(rd.getTotalTaxes())).append("\n");
		sb.append("Total: ").append(df.format(rd.getTotal())).append("\n");
		System.out.println(sb);
	}

}
