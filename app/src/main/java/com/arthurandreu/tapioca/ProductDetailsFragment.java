package com.arthurandreu.tapioca;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

// ProductDetailsFragment.java
public class ProductDetailsFragment extends Fragment {
    private ProductData productData;

    public static ProductDetailsFragment newInstance(ProductData productData) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("PRODUCT_DATA", productData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_details, container, false);

        // Get product data from fragment arguments
        if (getArguments() != null) {
            productData = getArguments().getParcelable("PRODUCT_DATA");
        }

        // Fill views with product data
        if (productData != null) {
            TextView productNameTextView = rootView.findViewById(R.id.productNameTextView);
            productNameTextView.setText(productData.getProductName());
            productNameTextView.setTextSize(20);
            productNameTextView.setTypeface(null, Typeface.BOLD);

            TextView ingredientsLabel = rootView.findViewById(R.id.ingredientsLabel);
            ingredientsLabel.setText("Ingredientes del Producto");
            ingredientsLabel.setTextSize(16);
            ingredientsLabel.setPadding(0, 16, 0, 8);

            TextView ingredientsTextView = rootView.findViewById(R.id.ingredientsTextView);
            List<String> ingredients = productData.getIngredients();
            if (ingredients != null && !ingredients.isEmpty()) {
                String ingredientsText = "• " + TextUtils.join("\n• ", ingredients);
                ingredientsTextView.setText(ingredientsText);
                ingredientsTextView.setTextSize(14);
                ingredientsTextView.setPadding(0, 8, 0, 8);
            }
        }

        return rootView;
    }
}
