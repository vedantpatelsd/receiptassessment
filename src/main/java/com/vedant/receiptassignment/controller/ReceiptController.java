package com.vedant.receiptassignment.controller;

import com.vedant.receiptassignment.model.CreatedResponseDto;
import com.vedant.receiptassignment.model.Points;
import com.vedant.receiptassignment.model.Receipt;
import com.vedant.receiptassignment.service.ReceiptService;
import com.vedant.receiptassignment.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private RulesService rulesService;

    @GetMapping(path = "/sanity")
    public ResponseEntity<String> sanity() {
        return new ResponseEntity<>("Sanity", HttpStatus.OK);
    }

    @PostMapping(path = "/receipts/process")
    public ResponseEntity<CreatedResponseDto> createReceipt(@RequestBody Receipt receipt) {
        Receipt receipt1 = receiptService.createReceipt(receipt);
        return new ResponseEntity<>(CreatedResponseDto.builder().
                id(receipt1.getId().toString()).build(), HttpStatus.OK);
    }

    @GetMapping(path = "/receipts/{id}/points")
    public ResponseEntity<Points> calculatePoints(@PathVariable(required = false) String id) {
        Integer points = 0;
        Receipt receipt = receiptService.findReceipt(id);
        if (receipt != null) {
            points = rulesService.calculatePoints(receipt);
        }
        return new ResponseEntity<>(Points.builder().points(points).build(), HttpStatus.OK);
    }
}
