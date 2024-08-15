package com.example.t33.items;

/*
ToolRate - enum for tool rates and whether there are weekday, weekend or holiday charges
 */
public enum ToolRate {
    LADDERRATE(ToolType.LADDER, "1.99", true, true, false),
    CHAINSAWRATE(ToolType.CHAINSAW, "1.49", true, false,true),
    JACKHAMMERATE(ToolType.JACKHAMMER, "2.99", true, false, false),
    NONE(ToolType.NONE, "0.00", false, false, false);


    /*
    contructor
     */
    private ToolRate(ToolType toolType, String dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.toolType = toolType;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

    private final ToolType toolType;
    private final String dailyCharge;
    private final boolean weekdayCharge;
    private final boolean weekendCharge;
    private final boolean holidayCharge;

    public ToolType getToolType() {
        return toolType;
    }

    public String getDailyCharge() {
        return dailyCharge;
    }

    public boolean isWeekdayCharge() {
        return weekdayCharge;
    }

    public boolean isWeekendCharge() {
        return weekendCharge;
    }

    public boolean isHolidayCharge() {
        return holidayCharge;
    }
}
