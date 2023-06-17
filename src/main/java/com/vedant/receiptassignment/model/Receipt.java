package com.vedant.receiptassignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Receipt {

    private UUID id;
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private String total;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String shortDescription;
        private String price;
    }
}
