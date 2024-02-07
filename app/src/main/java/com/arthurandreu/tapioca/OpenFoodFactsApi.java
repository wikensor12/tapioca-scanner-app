package com.arthurandreu.tapioca;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OpenFoodFactsApi {

    @GET("product/{barcode}?fields=product_name,nutriscore_data,ingredients")
    Call<ProductResponse> getProductData(@Path("barcode") String barcode);

}
