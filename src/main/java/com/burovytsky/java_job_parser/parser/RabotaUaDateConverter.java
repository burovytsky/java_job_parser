package com.burovytsky.java_job_parser.parser;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class RabotaUaDateConverter implements DateConverter {

    private final Map<String, Integer> months = new HashMap<>();

    public RabotaUaDateConverter() {
        initMonth();
    }

    private void initMonth() {
        months.put("янв.", 1);
        months.put("фев.", 2);
        months.put("мар.", 3);
        months.put("апр.", 4);
        months.put("май.", 5);
        months.put("июн.", 6);
        months.put("июл.", 7);
        months.put("авг.", 8);
        months.put("сен.", 9);
        months.put("окт.", 10);
        months.put("ноя.", 11);
        months.put("дек.", 12);
    }

    @Override
    public LocalDateTime formatDate(String date) {
        int year;
        int month;
        int day;
        int hour = 0;
        int minute = 0;
        String[] stringDate = date.split("-")[1].trim().split("\\.");
        String timePassed = date.split("-")[0].trim();
        LocalDateTime currentTime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        if (timePassed.contains("минут") || timePassed.contains("минуту")) {
            int minutesPassed = Integer.parseInt(timePassed.split(" ")[0]);
            if (minutesPassed > currentTime.getMinute()) {
                hour = currentTime.getHour() - 1;
                minute = 60 + (currentTime.getMinute() - minutesPassed);
            } else {
                hour = currentTime.getHour();
                minute = currentTime.getMinute() - minutesPassed;
            }
        } else if (timePassed.contains("час") || timePassed.contains("часа") || timePassed.contains("часов")) {
            int hoursPassed = Integer.parseInt(timePassed.split(" ")[0]);
            hour = currentTime.getHour() - hoursPassed;
        }
        year = Integer.parseInt(stringDate[2]);
        month = Integer.parseInt(stringDate[1]);
        day = Integer.parseInt(stringDate[0]);
        return LocalDateTime.of(year, month, day, 0, 0);
    }
}
