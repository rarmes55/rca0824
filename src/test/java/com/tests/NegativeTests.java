package com.tests;

import com.example.t33.items.RentalAgreement;
import com.example.t33.items.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/*
NegativeTests - all negative unit tests
 */
public class NegativeTests {

    // Test 1
    @Test
    void discountPriceShouldBeWithinRangeTest1() {
        final RentalAgreement ra1 = new RentalAgreement(Tool.JAKR, LocalDate.of(2015, 9, 3), 5, 101);
        boolean validateInput = ra1.validateInput();
        Assertions.assertFalse(validateInput);
    }

    @Test
    void numberDaysShouldBeWithinRangeTest1() {
        final RentalAgreement ra1 = new RentalAgreement(Tool.JAKR, LocalDate.of(2015, 9, 3), 0, 100);
        boolean validateInput = ra1.validateInput();
        Assertions.assertFalse(validateInput);
    }

}
