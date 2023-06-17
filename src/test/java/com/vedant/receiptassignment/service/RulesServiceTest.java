package com.vedant.receiptassignment.service;

import com.vedant.receiptassignment.model.Receipt;
import com.vedant.receiptassignment.service.impl.RulesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RulesServiceTest {
    
    @InjectMocks
    private RulesServiceImpl rulesService;

    @Test
    public void testCalculatePoints_EmptyReceipt_ReturnsZero() {
        
        Receipt emptyReceipt = new Receipt();

        
        Integer result = rulesService.calculatePoints(emptyReceipt);

        
        Assertions.assertEquals(0, result);
    }

    @Test
    public void testCalculatePoints_AllFieldsPopulated_ReturnsCorrectPoints() {
        
        Receipt receipt = new Receipt();
        receipt.setRetailer("ABC123");
        receipt.setTotal("100.00");
        receipt.setPurchaseDate("2023-06-17");
        receipt.setPurchaseTime("15:30");

        List<Receipt.Item> itemList = new ArrayList<>();
        itemList.add(new Receipt.Item("Item 1", "10.00"));
        itemList.add(new Receipt.Item("Item 2", "20.00"));
        receipt.setItems(itemList);

        
        Integer result = rulesService.calculatePoints(receipt);

        
        Assertions.assertEquals(108, result);
    }

    @Test
    public void testCalculatePoints_NullRetailer_ReturnsCorrectPoints() {
        
        Receipt receipt = new Receipt();
        receipt.setRetailer(null);
        receipt.setTotal("50.00");
        receipt.setPurchaseDate("2023-06-17");
        receipt.setPurchaseTime("15:30");

        List<Receipt.Item> itemList = new ArrayList<>();
        itemList.add(new Receipt.Item("Item 1", "10.00"));
        itemList.add(new Receipt.Item("Item 2", "20.00"));
        receipt.setItems(itemList);

        
        Integer result = rulesService.calculatePoints(receipt);

        
        Assertions.assertEquals(102, result);
    }

    @Test
    public void testCalculatePoints_NonNumericTotal_ReturnsZeroPoints() {
        
        Receipt receipt = new Receipt();
        receipt.setRetailer("XYZ789");
        receipt.setTotal("ABC");
        receipt.setPurchaseDate("2023-06-17");
        receipt.setPurchaseTime("15:30");

        List<Receipt.Item> itemList = new ArrayList<>();
        itemList.add(new Receipt.Item("Item 1", "10.00"));
        itemList.add(new Receipt.Item("Item 2", "20.00"));
        receipt.setItems(itemList);

        
        Integer result = rulesService.calculatePoints(receipt);

        
        Assertions.assertEquals(33, result);
    }

    @Test
    public void testCalculatePoints_EmptyItemList_ReturnsZeroPoints() {
        
        Receipt receipt = new Receipt();
        receipt.setRetailer("PQR456");
        receipt.setTotal("75.00");
        receipt.setPurchaseDate("2023-06-17");
        receipt.setPurchaseTime("15:30");

        List<Receipt.Item> emptyItemList = new ArrayList<>();
        receipt.setItems(emptyItemList);

        
        Integer result = rulesService.calculatePoints(receipt);

        
        Assertions.assertEquals(97, result);
    }

    @Test
    public void testCalculatePoints_NullPurchaseDate_ReturnsZeroPoints() {
        
        Receipt receipt = new Receipt();
        receipt.setRetailer("MNO321");
        receipt.setTotal("100.00");
        receipt.setPurchaseDate(null);
        receipt.setPurchaseTime("15:30");

        List<Receipt.Item> itemList = new ArrayList<>();
        itemList.add(new Receipt.Item("Item 1", "10.00"));
        itemList.add(new Receipt.Item("Item 2", "20.00"));
        receipt.setItems(itemList);

        
        Integer result = rulesService.calculatePoints(receipt);

        
        Assertions.assertEquals(102, result);
    }

    @Test
    public void testCalculatePoints_NullPurchaseTime_ReturnsZeroPoints() {
        
        Receipt receipt = new Receipt();
        receipt.setRetailer("JKL987");
        receipt.setTotal("125.00");
        receipt.setPurchaseDate("2023-06-17");
        receipt.setPurchaseTime(null);

        List<Receipt.Item> itemList = new ArrayList<>();
        itemList.add(new Receipt.Item("Item 1", "10.00"));
        itemList.add(new Receipt.Item("Item 2", "20.00"));
        receipt.setItems(itemList);

        
        Integer result = rulesService.calculatePoints(receipt);

        
        Assertions.assertEquals(98, result);
    }

}

