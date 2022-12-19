package org.example.service.receipt;

import org.example.dto.ReceiptContent;
import org.example.exception.EntityNotExistsException;

public interface ReceiptContentService<S> {

    ReceiptContent getReceiptContent(S source) throws EntityNotExistsException;

}
