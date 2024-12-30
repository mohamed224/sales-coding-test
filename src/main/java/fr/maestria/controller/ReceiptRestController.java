package fr.maestria.controller;


import fr.maestria.domains.Item;
import fr.maestria.domains.Receipt;
import fr.maestria.service.ReceiptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptRestController {

    private final ReceiptService receiptService;

    public ReceiptRestController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }


    @GetMapping
    public Receipt getReceiptDetails(@RequestBody List<Item> items) {
        return receiptService.getReceiptDetails(items);
    }
}
