package org.example.service.sender;

import org.example.entity.Receipt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileReceiptSenderDecorator extends ReceiptSenderDecorator {
    private static final String FILE_NAME = "receipts.txt";

    public FileReceiptSenderDecorator(ReceiptSender receiptSender) {
        super(receiptSender);
    }

    @Override
    public void sendReceipt(Receipt receipt) {
        super.sendReceipt(receipt);
        write(receipt);
    }

    public void write(Receipt receipt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.append(receipt.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
