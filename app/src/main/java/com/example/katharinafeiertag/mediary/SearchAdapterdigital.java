package com.example.katharinafeiertag.mediary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


class SearchViewHolderdigital extends RecyclerView.ViewHolder{

    public TextView medid,name,menge,art,nummer;

    public SearchViewHolderdigital(View itemView) {
        super(itemView);
        //medid = (TextView) itemView.findViewById(R.id.medid);
        name = (TextView) itemView.findViewById(R.id.name);
        menge = (TextView) itemView.findViewById(R.id.menge);
        art = (TextView) itemView.findViewById(R.id.art);
        //nummer = (TextView) itemView.findViewById(R.id.nummer);
    }
}

public class SearchAdapterdigital extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Drugs> drugs;

    public SearchAdapterdigital(Context context, List<Drugs> drugs) {
        this.context = context;
        this.drugs = drugs;
    }

    public SearchAdapterdigital() {

    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.medikamentensuche,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        //String pharmaId,name,menge,art,preis,code,bezeichnung;
        //holder.medid.setText(toString().valueOf(drugs.get(position).getMedID()));
        holder.name.setText(drugs.get(position).getName());
        holder.menge.setText(drugs.get(position).getMenge());
        holder.art.setText(drugs.get(position).getArt());
        //holder.nummer.setText(drugs.get(position).getNummer());
    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }
}
