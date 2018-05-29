package com.example.katharinafeiertag.mediary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by scheerbernhard on 05.05.18.
 */
class SearchViewHolder extends RecyclerView.ViewHolder{

    public TextView pharmaId,name, menge,art,preis,code,bezeichnung;

    public SearchViewHolder(View itemView) {
        super(itemView);
        pharmaId = (TextView) itemView.findViewById(R.id.pharmaId);
        name = (TextView) itemView.findViewById(R.id.name);
        menge = (TextView) itemView.findViewById(R.id.menge);
        art = (TextView) itemView.findViewById(R.id.art);
        preis = (TextView) itemView.findViewById(R.id.preis);
        code = (TextView) itemView.findViewById(R.id.code);
        bezeichnung = (TextView) itemView.findViewById(R.id.bezeichnung);

    }

}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Drugs> drugs;

    public SearchAdapter(Context context, List<Drugs> drugs) {
        this.context = context;
        this.drugs = drugs;
    }

    public SearchAdapter() {

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
        holder.pharmaId.setText(drugs.get(position).getPharmaId());
        holder.name.setText(drugs.get(position).getName());
        holder.menge.setText(drugs.get(position).getMenge());
        holder.art.setText(drugs.get(position).getArt());
        holder.preis.setText(drugs.get(position).getPreis());
        holder.code.setText(drugs.get(position).getCode());
        holder.bezeichnung.setText(drugs.get(position).getBezeichnung());


    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }
}
