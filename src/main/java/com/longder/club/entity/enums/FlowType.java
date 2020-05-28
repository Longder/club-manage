package com.longder.club.entity.enums;

/**
 * 流水类型
 */
public enum FlowType {
    INCOMING("INCOMING", "label-success", "收入"),
    EXPEND("EXPEND","label-danger","支出");


    private String name;

    private String label;

    private String displayName;

    FlowType(String name, String label, String displayName) {
        this.name = name;
        this.label = label;
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
