package com.example.t33.items;

/*
ToolType - enum for tool type
 */
public enum ToolType {
    CHAINSAW("Chainsaw"),
    LADDER("Ladder"),
    JACKHAMMER("Jackhammer"),
    NONE("None");

    private ToolType(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
