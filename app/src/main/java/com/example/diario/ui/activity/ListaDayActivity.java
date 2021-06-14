package com.example.diario.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diario.R;
import com.example.diario.model.Day;
import com.example.diario.ui.ListaDaysView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaDayActivity extends AppCompatActivity {
    private final String TAG = "LOG_MAIN";
    private final ListaDaysView listDaysView = new ListaDaysView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_days);
        setTitle(getString(R.string.string_title_main));
        configFabPlus();
        configList();
    }

    private void configFabPlus() {
        FloatingActionButton btnPlus = findViewById(R.id.activity_list_days_fab_plus);
        btnPlus.setOnClickListener(v -> intentToFormInsertion());
    }

    private void intentToFormInsertion() {
        startActivity(new Intent(this,
                FormDayActivity.class));
    }

    private void configList() {
        ListView listaOfDays = findViewById(R.id.activity_list_days_list_days);
        listDaysView.configAdapter(listaOfDays);
        configListenerClickItem(listaOfDays);
        registerForContextMenu(listaOfDays);
    }


    private void configListenerClickItem(ListView listaOfDays) {
        listaOfDays.setOnItemClickListener((parent, view, position, id) -> {
            Day dayClickedEditing = (Day) parent.getItemAtPosition(position);
            Log.d(TAG, "onItemClick: " + dayClickedEditing.getDayMonth());
            intentToFormEdition(dayClickedEditing);
        });
    }

    private void intentToFormEdition(Day dayClicked) {
        Intent goToFormDayEdition = new Intent(ListaDayActivity.this,
                FormDayActivity.class);
        goToFormDayEdition.putExtra(getString(R.string.string_intent_day_to_form),
                dayClicked);
        startActivity(goToFormDayEdition);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_days_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_list_days_menu_item_remove) {
            listDaysView.showDialogExclusionDay(item);
        }

        return super.onContextItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        listDaysView.updateListDays();

    }


}
