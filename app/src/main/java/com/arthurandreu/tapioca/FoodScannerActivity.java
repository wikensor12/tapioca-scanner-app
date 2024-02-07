package com.arthurandreu.tapioca;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodScannerActivity extends AppCompatActivity {
    private static String TAG = "FoodScannerActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_scanner);
        // Establecer la orientación a portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Configurar el botón de retroceso
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> hideProductDetailsFragment());

        // Inicia el escaneo automáticamente al abrir la actividad
        barcodeLauncher.launch(new ScanOptions());
    }

    // Register the launcher and result handler
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    String barcode = result.getContents();
                    loadProductDetailsFragment(barcode);
                    Log.d(TAG, barcode);
                }
            });

    private void loadProductDetailsFragment(String barcode) {
        // Aquí deberías realizar la petición a la API utilizando Retrofit u otra biblioteca
        // y obtener los datos del producto en el formato deseado
        // Log para verificar el código de barras
        Log.d("FoodScannerActivity", "loadProductDetailsFragment called with barcode: " + barcode + " from " + Thread.currentThread().getStackTrace()[2]);


        getProductDataFromApi(barcode, new ApiCallback() {
            @Override
            public void onSuccess(ProductData productData) {
                // Muestra el fragmento con los detalles del producto
                showProductDetailsFragment(productData);
                // Log para verificar el código de barras
                Log.d("FoodScannerActivity", "loadProductDetailsFragment called with barcode: " + barcode + " from " + Thread.currentThread().getStackTrace()[2]);
                Log.d(TAG, productData.toString());

            }

            @Override
            public void onFailure() {
                // Manejo de errores
                Toast.makeText(FoodScannerActivity.this, "Error al obtener datos del producto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProductDetailsFragment(ProductData productData) {
        // Muestra el fragmento con los detalles del producto
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ProductDetailsFragment.newInstance(productData))
                .addToBackStack(null)
                .commit();
    }

    private void hideProductDetailsFragment() {
        findViewById(R.id.fragment_container).setVisibility(View.GONE);
        // Vuelve a iniciar automáticamente el escáner al ocultar el fragmento de detalles
        barcodeLauncher.launch(new ScanOptions());
    }

    private void getProductDataFromApi(String barcode, ApiCallback callback) {
        // Lógica para realizar la solicitud a la API utilizando Retrofit
        RetrofitClientInstance.getApiService().getProductData(barcode).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    // Imprime la respuesta de la API
                    Log.d("FoodScannerActivity", "API Response: " + new Gson().toJson(response.body()));

                    // Aquí obtienes la respuesta y creas el objeto ProductData con los datos reales
                    ProductResponse productResponse = response.body();
                    if (productResponse != null) {
                        ProductResponse.Product product = productResponse.getProduct();
                        if (product != null) {
                            ProductData productData = new ProductData(
                                    product.getProductName(),
                                    barcode,
                                    extractIngredients(product.getIngredients())
                            );

                            // Devuelve los datos del producto a través del callback
                            callback.onSuccess(productData);
                        } else {
                            // Handle the case where product is null
                            callback.onFailure();
                        }
                    } else {
                        // Handle the case where productResponse is null
                        callback.onFailure();
                    }
                } else {
                    // Imprime el error de la API
                    Log.d("FoodScannerActivity", "API Error: " + response.errorBody().toString());

                    // Devuelve un callback de error
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                // Manejo de errores
                callback.onFailure();
            }
        });
    }

    // Helper method to extract ingredient texts from the list
    private List<String> extractIngredients(List<ProductResponse.Ingredient> ingredients) {
        List<String> ingredientTexts = new ArrayList<>();
        for (ProductResponse.Ingredient ingredient : ingredients) {
            ingredientTexts.add(ingredient.getText());
        }
        return ingredientTexts;
    }




    // Launch
    public void onButtonClick(View view) {
        barcodeLauncher.launch(new ScanOptions());
    }

    // Resto del código de la actividad...

    // Interfaz para manejar callbacks de la API
    private interface ApiCallback {
        void onSuccess(ProductData productData);

        void onFailure();
    }

    @Override
    public void onBackPressed() {
        // Verifica si el fragmento de detalles del producto está visible
        if (findViewById(R.id.fragment_container).getVisibility() == View.VISIBLE) {
            // Si está visible, oculta el fragmento de detalles del producto
            hideProductDetailsFragment();
        } else {
            // Si no está visible, reabre automáticamente el escáner al presionar el botón de retroceso
            barcodeLauncher.launch(new ScanOptions());
            super.onBackPressed();
        }
    }
}
