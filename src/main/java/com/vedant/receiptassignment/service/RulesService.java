package com.vedant.receiptassignment.service;

import com.vedant.receiptassignment.model.Receipt;

public interface RulesService {

    Integer calculatePoints(Receipt receipt);
}
