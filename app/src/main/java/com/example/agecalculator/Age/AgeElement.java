package com.example.agecalculator.Age;

public class AgeElement {
    private int year;
    private int month;
    private int day;
    private int yearTo;
    private int monthTo;
    private int dayTo;
    private String title = "Hello";
    private String fromDate;

    public AgeElement(int year, int month, int day, String title, String fromDate) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.title = title;
        this.fromDate = fromDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public int getMonthTo() {
        return monthTo;
    }

    public void setMonthTo(int monthTo) {
        this.monthTo = monthTo;
    }

    public int getDayTo() {
        return dayTo;
    }

    public void setDayTo(int dayTo) {
        this.dayTo = dayTo;
    }
}
