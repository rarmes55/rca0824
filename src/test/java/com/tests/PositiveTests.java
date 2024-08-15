package com.tests;

import com.example.t33.items.AcmeToolRentalStore;
import com.example.t33.items.RentalAgreement;
import com.example.t33.items.Tool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
PositiveTests - all positive unit tests
 */
public class PositiveTests {

    // Test 2
    @Test
    void handleFourthOfJulyTest1() {
        final RentalAgreement ra1 = new RentalAgreement(Tool.LADW, LocalDate.of(2020, 7, 2), 3, 10);
        Assertions.assertTrue(ra1.validateInput());
        Assertions.assertTrue(AcmeToolRentalStore.INSTANCE.processRentalAgreement(ra1));
        Assertions.assertEquals(2, ra1.calculateChargeDays(ra1.getTool()));
        Assertions.assertEquals(ra1.getPreDiscountCharge(), new BigDecimal("3.98"));
        Assertions.assertEquals(ra1.getDiscountAmount(), new BigDecimal("0.40"));
        Assertions.assertEquals(ra1.getFinalCharge(), new BigDecimal("3.58"));
    }

    // Test 3
    @Test
    void handleFourthOfJulyTest2() {
        final RentalAgreement ra1 = new RentalAgreement(Tool.CHNS, LocalDate.of(2015, 7, 2), 5, 25);
        Assertions.assertTrue(ra1.validateInput());
        Assertions.assertTrue(AcmeToolRentalStore.INSTANCE.processRentalAgreement(ra1));
        Assertions.assertEquals(3, ra1.calculateChargeDays(ra1.getTool()));
        Assertions.assertEquals(ra1.getPreDiscountCharge(), new BigDecimal("4.47"));
        Assertions.assertEquals(ra1.getDiscountAmount(), new BigDecimal("1.12"));
        Assertions.assertEquals(ra1.getFinalCharge(), new BigDecimal("3.35"));
    }

    // Test 4
    @Test
    void handleLaborDayTest1() {
        final RentalAgreement ra1 = new RentalAgreement(Tool.JAKD, LocalDate.of(2015, 9, 3), 6, 0);
        Assertions.assertTrue(ra1.validateInput());
        Assertions.assertTrue(AcmeToolRentalStore.INSTANCE.processRentalAgreement(ra1));
        Assertions.assertEquals(3, ra1.calculateChargeDays(ra1.getTool()));
        Assertions.assertEquals(ra1.getPreDiscountCharge(), new BigDecimal("8.97"));
        Assertions.assertEquals(ra1.getDiscountAmount(), new BigDecimal("0.00"));
        Assertions.assertEquals(ra1.getFinalCharge(), new BigDecimal("8.97"));
    }


    // Test 5
    @Test
    void handleFourthOfJulyTest3() {
        final RentalAgreement ra1 = new RentalAgreement(Tool.JAKR, LocalDate.of(2015, 7, 2), 9, 0);
        Assertions.assertTrue(ra1.validateInput());
        Assertions.assertTrue(AcmeToolRentalStore.INSTANCE.processRentalAgreement(ra1));
        Assertions.assertEquals(5, ra1.calculateChargeDays(ra1.getTool()));
        Assertions.assertEquals(ra1.getPreDiscountCharge(), new BigDecimal("14.95"));
        Assertions.assertEquals(ra1.getDiscountAmount(), new BigDecimal("0.00"));
        Assertions.assertEquals(ra1.getFinalCharge(), new BigDecimal("14.95"));
    }


    // Test 6
    @Test
    void handleFourthOfJulyTest4() {
        final RentalAgreement ra1 = new RentalAgreement(Tool.JAKR, LocalDate.of(2020, 7, 2), 4, 50);
        Assertions.assertTrue(ra1.validateInput());
        Assertions.assertTrue(AcmeToolRentalStore.INSTANCE.processRentalAgreement(ra1));
        Assertions.assertEquals(1, ra1.calculateChargeDays(ra1.getTool()));
        Assertions.assertEquals(ra1.getPreDiscountCharge(), new BigDecimal("2.99"));
        Assertions.assertEquals(ra1.getDiscountAmount(), new BigDecimal("1.50"));
        Assertions.assertEquals(ra1.getFinalCharge(), new BigDecimal("1.49"));
    }

}
