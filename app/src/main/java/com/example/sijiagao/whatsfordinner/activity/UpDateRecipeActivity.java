package com.example.sijiagao.whatsfordinner.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class UpDateRecipeActivity extends AppCompatActivity {

    private TextView recipeNamePlainText ;
    private TextView recipeDirectionText ;
    private ArrayList<TextView> tvList;
    private List<Ingredient> igList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date_recipe);
        setUpExistContent();
    }
    
    public void setUpExistContent() {
        String rpName = getIntent().getStringExtra("name");
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        Recipe myCurrentRecipt = db.getRecipeByName(rpName);
        
        recipeNamePlainText = findViewById(R.id.update_rpName);
        recipeNamePlainText.setText(myCurrentRecipt.getRecipeName());
        recipeDirectionText = findViewById(R.id.update_direction);
        recipeDirectionText.setText(myCurrentRecipt.getCookingDirections());
        tvList = new ArrayList<>();
        igList = myCurrentRecipt.getIngredients();
        
        findViews(this, findViewById(R.id.update_iglist),tvList);
        String[] myIngredient = db.getExistingIngredientList().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, myIngredient);
        
        for (int i =0, j=0 ;i< igList.size() ; i++, j+=3){
            tvList.get(j).setText(igList.get(i).getIngredientName());
            tvList.get(j+1).setText(Double.toString(igList.get(i).getUnit().getQuantity()));
            tvList.get(j+2).setText(igList.get(i).getUnit().getUnitName());
            tvList.get(j+1).setKeyListener((DigitsKeyListener.getInstance(true,true)));
            AutoCompleteTextView acTextView = (AutoCompleteTextView) tvList.get(j);
            acTextView.setThreshold(1);
            acTextView.setAdapter(adapter);
        }
    }

    public void clickUpdate(View view) {
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        Recipe tempRp = db.getRecipeByName(recipeNamePlainText.getText().toString());
        tempRp.setCookingDirections(recipeDirectionText.getText().toString());
        igList.clear();
        for (int i=0; i<tvList.size();i+= 3) {
              String igName=tvList.get(i).getText().toString();
              String igUnitNum = tvList.get(i+1).getText().toString();
              String igUnitName =tvList.get(i+2).getText().toString();
            if (!igName.matches("") && 
                    !igUnitNum.matches("") && 
                    !igUnitName.matches("")){
                igList.add(new Ingredient(igName, 
                        new IngredientUnit(igUnitName, Double.parseDouble(igUnitNum))));
            }
        }
        tempRp.setIngredients(igList);

        db.updateRecipe(tempRp);
        Toast.makeText(getApplicationContext(),
                "Update " + tvList.get(0).getText().toString() + " successfully",
                Toast.LENGTH_SHORT).show();
       

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public static void findViews(Context context, View v, ArrayList tvList){
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    findViews(context, child,tvList);
                }
            } else if (v instanceof TextView) {
                //Log.i(TAG,  ((TextView) v).getText().toString());
                 tvList.add(((TextView) v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

