package org.example.service.receipt;

import org.example.dto.ReceiptContent;
import org.example.exception.EntityNotExistsException;

public class ReceiptContentServiceDecorator implements ReceiptContentService<String[]> {
    private final ReceiptContentService<String[]> wrappee;

    public ReceiptContentServiceDecorator(ReceiptContentService<String[]> wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public ReceiptContent getReceiptContent(String[] source) throws EntityNotExistsException {
        return wrappee.getReceiptContent(source);
    }

}
