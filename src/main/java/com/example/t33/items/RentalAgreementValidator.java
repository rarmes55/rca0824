package com.example.t33.items;

import jdk.jshell.spi.ExecutionControl;

public class RentalAgreementValidator {

    public static final String RENTAL_DAY_COUNT_SHOULD_BE_AN_INTEGER_1_OR_GREATER = "Rental day count should be an integer 1 or greater";
    public static final String DISCOUNT_PERCENT_SHOULD_BE_IN_THE_RANGE_0_100_INCLUSIVE = "Discount percent should be in the range 0-100 (inclusive).";
    private final RentalAgreement rentalAgreement;

    public RentalAgreementValidator(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public void validateNumberDays() throws ExecutionControl.UserException {
        if (rentalAgreement.getNumDays() <= 0) {
            throw new ExecutionControl.UserException(
                    RENTAL_DAY_COUNT_SHOULD_BE_AN_INTEGER_1_OR_GREATER,
                    null,
                    Thread.currentThread().getStackTrace());

        }
    }

    public void validateDiscountPercent() throws ExecutionControl.UserException {
        if (rentalAgreement.getDiscountPercent() < 0 || rentalAgreement.getDiscountPercent() > 100) {
            throw new ExecutionControl.UserException(
                    DISCOUNT_PERCENT_SHOULD_BE_IN_THE_RANGE_0_100_INCLUSIVE,
                    null,
                    Thread.currentThread().getStackTrace());
        }
    }
}
