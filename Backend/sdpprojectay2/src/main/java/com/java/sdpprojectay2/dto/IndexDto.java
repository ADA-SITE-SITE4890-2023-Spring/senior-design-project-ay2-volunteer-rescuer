package com.java.sdpprojectay2.dto;

import com.java.sdpprojectay2.model.EmergencyDetails;
import com.java.sdpprojectay2.model.Users;

import java.util.List;

public class IndexDto {

    public long numberOfUsers;
    public long numberOfReports;
    public long numberOfAccepted;
    public long numberOfDenied;
    List<EmergencyDetails> emergencyDetails;

    public IndexDto() {
    }

    public long getNumberOfUsers() {
        return numberOfUsers;
    }

    public IndexDto setNumberOfUsers(long numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
        return this;
    }

    public long getNumberOfReports() {
        return numberOfReports;
    }

    public IndexDto setNumberOfReports(long numberOfReports) {
        this.numberOfReports = numberOfReports;
        return this;
    }

    public long getNumberOfAccepted() {
        return numberOfAccepted;
    }

    public IndexDto setNumberOfAccepted(long numberOfAccepted) {
        this.numberOfAccepted = numberOfAccepted;
        return this;
    }

    public long getNumberOfDenied() {
        return numberOfDenied;
    }

    public IndexDto setNumberOfDenied(long numberOfDenied) {
        this.numberOfDenied = numberOfDenied;
        return this;
    }

    public List<EmergencyDetails> getEmergencyDetails() {
        return emergencyDetails;
    }

    public IndexDto setEmergencyDetails(List<EmergencyDetails> emergencyDetails) {
        this.emergencyDetails = emergencyDetails;
        return this;
    }
}
