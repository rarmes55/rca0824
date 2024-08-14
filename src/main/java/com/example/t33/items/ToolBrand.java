package com.example.t33.items;

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
