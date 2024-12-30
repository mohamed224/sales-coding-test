package fr.maestria;

import fr.maestria.domains.Item;
import fr.maestria.domains.Receipt;
import fr.maestria.service.ReceiptService;
import fr.maestria.service.TaxCalculatorService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SalesCodingTestApplicationTests {

	ReceiptService receiptService = new ReceiptService(new TaxCalculatorService());

	@Test
	void testGetReceiptDetailsOK1() {
		// GIVEN
		List<Item> items = Arrays.asList(
				new Item("book", 12.49, 1, false, true),
				new Item("music CD", 14.99, 1, false, false),
				new Item("chocolate bar", 0.85, 1, false, true)
		);

		// WHEN
		Receipt receiptItems = receiptService.getReceiptDetails(items);

		// THEN
		assertEquals(12.49, receiptItems.getItems().getFirst().getTotalPrice());
		assertEquals(16.49, receiptItems.getItems().get(1).getTotalPrice(), 0.01);
		assertEquals(0.85, receiptItems.getItems().getLast().getTotalPrice());
		assertEquals(1.50, receiptItems.getTotalSalesTax());
		assertEquals(29.83, receiptItems.getTotalCost(), 0.01);
	}

	@Test
	void testGetReceiptDetailsOK2() {
		// GIVEN
		List<Item> items = Arrays.asList(
				new Item("box of chocolates", 10.00, 1, true, true),
				new Item("bottle of perfume", 47.50, 1, true, false)
		);

		// WHEN
		Receipt receiptItems = receiptService.getReceiptDetails(items);

		// THEN
		assertEquals(10.50, receiptItems.getItems().getFirst().getTotalPrice());
		assertEquals(54.65, receiptItems.getItems().getLast().getTotalPrice());
		assertEquals(7.65, receiptItems.getTotalSalesTax());
		assertEquals(65.15, receiptItems.getTotalCost());
	}

	@Test
	void testGetReceiptDetailsOK3() {
		// GIVEN
		List<Item> items = Arrays.asList(
				new Item("bottle perfume", 27.99, 1, true, false),
				new Item("bottle of perfume", 18.99, 1, false, false),
				new Item("packet of headache pills", 9.75, 1, false, true),
				new Item("box of chocolates", 11.25, 1, true, true)
		);

		// WHEN
		Receipt receiptItems = receiptService.getReceiptDetails(items);

		// THEN
		assertEquals(32.19, receiptItems.getItems().getFirst().getTotalPrice());
		assertEquals(20.89, receiptItems.getItems().get(1).getTotalPrice(), 0.01);
		assertEquals(9.75, receiptItems.getItems().get(2).getTotalPrice());
		assertEquals(11.85, receiptItems.getItems().getLast().getTotalPrice(), 0.01);
		assertEquals(6.70, receiptItems.getTotalSalesTax(), 0.01);
		assertEquals(74.68, receiptItems.getTotalCost(), 0.01);
	}


}
