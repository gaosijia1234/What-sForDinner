package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DayMealActivity extends AppCompatActivity {
    private Spinner breakfast_sp;
    private Spinner lunch_sp;
    private Spinner dinner_sp;
    private int breakfast_position;
    private int lunch_position;
    private int dinner_position;
    private ArrayList<String> mealList;
    private String today;
    private ArrayAdapter<String> bfAdapter;
    private ArrayAdapter<String> luAdapter;
    private ArrayAdapter<String> diAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_meal);

        DatabaseHelper db = DatabaseHelper.getInstance(this);

        breakfast_sp = (Spinner) findViewById(R.id.breakfast_sp);
        lunch_sp     = (Spinner) findViewById(R.id.lunch_sp);
        dinner_sp    = (Spinner) findViewById(R.id.dinner_sp);
        breakfast_position = 0;
        lunch_position = 0;
        dinner_position = 0;
        today = getIntent().getStringExtra("day");
        mealList = new ArrayList<>(db.getAllMeal().keySet());

        TextView day = findViewById(R.id.daymealLabelTV);
        day.setText(today);

        setUp();
        listenerSetup();
    }

    public void setUp(){
        DatabaseHelper db = DatabaseHelper.getInstance(this);

        List<String> bfList = (ArrayList<String>)this.mealList.clone();
        bfList.add(0, db.getMealPlanByDay(today).get(0));
        bfAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bfList){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }else{
                    tv.setTextColor(Color.BLACK);
                }
                    return view;
            }
        };
        bfAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breakfast_sp.setAdapter(bfAdapter);

        List<String> luList = (ArrayList<String>)this.mealList.clone();
        luList.add(0, db.getMealPlanByDay(today).get(1));
        luAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, luList){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    tv.setTextColor(Color.GRAY);
                }else{
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        luAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lunch_sp.setAdapter(luAdapter);

        List<String> diList = (ArrayList<String>)this.mealList.clone();
        diList.add(0, db.getMealPlanByDay(today).get(2));
        diAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, diList){
            @Override
            public boolean isEnabled(int position){
                return position != 0;
            }
                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;
                    if(position == 0){
                        tv.setTextColor(Color.GRAY);
                    }else{
                        tv.setTextColor(Color.BLACK);
                    }
                    return view;
                }
            };
            diAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dinner_sp.setAdapter(diAdapter);
    }

    public void listenerSetup() {

        breakfast_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                    breakfast_position = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        lunch_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                    lunch_position = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        dinner_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();
                    dinner_position = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    public void breakfastDoneBtnClick(View view){
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        if(breakfast_position > 0){

            db.assignRecipeToPlanSlot(today,"Breakfast", mealList.get(breakfast_position - 1));
        }
        bfAdapter.clear();
        setUp();
    }

    public void lunchDoneBtnClick(View view){
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        if(lunch_position > 0){
            db.assignRecipeToPlanSlot(today,"Lunch", mealList.get(lunch_position - 1));
        }
        luAdapter.clear();
        setUp();
    }

    public void dinnerDoneBtnClick(View view){
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        if(dinner_position > 0){
            db.assignRecipeToPlanSlot(today,"Dinner", mealList.get(dinner_position - 1));
        }
        diAdapter.clear();
        setUp();
    }

    public void dayMealDoneBtnClick(View view){
        Intent i = new Intent(this, MealActivity.class);
        startActivity(i);
    }

}

