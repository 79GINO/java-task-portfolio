package com.example.taskportfolio.entity;

public enum TaskStatus {
    TODO("未対応"),
    OVERDUE("期限切れ");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
}
