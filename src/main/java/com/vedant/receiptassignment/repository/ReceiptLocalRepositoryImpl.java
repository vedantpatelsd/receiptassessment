package com.vedant.receiptassignment.repository;

import com.vedant.receiptassignment.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ReceiptLocalRepositoryImpl implements ReceiptRepository {

    private Map<UUID, Receipt> cache = new HashMap<>();

    @Override
    public Receipt save(Receipt receipt) {
        UUID id = UUID.randomUUID();
        receipt.setId(id);
        cache.put(id, receipt);
        return receipt;
    }

    @Override
    public Receipt fetch(Receipt receipt) {
        return cache.get(receipt.getId());
    }

    @Override
    public Receipt fetchById(UUID uuid) {
        return cache.get(uuid);
    }
}
