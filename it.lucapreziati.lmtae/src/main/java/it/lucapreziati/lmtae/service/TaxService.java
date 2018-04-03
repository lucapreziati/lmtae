package it.lucapreziati.lmtae.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import it.lucapreziati.lmtae.model.InputData;
import it.lucapreziati.lmtae.model.OutputData;
import it.lucapreziati.lmtae.model.ResultData;

public class TaxService {

	public ResultData madeOperation(List<InputData> inputDataList) {
		List<OutputData> odList = prepareAmount(inputDataList);
		double totalPreTaxes = totalPreTaxes(odList);
		double totalTaxes = totalTaxes(odList);
		double total = total(totalPreTaxes, totalTaxes);
		ResultData rs = new ResultData(odList, totalTaxes, total);
		return rs;
	}

	public List<OutputData> prepareAmount(List<InputData> inputDataList) {
		List<OutputData> result = inputDataList.stream().map(x -> x.mapper()).collect(Collectors.toList());
		return result;
	}

	public double totalPreTaxes(List<OutputData> result) {
		double totalAmount = 0.0;
		for (OutputData od : result) {
			totalAmount += od.getTotalPrice();
		}
		return totalAmount;
	}

	public double totalTaxes(List<OutputData> result) {
		double totalTaxes = 0.0;
		for (OutputData od : result) {
			totalTaxes += od.getTotalTaxes();
		}
		return totalTaxes;
	}

	public double total(double totalAmount, double totalTaxes) {
		return totalAmount + totalTaxes;
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
