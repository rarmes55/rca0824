package com.example.t33.items;

/*
ToolBrand - enum for tool brand
 */
public enum ToolBrand {
    STIHL("Stihl"),
    WERNER("Werner"),
    DEWALT("DeWalt"),
    RIDGID("Ridgid"),

    NONE("None");



    ToolBrand(String name) {
        this.name = name;
    }
    private final String name;

    public String getName() {
        return name;
    }
}
