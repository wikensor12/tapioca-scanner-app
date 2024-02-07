package com.arthurandreu.tapioca;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    @SerializedName("code")
    private String code;

    @SerializedName("product")
    private Product product;
    public class Product {
        @SerializedName("ingredients")
        private List<Ingredient> ingredients;

        @SerializedName("product_name")
        private String productName;

        // Constructors, getters, and setters

        public List<Ingredient> getIngredients() {
            return ingredients;
        }

        public String getProductName() {
            return productName;
        }
    }

    public String getCode() {
        return code;
    }

    public Product getProduct() {
        return product;
    }

    public class Ingredient {

        @SerializedName("text")
        private String text;
        @SerializedName("vegan")
        private String vegan;

        @SerializedName("vegetarian")
        private String vegetarian;

        public String getText() {
            return text;
        }

        public String getVegan() {
            return vegan;
        }

        public String getVegetarian() {
            return vegetarian;
        }
    }

}
