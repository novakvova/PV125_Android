package com.example.store.network;

import android.view.PixelCopy;

import com.example.store.dto.category.CategoryCreateDTO;
import com.example.store.dto.category.CategoryItemDTO;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface CategoriesApi {
    @GET("/api/categories/list")
    public Call<List<CategoryItemDTO>> list();

    @Multipart
    @POST("/api/categories/create")
    public Call<CategoryItemDTO> create(@PartMap Map<String, RequestBody> params,
                                        @Part MultipartBody.Part image);
}
