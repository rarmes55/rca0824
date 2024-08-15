package com.example.t33.items;

import java.time.DayOfWeek;
import java.time.LocalDate;

/*
Holidays - Singleton - returns the observed day for Fourth of July or Labor Day
 */
public enum Holidays {
    INSTANCE;

    /*
    getIndependenceDay - returns the observed day for Fourth of July
     */
    public static LocalDate getIndependenceDay(final LocalDate ld) {
        /*
            Independence Day, July 4th - If falls on weekend, it is observed on the closest weekday
            (if Sat, then Friday before, if Sunday, then Monday after)
        */
        LocalDate independenceDay = LocalDate.of(ld.getYear(), ld.getMonth(), ld.getDayOfMonth());
        independenceDay = independenceDay.withMonth(7);
        independenceDay = independenceDay.withDayOfMonth(4);
        final DayOfWeek dow = independenceDay.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY) {
            // set to Friday
            independenceDay = independenceDay.minusDays(1);
        } else if (dow == DayOfWeek.SUNDAY) {
            // set to Monday
            independenceDay = independenceDay.plusDays(1);
        }
        // else it is on a weekday - nothing to do
        return independenceDay;
    }

    /*
   getLaborDay - returns the observed day for Labor Day
    */
    public static LocalDate getLaborDay(final LocalDate ld) {
        /*
            Labor Day - First Monday in September
        */
        LocalDate laborDay = LocalDate.of(ld.getYear(), ld.getMonth(), ld.getDayOfMonth());
        laborDay = laborDay.withMonth(9);
        laborDay = laborDay.withDayOfMonth(1);
        final DayOfWeek dow = laborDay.getDayOfWeek();
        if (dow == DayOfWeek.MONDAY) {
            return laborDay;
        } else if (dow == DayOfWeek.SUNDAY) {
            return laborDay.plusDays(1);
        } else if (dow == DayOfWeek.TUESDAY) {
            return laborDay.plusDays(6);
        } else if (dow == DayOfWeek.WEDNESDAY) {
            return laborDay.plusDays(5);
        } else if (dow == DayOfWeek.THURSDAY) {
            return laborDay.plusDays(4);
        } else if (dow == DayOfWeek.FRIDAY) {
            return laborDay.plusDays(3);
        } else {
            // dow must be Saturday
            return laborDay.plusDays(2);
        }
    }
}
