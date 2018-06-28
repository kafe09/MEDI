package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;


//um Medikamente in unserer großen Datenbank zu suchen
//das Suchen geht verdammt langsam - eventuell Prof fragen was wir hier tun können
public class MedSucheActivity extends AppCompatActivity {
    public static final String TAG = DatabaseHelperMedikament.class.getSimpleName();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    TextView textView;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    DrugsDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"in onCreate von Medikament suchen");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medsuchen);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_bar);
        textView = (TextView) findViewById(R.id.versuch);

        //Datenbank
        database = new DrugsDatabase(this);
        Log.d(TAG,"Datenbank gefunden");

        //Searchbar
        materialSearchBar.setHint("Search");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                List<String> suggest = new ArrayList<>();
                for(String search:suggestList) {
                   String medikament = materialSearchBar.getText().toLowerCase();
                   textView.setText(medikament);



                }
                materialSearchBar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        //init Adapter default set all result
        adapter = new SearchAdapter(this,database.getDrug());
        recyclerView.setAdapter(adapter);

    }

    private void startSearch(String text) {

       // adapter = new SearchAdapter(this,database.getDrugsByName(text));
        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = database.getNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    public void onHinzuClick(View v) {
        Log.d("msg","Auf Hinzufügen Button geklickt");
        Intent intent = new Intent (getBaseContext(),MedikamentHinzufugenActivity.class);
        startActivity(intent);
    }
}
