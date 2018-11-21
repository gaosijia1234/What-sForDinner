package com.example.sijiagao.whatsfordinner.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NewdishActivity extends AppCompatActivity {

    public static final String TAG = "NewdishActivity";
    public static final int PICK_IMAGE = 100;
    public Uri imageUri;
    private TextView  recipeNamePlainText ;
    private TextView  recipeDirectionText ;
    private ImageView recipeImageImageView;
    private ArrayList<TextView> tvList;
    private List<Ingredient> igList ;
    private Recipe myCurrentRecipe;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);
        setUp();

        //String urlImage = "https://upload.wikimedia.org/wikipedia/commons/b/b4/JPEG_example_JPG_RIP_100.jpg";
        //new GetImageFromUrl(recipeImageImageView).execute(urlImage);

    }

    public void setUp(){
        recipeNamePlainText  = findViewById(R.id.recipeNamePlainText);
        recipeDirectionText  = findViewById(R.id.recipeDirection);
        recipeImageImageView = findViewById(R.id.recipeImageImageView);
        tvList = new ArrayList<>();
        igList = new ArrayList<>();
        myCurrentRecipe = new Recipe();
        findViews(this, findViewById(R.id.linearLayout_newDish),tvList);

        DatabaseHelper db = DatabaseHelper.getInstance(this);
        String[] myIngredient = db.getExistingIngredientList().toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, myIngredient);
        for (int  i=0 ;i< tvList.size(); i+=3){
            tvList.get(i+1).setKeyListener((DigitsKeyListener.getInstance(true,true)));
            AutoCompleteTextView acTextView = (AutoCompleteTextView) tvList.get(i);
            acTextView.setThreshold(1);
            acTextView.setAdapter(adapter);
        }
    }

    public void imageGalleryBtnClick(View view) {
        Log.i(TAG,"imageGalleryBtnClick");
        openGallery();
    }

    public void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        Log.i(TAG,"imageGalleryBtnClick2");
        startActivityForResult(gallery, PICK_IMAGE);
        Log.i(TAG,"imageGalleryBtnClick5");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            Log.i(TAG,"imageGalleryBtnClick3");
            recipeImageImageView.setImageURI(imageUri);
        }
    }


    public void imageUrlBtnClick(View view) {
        Log.i(TAG,"imageUrlBtnClick");

    }
    // Buttons for add image to ImageView//
    public void clickAddImage(View view) {
        Log.i(TAG,"clickAddImage");
    }

    // Click Done Button to save data to DataBase //
    public void clickDone(View view) {
        Log.i(TAG,"clickDone");
        DatabaseHelper helper = DatabaseHelper.getInstance(this);

        myCurrentRecipe.setRecipeName(recipeNamePlainText.getText().toString());
        myCurrentRecipe.setCookingDirections(recipeDirectionText.getText().toString());

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

        myCurrentRecipe.setIngredients(igList);
        String recipeName = myCurrentRecipe.getRecipeName();

           if (!recipeName.matches("") && !helper.checkRecipeExistence(recipeName)) {
               Log.i(TAG,"true or fasle is :"+ Boolean.toString(helper.checkRecipeExistence(recipeName)));
               helper.addRecipe(myCurrentRecipe);
               Toast toast = Toast.makeText(this, "Recipe Added", Toast.LENGTH_SHORT);
               toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
               toast.show();
             }
             else {
               String error = "";
               if(recipeName.matches("")) error = "Nothing Added";
               else if(helper.checkRecipeExistence(recipeName)) error = "Duplicated Recipe Name";
               Toast toast = Toast.makeText(this, error, Toast.LENGTH_SHORT);
               toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
               toast.show();
           }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    // Helper function to get all TextView in ViewGroup//
    public static void findViews(Context context, View v, ArrayList tvList){
        try { if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    findViews(context, child,tvList);
                }
            } else if (v instanceof TextView) {
                tvList.add(((TextView) v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   public class GetImageFromUrl extends AsyncTask<String,Void,Bitmap> {
      ImageView imgV;

       public GetImageFromUrl(ImageView imageView) {
           this.imgV = imageView;
       }

       @Override
       protected Bitmap doInBackground(String... url) {
           String urldisplay = url[0];
           bitmap = null;
           Log.i(TAG,"bitmap class");
           try {

               InputStream srt = new java.net.URL(urldisplay).openStream();
                 bitmap = BitmapFactory.decodeStream(srt);
           }
   catch( Exception e) {
               e.printStackTrace();
           }

           return bitmap;
       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           imgV.setImageBitmap(bitmap);
       }


   }

}
