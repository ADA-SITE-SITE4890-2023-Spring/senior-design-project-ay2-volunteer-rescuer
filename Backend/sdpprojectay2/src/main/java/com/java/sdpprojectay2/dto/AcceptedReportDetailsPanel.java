package com.java.sdpprojectay2.dto;

public class AcceptedReportDetailsPanel {

    private int checkAccept;
    private boolean checkResolved;
    private int checkPriority;
    private String reportNote;

    public String getReportNote() {
        return reportNote;
    }

    public void setReportNote(String reportNote) {
        this.reportNote = reportNote;
    }

    public int isCheckAccept() {
        return checkAccept;
    }

    public void setCheckAccept(int checkAccept) {
        this.checkAccept = checkAccept;
    }

    public boolean isCheckResolved() {
        return checkResolved;
    }

    public void setCheckResolved(boolean checkResolved) {
        this.checkResolved = checkResolved;
    }

    public int getCheckPriority() {
        return checkPriority;
    }

    public void setCheckPriority(int checkPriority) {
        this.checkPriority = checkPriority;
    }
}
