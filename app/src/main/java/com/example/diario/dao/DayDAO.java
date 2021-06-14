package com.example.diario.dao;

import com.example.diario.model.Day;

import java.util.ArrayList;
import java.util.List;

public class DayDAO {
    private final String TAG = "LOG_DAY_DAO";
    private final static List<Day> days = new ArrayList<>();
    private static int idCounter = 1;

    public void save(Day day) {
        day.setId(idCounter);
        days.add(day);
        increaseId();
    }

    private void increaseId() {
        idCounter++;
    }

    public void edit(Day day) {
        Day foundDay;
        foundDay = findDayById(day);
        if (foundDay != null) {
            int foundDayPosition = days.indexOf(foundDay);
            days.set(foundDayPosition, day);
        }
    }

    private Day findDayById(Day day) {
        for (Day a : days) {
            if (a.getId() == day.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Day> all() {
        return days;
    }

    public void remove(Day dayClickedRemove) {
        Day dayForRemoval = findDayById(dayClickedRemove);
        if (dayClickedRemove != null) {
            days.remove(dayForRemoval);
        }
    }
}
