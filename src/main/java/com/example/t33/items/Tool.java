package com.example.t33.items;

public enum Tool {
    CHNS(ToolType.CHAINSAW, ToolBrand.STIHL, ToolRate.CHAINSAWRATE),
    LADW(ToolType.LADDER, ToolBrand.WERNER, ToolRate.LADDERRATE),
    JAKD(ToolType.JACKHAMMER, ToolBrand.DEWALT, ToolRate.JACKHAMMERATE),
    JAKR(ToolType.JACKHAMMER, ToolBrand.RIDGID, ToolRate.JACKHAMMERATE),

    NONE(ToolType.NONE, ToolBrand.NONE, ToolRate.JACKHAMMERATE);

    public static Tool fromCode(final String code) {
        Tool tool = NONE;
        for (Tool t : Tool.values()) {
            if (t.name().equals(code)) {
                tool = t;
                break;
            }
        }
        return tool;
    }

    private Tool(ToolType type, ToolBrand brand, ToolRate rate) {
        this.type = type;
        this.brand = brand;
        this.rate = rate;
    }
    private final ToolType type;
    private final ToolBrand brand;

    private final ToolRate rate;


    public ToolType getType() {
        return type;
    }

    public ToolBrand getBrand() {
        return brand;
    }

    public ToolRate getRate() {
        return rate;
    }
}
