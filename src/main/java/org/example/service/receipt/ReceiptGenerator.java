package org.example.service.receipt;

import org.example.entity.Receipt;
import org.example.dto.ReceiptContent;

public interface ReceiptGenerator {

    Receipt generateReceipt(ReceiptContent source);

}
