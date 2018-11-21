package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ListDemoActivity extends AppCompatActivity {
    private ListAdapter adapter;
    TreeMap<String, IngredientUnit> allGroceryItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo);
//        setupActionBar();
        setupList();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Only if you need to restore open/close state when
        // the orientation is changed
        if (adapter != null) {
            adapter.saveStates(outState);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Only if you need to restore open/close state when
        // the orientation is changed
        if (adapter != null) {
            adapter.restoreStates(savedInstanceState);
        }
    }

    private void setupList() {
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        allGroceryItems = db.getAllGroceryItems();

        ListView listView = (ListView) findViewById(R.id.list_view);
        adapter = new ListAdapter(this, createList(allGroceryItems.size()), db);
        listView.setAdapter(adapter);
    }

    // mock data for listview
    private List<String> createList(int n) {
        List<String> list = new ArrayList<>();

        for (Map.Entry<String, IngredientUnit> map : allGroceryItems.entrySet()){
            list.add(map.getKey() + " " + map.getValue().getQuantity() + " " + map.getValue().getUnitName());
        }

        return list;
    }

    public void groceryDoneBtnOnClick(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
