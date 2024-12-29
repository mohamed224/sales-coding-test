package fr.maestria.service;


import fr.maestria.entities.Item;
import org.springframework.stereotype.Service;

@Service
public class TaxCalculatorService {

    private static final double BASIC_SALES_TAX = 0.10;
    private static final double IMPORT_DUTY = 0.05;

    public double calculateTax(Item item) {
        double tax = 0.0;
        if (!item.isExempt()) {
            tax += item.getPrice() * BASIC_SALES_TAX;
        }

        if (item.isImported()) {
            tax += item.getPrice() * IMPORT_DUTY;
        }
        return Math.ceil(tax * 20) / 20.0;
    }
}
