package com.vedant.receiptassignment.service;

import com.vedant.receiptassignment.model.Receipt;

public interface ReceiptService {

    Receipt createReceipt(Receipt receipt);

    Receipt findReceipt(String id);
}
