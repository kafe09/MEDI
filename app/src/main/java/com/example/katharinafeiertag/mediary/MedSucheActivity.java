package com.example.katharinafeiertag.mediary;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;


//um Medikamente in unserer großen Datenbank zu suchen

public class MedSucheActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;
    TextView textView;



    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    DatabaseOpenHelper database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medsuchen);

        Button button = (Button) findViewById(R.id.hinzufügen);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MedTableActivity.class);
                TextView suche = (TextView) findViewById(R.id.SuchMedikament);
                intent.putExtra("weitergabe",suche.getText().toString());
                startActivityForResult(intent,1);
                //wichtig wenn man Daten zurück geben will von der 2.Activity
            }
        });
        textView = (TextView) findViewById(R.id.SuchMedikament);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_bar);
        //textView = (TextView) findViewById(R.id.versuch);

        //Datenbank
        database = new DatabaseOpenHelper(this);

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
                   if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                       suggest.add(search);




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

       adapter = new SearchAdapter(this,database.getDrugsByName(text));
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
