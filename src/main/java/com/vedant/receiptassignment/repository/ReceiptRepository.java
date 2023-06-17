package com.vedant.receiptassignment.repository;

import com.vedant.receiptassignment.model.Receipt;

import java.util.UUID;

public interface ReceiptRepository {
    Receipt save(Receipt receipt);
    Receipt fetch(Receipt receipt);
    Receipt fetchById(UUID uuid);
}
