package org.example.service.receipt;

import org.example.dto.ReceiptContent;

public abstract class ReceiptContentServiceFactory {

    public <T> ReceiptContent getReceiptContent(T source) {
        ReceiptContentService<T> receiptContentService = createReceiptContentService();
        return receiptContentService.getReceiptContent(source);
    }

    protected abstract <T> ReceiptContentService<T> createReceiptContentService();

}
