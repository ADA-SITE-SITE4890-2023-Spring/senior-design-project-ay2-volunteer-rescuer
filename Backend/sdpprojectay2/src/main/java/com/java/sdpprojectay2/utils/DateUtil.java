package com.java.sdpprojectay2.utils;

public class DateUtil {

    public String convertTime(String date){
        String hours=date.split("T")[1].split(":")[0];
        String minute=date.split("T")[1].split(":")[1];
        return hours+":"+minute;
    }


    public String convertDate(String date) {
        String day = date.split("T")[0].split("-")[2];
        String month = date.split("-")[1];
        String year = date.split("-")[0];
        switch (month) {
            case "01":
                month = "January";
                break;
            case "02":
                month = "February";
                break;
            case "03":
                month = "March";
                break;
            case "04":
                month = "April";
                break;
            case "05":
                month = "May";
                break;
            case "06":
                month = "June";
                break;
            case "07":
                month = "July";
                break;
            case "08":
                month = "August";
                break;
            case "09":
                month = "September";
                break;
            case "10":
                month = "October";
                break;
            case "11":
                month = "November";
                break;
            case "12":
                month = "December";
                break;
            default:
                System.out.println("Invalid input - Wrong month number.");
                break;
        }
        return day + " " + month + ", " + year;
    }
}
