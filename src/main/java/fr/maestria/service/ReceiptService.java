package fr.maestria.service;


import fr.maestria.entities.Item;
import fr.maestria.entities.Receipt;
import fr.maestria.entities.ReceiptItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    private final TaxCalculatorService taxCalculatorService;

    public ReceiptService(TaxCalculatorService taxCalculatorService) {
        this.taxCalculatorService = taxCalculatorService;
    }

    public Receipt getReceiptDetails(List<Item> items) {
        double totalSalesTax = 0.0;
        double totalCost = 0.0;
        List<ReceiptItem> receiptItems = new ArrayList<>();

        for (var item : items) {
            double itemTax = taxCalculatorService.calculateTax(item);
            double itemTotal = (item.getPrice() + itemTax) * item.getQuantity();

            totalSalesTax += itemTax;
            totalCost += itemTotal;

            receiptItems.add(new ReceiptItem(item.getName(), item.getQuantity(), itemTotal));
        }
        return new Receipt(receiptItems, totalSalesTax, totalCost);
    }
}
