package com.example.store.category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.store.CategoriesActivity;
import com.example.store.R;
import com.example.store.dto.category.CategoryCreateDTO;
import com.example.store.dto.category.CategoryItemDTO;
import com.example.store.services.ApplicationNetwork;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryCreateActivity extends AppCompatActivity {

    TextInputLayout tlCategoryName;
    TextInputLayout tlCategoryImage;
    TextInputLayout tlCategoryDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_create);

        tlCategoryName = findViewById(R.id.tlCategoryName);
        tlCategoryImage = findViewById(R.id.tlCategoryImage);
        tlCategoryDescription = findViewById(R.id.tlCategoryDescription);
    }

    public void onClickCreateCategory(View view)
    {
        String name = tlCategoryName.getEditText().getText().toString().trim();
        String image = tlCategoryImage.getEditText().getText().toString().trim();
        String description = tlCategoryDescription.getEditText().getText().toString().trim();
//        Log.d("--salo--", "--name--" + name);
//        Log.d("--salo--", "--image--" + image);
//        Log.d("--salo--", "--description--" + description);
        CategoryCreateDTO dto = new CategoryCreateDTO();
        dto.setName(name);
        dto.setImage(image);
        dto.setDescription(description);
        ApplicationNetwork
                .getInstance()
                .getCategoriesApi()
                .create(dto)
                .enqueue(new Callback<CategoryItemDTO>() {
                    @Override
                    public void onResponse(Call<CategoryItemDTO> call, Response<CategoryItemDTO> response) {
                        if(response.isSuccessful()) {
                            Intent intent = new Intent(CategoryCreateActivity.this, CategoriesActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<CategoryItemDTO> call, Throwable t) {

                    }
                });
    }
}