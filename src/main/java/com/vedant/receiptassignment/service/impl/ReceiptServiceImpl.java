package com.vedant.receiptassignment.service.impl;

import com.vedant.receiptassignment.model.Points;
import com.vedant.receiptassignment.model.Receipt;
import com.vedant.receiptassignment.repository.ReceiptRepository;
import com.vedant.receiptassignment.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Override
    public Receipt createReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt findReceipt(String id) {
        return receiptRepository.fetchById(UUID.fromString(id));
    }
}
