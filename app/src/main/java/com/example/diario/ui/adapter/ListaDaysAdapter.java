package com.example.diario.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diario.R;
import com.example.diario.model.Day;

import java.util.ArrayList;
import java.util.List;

public class ListaDaysAdapter extends BaseAdapter {
    private final Context context;
    @SuppressWarnings("CanBeFinal")
    private final List<Day> days = new ArrayList<>();

    public ListaDaysAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int position) {
        return days.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cardDay = inflateCard(parent);
        linkCard(position, cardDay);
        return cardDay;

    }

    private void linkCard(int position, View cardAluno) {
        TextView dayMonth = cardAluno.findViewById(R.id.item_day_month);
        TextView dayWeek = cardAluno.findViewById(R.id.item_day_week);
        dayMonth.setText(days.get(position).getDayMonth());
        dayWeek.setText(days.get(position).getDayWeek());
    }

    private View inflateCard(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_day, parent, false);
    }

    public void remove(Day day) {
        days.remove(day);
        notifyDataSetChanged();
    }

    public void update(List<Day> days) {
        this.days.clear();
        this.days.addAll(days);
        notifyDataSetChanged();
    }
}

