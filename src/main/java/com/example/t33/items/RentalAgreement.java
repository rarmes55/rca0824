package com.example.t33.items;

import jdk.jshell.spi.ExecutionControl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    public static final String DATE_FORMAT = "MM/dd/yy";
    private final Tool tool;

    private final LocalDate checkoutDate;

    // Rental day count - The number of days for which the customer wants to rent the tool. (e.g. 4 days)
    private final int numDays;

    // As a whole number, 0-100 (e.g. 20 = 20%)
    private final int discountPercent;

    private boolean processed = false;

    // The one and only constructor
    public RentalAgreement(final Tool tool,
                           final LocalDate checkoutDate,
                           final int numDays,
                           final int discountPercent) {
        this.tool = tool;
        this.checkoutDate  = checkoutDate;
        this.numDays = numDays;
        this.discountPercent = discountPercent;
    }

    public LocalDate getReturnDate() {
        return checkoutDate.plusDays(numDays);
    }

    public Tool getTool() {
        return tool;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    private String dateFormatter(final LocalDate ld) {
        return ld.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }


    public int getNumDays() {
        return numDays;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    /*
    checkout - validates the input, prints and processes the rental agreement
     */
    public boolean checkout() {
        if (!validateInput()) {
            return false;
        }
        printRentalAgreement();
        setProcessed(true);
        return true;
    }

    /*
    printRentalAgreement - prints to the console the details of the rental agreement
     */
    public void printRentalAgreement() {
        final StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator()).append("Rental Agreement:").append(System.lineSeparator());
        sb.append("Tool code: ").append(tool.name()).append(System.lineSeparator());
        sb.append("Tool type: ").append(tool.getType().getName()).append(System.lineSeparator());
        sb.append("Tool brand: ").append(tool.getBrand().getName()).append(System.lineSeparator());
        sb.append("Rental days: ").append(getNumDays()).append(System.lineSeparator());
        sb.append("Checkout date: ").append(dateFormatter(getCheckoutDate())).append(System.lineSeparator());
        sb.append("Due date: ").append(dateFormatter(getCheckoutDate().plusDays(getNumDays()))).append(System.lineSeparator());
        sb.append("Daily rental charge: ").append(currencyFormat(new BigDecimal(tool.getRate().getDailyCharge()))).append(System.lineSeparator());
        sb.append("Charge days: ").append(calculateChargeDays(tool)).append(System.lineSeparator());
        sb.append("Pre-discount charge: ").append(currencyFormat(getPreDiscountCharge())).append(System.lineSeparator());
        sb.append("Discount percent: ").append(percentageFormat(((float)getDiscountPercent() / (float)100))).append(System.lineSeparator());
        sb.append("Discount amount: ").append(currencyFormat(getDiscountAmount())).append(System.lineSeparator());
        sb.append("Final charge: ").append(currencyFormat(getFinalCharge())).append(System.lineSeparator());

        System.out.println(sb.toString());
    }

    /*
    getDiscountAmount - calculates and returns the discount amount
     */
    public BigDecimal getDiscountAmount() {
        final float charge = calculateChargeDays(tool) * Float.parseFloat(tool.getRate().getDailyCharge());
        return new BigDecimal(charge * discountPercent / 100).setScale(2, RoundingMode.HALF_DOWN);
    }

    /*
    getPreDiscountCharge - calculates the pre-discount charge
     */
    public BigDecimal getPreDiscountCharge() {
        // the below cannot fail because the daily charge is statically defined
        final float charge = calculateChargeDays(tool) * Float.parseFloat(tool.getRate().getDailyCharge());
        return new BigDecimal(charge).setScale(2, RoundingMode.HALF_UP);
    }

    /*
    currencyFormat - returns BigDecimal n with the currency format
     */
    public static String currencyFormat(final BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }

    /*
    percentageFormat - returns BigDecimal n with the percentage format
     */
    public static String percentageFormat(final float n) {
       return NumberFormat.getPercentInstance().format(n);
    }

    /*
    getFinalCharge - calculates and returns the final charge
     */
    public BigDecimal getFinalCharge() {
        float charge = calculateChargeDays(tool) * Float.parseFloat(tool.getRate().getDailyCharge());
        charge = charge - Float.parseFloat(getDiscountAmount().toString());
        return new BigDecimal(charge).setScale(2, RoundingMode.HALF_UP);
    }

    /*
    calculateChargeDays - calculates and returns the number of actual days for which to impose the charge
     */
    public int calculateChargeDays(final Tool tool) {
        int count = 0;

        LocalDate currentDate = getCheckoutDate();
        for (int i = getNumDays(); i > 0 ; i--) {
            currentDate = currentDate.plusDays(1);

            if (isWeekday(currentDate.getDayOfWeek()) && !tool.getRate().isWeekdayCharge()) {
                continue;
            } else if (isWeekEnd(currentDate.getDayOfWeek()) && !tool.getRate().isWeekendCharge()) {
                continue;
            }
            if (isHoliday(currentDate) && !tool.getRate().isHolidayCharge()) {
                continue;
            }
            count++;
        }

        return count;
    }

    /*
    isHoliday - calculates and returns whether the passed in date in a holiday
     */
    private boolean isHoliday(final LocalDate currentDate) {
        return  currentDate.equals(Holidays.getIndependenceDay(currentDate)) ||
                currentDate.equals(Holidays.getLaborDay(currentDate));
    }

    /*
    isWeekEnd - returns whether the dayOfWeek is a weekend day
     */
    private boolean isWeekEnd(final DayOfWeek dayOfWeek) {
        return  dayOfWeek == DayOfWeek.SATURDAY ||
                dayOfWeek == DayOfWeek.SUNDAY;
    }

    /*
    isWeekday - returns whether the dayOfWeek is a week day
     */
    private boolean isWeekday(final DayOfWeek dayOfWeek) {
        return  dayOfWeek == DayOfWeek.MONDAY ||
                dayOfWeek == DayOfWeek.TUESDAY ||
                dayOfWeek == DayOfWeek.WEDNESDAY ||
                dayOfWeek == DayOfWeek.THURSDAY ||
                dayOfWeek == DayOfWeek.FRIDAY;
    }

    /*
    validateInput - validates the proper range of numDays and discountPercent
     */
    public boolean validateInput() {
        boolean returnValue = true;
        final RentalAgreementValidator validator = new RentalAgreementValidator(this);

        try {
            validator.validateNumberDays();
        } catch (ExecutionControl.UserException userException) {
            System.out.println(userException.getMessage());
            returnValue = false;
        }

        try {
            validator.validateDiscountPercent();
        } catch (ExecutionControl.UserException userException) {
            System.out.println(userException.getMessage());
            returnValue = false;
        }
        return returnValue;
    }

}
