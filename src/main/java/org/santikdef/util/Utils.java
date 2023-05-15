package org.santikdef.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Utils {
    public static double round(double number,  int scale) {
        return BigDecimal
                .valueOf(number)
                .setScale(scale, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
