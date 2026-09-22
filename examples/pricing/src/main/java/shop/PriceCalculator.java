package shop;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Calculates the amount a customer pays at checkout.
 *
 * Business rules (from the product owner):
 * - Orders of EUR 100 or more get 5% off.
 * - Orders of EUR 250 or more get 10% off.
 * - Orders of EUR 500 or more get 15% off.
 * - Coupon "WELCOME10" gives an extra EUR 10 off, on top of the tier
 *   discount. Only valid for orders of EUR 50 or more.
 * - The total discount (tier + coupon) is never more than EUR 75.
 * - The amount to pay is rounded to cents (half up) and never negative.
 */
public class PriceCalculator {

    private static final BigDecimal TIER_1 = new BigDecimal("100");
    private static final BigDecimal TIER_2 = new BigDecimal("250");
    private static final BigDecimal TIER_3 = new BigDecimal("500");

    private static final String WELCOME_COUPON = "WELCOME10";
    private static final BigDecimal COUPON_AMOUNT = new BigDecimal("10");
    private static final BigDecimal COUPON_MINIMUM = new BigDecimal("50");

    private static final BigDecimal MAX_DISCOUNT = new BigDecimal("75");

    public BigDecimal calculateTotal(BigDecimal subtotal, String couponCode) {
        if (subtotal == null || subtotal.signum() < 0) {
            throw new IllegalArgumentException("Subtotal must be zero or positive");
        }

        BigDecimal discount = subtotal.multiply(tierPercentage(subtotal));
        discount = discount.min(MAX_DISCOUNT);

        if (isValidCoupon(couponCode, subtotal)) {
            discount = discount.add(COUPON_AMOUNT);
        }

        BigDecimal total = subtotal.subtract(discount).max(BigDecimal.ZERO);
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal tierPercentage(BigDecimal subtotal) {
        if (subtotal.compareTo(TIER_3) >= 0) {
            return new BigDecimal("0.15");
        }
        if (subtotal.compareTo(TIER_2) > 0) {
            return new BigDecimal("0.10");
        }
        if (subtotal.compareTo(TIER_1) >= 0) {
            return new BigDecimal("0.05");
        }
        return BigDecimal.ZERO;
    }

    private boolean isValidCoupon(String couponCode, BigDecimal subtotal) {
        return WELCOME_COUPON.equalsIgnoreCase(couponCode)
                && subtotal.compareTo(COUPON_MINIMUM) >= 0;
    }
}
