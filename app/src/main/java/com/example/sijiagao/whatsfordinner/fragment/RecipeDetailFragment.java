package com.example.sijiagao.whatsfordinner.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.activity.RecipeActivity;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment {

      public static final String TAG = "LifecycleEvents";
      public static final String MESSAGE_KEY="message_key";

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance(String message) {
        Bundle args = new Bundle();
        args.putString(RecipeDetailFragment.MESSAGE_KEY, message);
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG,"onAttach");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);


        Bundle arguments = getArguments();
        //MESSAGE_KEY = recipe name here. later for DataBase use
        if (arguments != null) {
            String message = arguments.getString(MESSAGE_KEY);

            DatabaseHelper db = DatabaseHelper.getInstance(this.getActivity());
            Recipe rp = db.getRecipeByName(message);
            TextView title = (TextView) view.findViewById(R.id.frd_Title);
            title.setText(rp.getRecipeName());

            TextView direction = (TextView) view.findViewById(R.id.frd_direction);
            direction.setText(rp.getCookingDirections());

            ArrayList<String> igList = new ArrayList<>();

            for (int i=0 ; i<rp.getIngredients().size() ; i++) {
                 String temp = rp.getIngredients().get(i).getIngredientName()+" "+
                         Double.toString(rp.getIngredients().get(i).getUnit().getQuantity()) +
                         " " + rp.getIngredients().get(i).getUnit().getUnitName();
                 igList.add(temp);
             }

           ListView lv;
           lv =(ListView) view.findViewById(R.id.iglistView);
           lv.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.ingredient_listview, igList));

        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestory");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG,"onDetach");
    }

}
