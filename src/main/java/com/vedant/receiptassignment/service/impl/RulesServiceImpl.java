package com.vedant.receiptassignment.service.impl;

import com.vedant.receiptassignment.model.Receipt;
import com.vedant.receiptassignment.service.RulesService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RulesServiceImpl implements RulesService {

    @Override
    public Integer calculatePoints(Receipt receipt) {
        Integer points = 0;
        points += alphaNumericPoint(receipt.getRetailer());
        points += totalAmountPoints(receipt.getTotal());
        points += itemCountPoints(receipt.getItems());
        points += itemDescriptionPoints(receipt.getItems());
        points += purchaseDatePoints(receipt.getPurchaseDate());
        points += puchaseTimePoints(receipt.getPurchaseTime());
        return points;
    }

    private Integer alphaNumericPoint(String name) {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }
        name = name.trim();
        int count = 0;
        for (Character character: name.toCharArray()) {
            if (Character.isAlphabetic(character) || Character.isDigit(character)) {
                count++;
            }
        }
        return count;
    }

    private Integer totalAmountPoints(String total) {
        Integer points = 0;
        Double amount = 0.0;
        try {
            amount = Double.parseDouble(total);
        } catch (Exception e) {
            return 0;
        }
        BigDecimal bigDecimal = new BigDecimal(total);

        if (bigDecimal.subtract(new BigDecimal(bigDecimal.intValue())).equals(new BigDecimal("0.00"))) {
            points += 50;
        }
        if (amount % 0.25 == 0) {
            points += 25;
        }
        return points;
    }

    private Integer itemCountPoints(List<Receipt.Item> itemList) {
        if (itemList == null) {
            return 0;
        }
        return (itemList.size() / 2) * 5;
    }

    private Integer itemDescriptionPoints(List<Receipt.Item> itemList) {
        Integer points = 0;
        itemList = itemList == null ? new ArrayList<>() : itemList;
        for (Receipt.Item item: itemList) {
            String description = item.getShortDescription();
            if (description == null || description.trim().length() == 0) {
                continue;
            }
            description = description.trim();
            if (description.length() % 3 == 0) {
                try {
                    points += (int)(Math.ceil(Double.parseDouble(item.getPrice()) * 0.2));
                } catch (Exception e) {
                    points += 0;
                }
            }
        }
        return points;
    }

    private Integer purchaseDatePoints(String date) {
        if (date == null) {
            return 0;
        }
        LocalDate localDate = LocalDate.parse(date);
        return localDate.getDayOfMonth() % 2 != 0 ? 6 : 0;
    }

    private Integer puchaseTimePoints(String time) {
        if (time == null) {
            return 0;
        }
        LocalTime localTime = LocalTime.parse(time);
        return localTime.isAfter(LocalTime.parse("14:00")) && localTime.isBefore(LocalTime.parse("16:00")) ? 10: 0;
    }
}
