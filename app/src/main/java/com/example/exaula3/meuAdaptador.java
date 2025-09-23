package com.example.exaula3;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class meuAdaptador extends SimpleAdapter {
    public meuAdaptador(Context ctx, List<HashMap<String, Object>> lista, int umaLinha, String[] de, int[] para) {
        super(ctx, lista, umaLinha, de, para);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        TextView nomeTime = view.findViewById(R.id.timeNome);
        TextView pontosTime = view.findViewById(R.id.ptsTime);

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.parseColor("#FFF00F"));
            nomeTime.setTextColor(Color.BLACK);
            pontosTime.setTextColor(Color.BLACK);
        } else {
            view.setBackgroundColor(Color.parseColor("#0AA30A"));
            nomeTime.setTextColor(Color.WHITE);
            pontosTime.setTextColor(Color.WHITE);
        }

        return view;
    }

}
