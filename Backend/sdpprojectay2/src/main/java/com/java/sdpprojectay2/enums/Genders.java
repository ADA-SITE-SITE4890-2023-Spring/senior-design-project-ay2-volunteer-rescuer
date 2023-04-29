package com.java.sdpprojectay2.enums;

public enum Genders {

    MALE(1),
    FEMALE(2);

    private final int gender;

    Genders(final int gender){
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }

}
