package com.example.diario.ui.activity;

import android.widget.EditText;

import com.example.diario.model.Day;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormDayActivityTest {

    private Day day = new Day();
    private static final String ANNOTATION = "sad";

    @Test
    public void checkTypedAnnotationIsEqualsSavedAnnotation() {
        day.setAnnotation(ANNOTATION);
        assertEquals(ANNOTATION, day.getAnnotation());
    }
}