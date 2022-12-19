package org.example.exception;

public class MultipleDiscountCardsException extends RuntimeException {

    public MultipleDiscountCardsException() {
    }

    public MultipleDiscountCardsException(String message) {
        super(message);
    }

    public MultipleDiscountCardsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleDiscountCardsException(Throwable cause) {
        super(cause);
    }

    public MultipleDiscountCardsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
