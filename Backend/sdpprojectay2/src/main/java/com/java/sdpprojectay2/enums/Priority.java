package com.java.sdpprojectay2.enums;

public enum Priority {
    LOW(1),
    MEDIUM(2),

    HIGH(3);

    private final int status;

    Priority(final int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
