package com.example.diario.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diario.R;
import com.example.diario.dao.DayDAO;
import com.example.diario.model.Day;

public class FormDayActivity extends AppCompatActivity {
    private final String TAG = "LOG_FORM";
    private EditText edtDayMonth;
    private EditText edtDayWeek;
    private EditText edtAnnotation;
    private final DayDAO dao = new DayDAO();
    private Day day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_day);
        initializingFields();
        chargeDays();
    }

    private void chargeDays() {
        Intent dateIntent = getIntent();
        if (dateIntent.hasExtra(getString(R.string.string_intent_day_to_form))) {
            setTitle(getString(R.string.string_titulo_form_edit));

            day = (Day) dateIntent
                    .getSerializableExtra(getString(R.string.string_intent_day_to_form));
            fillFieldsDayEdit();
        } else {
            setTitle(getString(R.string.string_title_form_insertion));
            day = new Day();
        }
    }

    private void fillFieldsDayEdit() {
        edtDayMonth.setText(day.getDayMonth());
        edtDayWeek.setText(day.getDayWeek());
        edtAnnotation.setText(day.getAnnotation());
    }


    private void finalizeForm() {
        fillDay();
        if (day.temIdValido()) {
            dao.edit(day);
        } else {
            dao.save(day);
        }
        finish();
    }

    private void initializingFields() {
        edtDayMonth = findViewById(R.id.activity_formulario_aluno_nome);
        edtDayWeek = findViewById(R.id.activity_formulario_aluno_telefone);
        edtAnnotation = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void fillDay() {
        String dayMonth = edtDayMonth.getText().toString();
        String dayWeek = edtDayWeek.getText().toString();
        String annotation = edtAnnotation.getText().toString();
        day.setDayMonth(dayMonth);
        day.setDayWeek(dayWeek);
        day.setAnnotation(annotation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_day_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_form_day_menu_ok) {
            finalizeForm();
        }
        return super.onOptionsItemSelected(item);
    }
}
