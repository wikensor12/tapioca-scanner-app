package com.arthurandreu.tapioca;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static final String BASE_URL = "https://world.openfoodfacts.org/api/v2/";

    private static Retrofit retrofit;

    public static OpenFoodFactsApi getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(OpenFoodFactsApi.class);
    }
}
