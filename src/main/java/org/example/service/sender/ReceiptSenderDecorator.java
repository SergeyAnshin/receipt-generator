package org.example.service.sender;

import org.example.entity.Receipt;

public class ReceiptSenderDecorator implements ReceiptSender {
    private ReceiptSender wrapper;

    public ReceiptSenderDecorator(ReceiptSender receiptSender) {
        this.wrapper = receiptSender;
    }

    @Override
    public void sendReceipt(Receipt receipt) {
        wrapper.sendReceipt(receipt);
    }
}
