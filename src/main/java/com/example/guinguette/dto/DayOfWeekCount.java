package com.example.guinguette.dto;

public class DayOfWeekCount {

    private String dayOfWeek;
    private Long pichetCount;

    public DayOfWeekCount(String dayOfWeek, Long count) {
        this.dayOfWeek = dayOfWeek;
        this.pichetCount = count;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getPichetCount() {
        return pichetCount;
    }

    public void setPichetCount(Long count) {
        this.pichetCount = count;
    }

}
