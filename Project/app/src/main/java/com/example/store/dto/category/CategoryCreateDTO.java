package com.example.store.dto.category;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CategoryCreateDTO {
    private RequestBody name;
    private MultipartBody.Part image;
    private RequestBody description;

    public RequestBody getName() {
        return name;
    }

    public void setName(RequestBody name) {
        this.name = name;
    }

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }

    public RequestBody getDescription() {
        return description;
    }

    public void setDescription(RequestBody description) {
        this.description = description;
    }
}
