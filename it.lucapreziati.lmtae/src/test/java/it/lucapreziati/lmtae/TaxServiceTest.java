package it.lucapreziati.lmtae;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import it.lucapreziati.lmtae.model.InputData;
import it.lucapreziati.lmtae.model.ResultData;
import it.lucapreziati.lmtae.service.TaxService;

public class TaxServiceTest {

	private TaxService ts = new TaxService();

	private List<InputData> input1 = new ArrayList<InputData>();
	private List<InputData> input2 = new ArrayList<InputData>();
	private List<InputData> input3 = new ArrayList<InputData>();

	@Before
	public void init() {
		input1.add(new InputData(1, false, true, "book", new BigDecimal(12.49)));
		input1.add(new InputData(1, false, false, "music CD", new BigDecimal(14.99)));
		input1.add(new InputData(1, false, true, "chocolate bar", new BigDecimal(0.85)));

		// 1 imported box of chocolates at 10.00
		// 1 imported bottle of perfume at 47.50

		input2.add(new InputData(1, true, true, "imported box fo chocolates", new BigDecimal(10.00)));
		input2.add(new InputData(1, true, false, "imported bottle of perfume", new BigDecimal(47.50)));

		// imported bottle of perfume at 27.99
		// 1 bottle of perfume at 18.99
		// 1 packet of headache pills at 9.75
		// 1 box of imported chocolates at 11.25
		// books, food, and medical products that are exempt.
		input3.add(new InputData(1, true, false, "imported bottle of perfume", new BigDecimal(27.99)));
		input3.add(new InputData(1, false, false, "bottle of perfume", new BigDecimal(18.99)));
		input3.add(new InputData(1, false, true, "packet of headache pills", new BigDecimal(9.75)));
		input3.add(new InputData(1, true, true, "box of imported chocolates ", new BigDecimal(11.25)));

	}

	@Test
	public void testMadeOperation1() {

		ResultData result = ts.madeOperation(input1);
		assertNotNull(result);
		ts.printResult(result);
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

		assertEquals("12.49", df.format(result.getResultList().get(0).getTotalAmout()));
		assertEquals("16.49", df.format(result.getResultList().get(1).getTotalAmout()));
		assertEquals("0.85", df.format(result.getResultList().get(2).getTotalAmout()));

		assertEquals("29.83", df.format(result.getTotal()));
		assertEquals("1.50", df.format(result.getTotalTaxes()));

	}

	@Test
	public void testMadeOperation2() {

		ResultData result = ts.madeOperation(input2);
		assertNotNull(result);
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

		assertEquals("10.50", df.format(result.getResultList().get(0).getTotalAmout()));
		assertEquals("54.65", df.format(result.getResultList().get(1).getTotalAmout()));

		assertEquals("65.15", df.format(result.getTotal()));
		assertEquals("7.65", df.format(result.getTotalTaxes()));

		ts.printResult(result);
	}

	@Test
	public void testMadeOperation3() {

		ResultData result = ts.madeOperation(input3);
		assertNotNull(result);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

		assertEquals("32.19", df.format(result.getResultList().get(0).getTotalAmout()));
		assertEquals("20.89", df.format(result.getResultList().get(1).getTotalAmout()));
		assertEquals("9.75", df.format(result.getResultList().get(2).getTotalAmout()));
		assertEquals("11.85", df.format(result.getResultList().get(3).getTotalAmout()));
		
		assertEquals("74.68", df.format(result.getTotal()));
		assertEquals("6.70", df.format(result.getTotalTaxes()));

		ts.printResult(result);
	}

}
