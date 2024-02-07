package com.arthurandreu.tapioca;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ProductData implements Parcelable {
    private String productName;
    private String barcode;
    private List<String> ingredients;

    // Constructor that accepts the necessary arguments
    public ProductData(String productName, String barcode, List<String> ingredients) {
        this.productName = productName;
        this.barcode = barcode;
        this.ingredients = ingredients;
    }

    protected ProductData(Parcel in) {
        productName = in.readString();
        barcode = in.readString();
        ingredients = in.createStringArrayList();
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    // Getter methods...

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(barcode);
        dest.writeStringList(ingredients);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "productName='" + productName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
