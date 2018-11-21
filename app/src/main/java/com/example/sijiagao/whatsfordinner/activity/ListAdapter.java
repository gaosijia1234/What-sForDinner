package com.example.sijiagao.whatsfordinner.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;
import java.util.TreeMap;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;


public class ListAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    private final ViewBinderHelper binderHelper;
    DatabaseHelper db;
    TreeMap<String, IngredientUnit> allGroceryItems;

    public ListAdapter(Context context, List<String> objects, DatabaseHelper db) {
        super(context, R.layout.row_list, objects);
        mInflater = LayoutInflater.from(context);
        binderHelper = new ViewBinderHelper();
        this.db = db;

        // uncomment if you want to open only one row at a time
        // binderHelper.setOpenOnlyOne(true);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_list, parent, false);

            holder = new ViewHolder();
            holder.swipeLayout = (SwipeRevealLayout) convertView.findViewById(R.id.swipe_layout);
            holder.frontView = convertView.findViewById(R.id.front_layout);
            holder.deleteView = convertView.findViewById(R.id.delete_layout);
            holder.moreView = convertView.findViewById(R.id.more_layout);
            holder.textView = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final String item = getItem(position);
        if (item != null) {
            binderHelper.bind(holder.swipeLayout, item);
            holder.textView.setText(item);

            String parts[] = item.trim().split("\\s+");
            final String quantity = parts[parts.length - 2];
            final String unitName = parts[parts.length - 1];
            StringBuilder temp = new StringBuilder();
            for(int i = 0; i <= parts.length - 3; i++){
                if(i == 0){
                    temp.append(parts[i]);
                }else{
                    temp.append(" ").append(parts[i]);
                }
            }
            final String ingreName = temp.toString();

            // change the function to delete 1
            holder.deleteView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allGroceryItems = db.getAllGroceryItems();
                    db.updateSingleGroceryItem(ingreName, unitName, "SUB",  1.0);
                    if(db.checkExistingGroceryItem(ingreName, unitName)){
                        String s = ingreName +  " " +
                                db.getAllGroceryItems().get(ingreName).getQuantity() + " " +
                                db.getAllGroceryItems().get(ingreName).getUnitName();
                        holder.textView.setText(s);
                    }else{
                        remove(item);
                    }
                }
            });

            // change the function to add 1
            holder.moreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    remove(item);
                    allGroceryItems = db.getAllGroceryItems();
                    db.updateSingleGroceryItem(ingreName, unitName, "ADD",  1.0);
                    String s = ingreName +  " " +
                            db.getAllGroceryItems().get(ingreName).getQuantity() + " " +
                            db.getAllGroceryItems().get(ingreName).getUnitName();
                    holder.textView.setText(s);
                }
            });

            holder.frontView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String displayText = "" + item + " clicked";
                    Toast.makeText(getContext(), displayText, Toast.LENGTH_SHORT).show();
                    Log.d("ListAdapter", displayText);
                }
            });
        }

        return convertView;
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onSaveInstanceState(Bundle)}
     */
    public void saveStates(Bundle outState) {
        binderHelper.saveStates(outState);
    }

    /**
     * Only if you need to restore open/close state when the orientation is changed.
     * Call this method in {@link android.app.Activity#onRestoreInstanceState(Bundle)}
     */
    public void restoreStates(Bundle inState) {
        binderHelper.restoreStates(inState);
    }

    private class ViewHolder {
        SwipeRevealLayout swipeLayout;
        View frontView;
        View deleteView;
        View moreView;
        TextView textView;
    }
}
