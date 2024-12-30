package fr.maestria.domains;

import java.util.List;

public class Receipt {

    private List<ReceiptItem> items;
    private double totalSalesTax;
    private double totalCost;


    public Receipt(List<ReceiptItem> items, double totalSalesTax, double totalCost) {
        this.items = items;
        this.totalSalesTax = totalSalesTax;
        this.totalCost = totalCost;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItem> items) {
        this.items = items;
    }

    public double getTotalSalesTax() {
        return totalSalesTax;
    }

    public void setTotalSalesTax(double totalSalesTax) {
        this.totalSalesTax = totalSalesTax;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
