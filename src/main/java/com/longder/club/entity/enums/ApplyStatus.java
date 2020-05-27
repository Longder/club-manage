package com.longder.club.entity.enums;

/**
 * 申请状态
 */
public enum ApplyStatus {
    APPLYING("APPLYING","label-warning","申请中"),
    CONSENT("CONSENT","label-success","已同意"),
    REJECT("REJECT","label-danger","拒绝");
    private String name;

    private String label;

    private String displayName;

    ApplyStatus(String name, String label, String displayName) {
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
