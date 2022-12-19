package org.example.service.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PercentConverter {
    public static final BigDecimal ONE_HUNDRED_PERCENT = BigDecimal.valueOf(100.00);

    public BigDecimal convertPercentageToFraction(int percent) {
        return ONE_HUNDRED_PERCENT.subtract(BigDecimal.valueOf(percent)).divide(ONE_HUNDRED_PERCENT, RoundingMode.CEILING);
    }
}
