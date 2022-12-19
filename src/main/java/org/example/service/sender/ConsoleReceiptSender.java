package org.example.service.sender;

import org.example.entity.Receipt;

public class ConsoleReceiptSender implements ReceiptSender {

    @Override
    public void sendReceipt(Receipt receipt) {
        System.out.println(receipt);
    }
}
