package com.example.calendar_view_2;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class Event {

    private String description;
    private String startTime;
    private String endTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // Default constructor (required for Jackson JSON deserialization)
    public Event() {
    }

    // Constructor without date (for backwards compatibility)
    public Event(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Constructor with date
    public Event(String description, String startTime, String endTime, LocalDate date) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return description + " (" + startTime + " - " + endTime + ")" +
                (date != null ? " on " + date : "");
    }
}