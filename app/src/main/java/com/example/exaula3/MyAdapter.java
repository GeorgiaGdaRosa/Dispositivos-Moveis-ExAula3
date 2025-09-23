package com.example.exaula3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] nomes;
    private String[] cores;

    public MyAdapter(Context context, String[] nomes, String[] cores) {
        super(context, R.layout.spinner_item, nomes);
        this.context = context;
        this.nomes = nomes;
        this.cores = cores;
    }

    @Override
    public int getCount() {
        return nomes.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return criarItemColorido(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return criarItemColorido(position, convertView, parent);
    }

    private View criarItemColorido(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        TextView textView = view.findViewById(R.id.spinnerText);
        textView.setText(nomes[position]);

        try {
            textView.setBackgroundColor(Color.parseColor(cores[position]));
            textView.setTextColor(Color.WHITE); // ou ajuste conforme contraste
        } catch (IllegalArgumentException e) {
            textView.setBackgroundColor(Color.GRAY);
        }

        return view;
    }
}