package com.java.sdpprojectay2.enums;

public enum Status {

    WAITING(1),
    APPROVE(2),

    DENIED(3);

    private final int status;

    Status(final int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
