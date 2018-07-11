package com.example.katharinafeiertag.mediary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by katharinafeiertag on 11.07.18.
 */

public class SQLiteListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> medname;
    ArrayList<String> medmenge;
    ArrayList<String> medart;


    public SQLiteListAdapter(
            Context context2,
            ArrayList<String> Name,
            ArrayList<String> Menge,
            ArrayList<String> Art) {

        Log.d("im ListAdapter", " Listadapter aufgerufen");

        this.context = context2;
        this.medname = Name;
        this.medmenge = Menge;
        this.medart = Art;
    }

    @Override
    public int getCount() {
        return 0;
    }


    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();
            holder.textviewname = (TextView) child.findViewById(R.id.textViewNAME);
            holder.textviewmenge = (TextView) child.findViewById(R.id.textViewMenge);
            holder.textviewart = (TextView) child.findViewById(R.id.textViewArt);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewname.setText(medname.get(position));
        holder.textviewmenge.setText(medmenge.get(position));
        holder.textviewart.setText(medart.get(position));

        return child;
    }

public class Holder {
    TextView textviewname;
    TextView textviewmenge;
    TextView textviewart;
}

}