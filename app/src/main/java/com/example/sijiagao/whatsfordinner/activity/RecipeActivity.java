package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.fragment.RecipeDetailFragment;
import com.example.sijiagao.whatsfordinner.fragment.RecipeListFragment;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.List;

public class RecipeActivity extends AppCompatActivity implements RecipeListFragment.ListFragmentListener    {
     private RecipeDetailFragment fragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Log.i("uu","new Recipe_Activity");
       // DatabaseHelper db = DatabaseHelper.getInstance(this);
       // String[] sampleList = db.getAllRecipeNames().toArray(new String[0]);
       // RecipeDetailFragment fragment = RecipeDetailFragment.newInstance("Pass message from GroceryActivity");
       // Fragment rFragment = getSupportFragmentManager().findFragmentById(R.id.detailFragment);

        fragment2 = (RecipeDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detailFragment);

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
              && fragment2 == null  ){
            Log.i("uu","new fg2");
            RecipeDetailFragment fragment = new RecipeDetailFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.detailFragment,fragment)
                    .commit();
        }

        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                && fragment2 != null) {
            Log.i("uu","old fg2");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailFragment,fragment2)
                    .commit();

        }
    }


    @Override
    public void onListItemClick_LandMode(String rName) {
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        fragment2 = RecipeDetailFragment.newInstance(rName);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailFragment,fragment2)
                .commit();
    }


    @Override
    public void onListItemClick_PortraitMode(String rName) {
//        add recipe number to meal later
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        db.addRecipeToMeal(rName);


    }

    @Override
    public void onListItemLongClick_Mode(String name) {

        Intent newDishIntent = new Intent(getApplicationContext(), UpDateRecipeActivity.class);
        newDishIntent.putExtra("name",name);
        startActivity(newDishIntent);

    }


    @Override
    public void passDataBase(RecipeListFragment rf){
        Log.i("DATABASE","DataBase pass");
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        String[] sampleList = db.getAllRecipeNames().toArray(new String[0]);
        rf.setSampleList(sampleList);
    }

}
