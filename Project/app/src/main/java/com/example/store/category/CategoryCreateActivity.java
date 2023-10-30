package com.example.store.category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.store.CategoriesActivity;
import com.example.store.R;
import com.example.store.application.HomeApplication;
import com.example.store.dto.category.CategoryCreateDTO;
import com.example.store.dto.category.CategoryItemDTO;
import com.example.store.services.ApplicationNetwork;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryCreateActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView ivSelectImage;
    TextInputLayout tlCategoryName;

    TextInputLayout tlCategoryDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_create);

        tlCategoryName = findViewById(R.id.tlCategoryName);
        tlCategoryDescription = findViewById(R.id.tlCategoryDescription);

        ivSelectImage = findViewById(R.id.ivSelectImage);
        String url = "https://cdn.pixabay.com/photo/2016/01/03/00/43/upload-1118929_1280.png";
        Glide
                .with(HomeApplication.getAppContext())
                .load(url)
                .apply(new RequestOptions().override(300))
                .into(ivSelectImage);
    }

    public void onClickCreateCategory(View view)
    {
        String name = tlCategoryName.getEditText().getText().toString().trim();

        String description = tlCategoryDescription.getEditText().getText().toString().trim();
//        Log.d("--salo--", "--name--" + name);
//        Log.d("--salo--", "--image--" + image);
//        Log.d("--salo--", "--description--" + description);
        CategoryCreateDTO dto = new CategoryCreateDTO();
        dto.setName(name);

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

    public void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the URI of the selected image
            Uri uri = data.getData();

            Glide
                    .with(HomeApplication.getAppContext())
                    .load(uri)
                    .apply(new RequestOptions().override(300))
                    .into(ivSelectImage);

            // If you want to get the file path from the URI, you can use the following code:
            //filePath = getPathFromURI(uri);
        }
    }

    // This method converts the image URI to the direct file system path of the image file
    private String getPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            cursor.close();
            return filePath;
        }
        return null;
    }
}