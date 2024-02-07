package com.arthurandreu.tapioca;

import android.os.Parcel;
import android.os.Parcelable;

public class NutritionalInfo implements Parcelable {
    private int calories;
    private int protein;
    private int fat;
    private int carbohydrates;

    public NutritionalInfo(int calories, int protein, int fat, int carbohydrates) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
    }

    protected NutritionalInfo(Parcel in) {
        calories = in.readInt();
        protein = in.readInt();
        fat = in.readInt();
        carbohydrates = in.readInt();
    }

    public static final Creator<NutritionalInfo> CREATOR = new Creator<NutritionalInfo>() {
        @Override
        public NutritionalInfo createFromParcel(Parcel in) {
            return new NutritionalInfo(in);
        }

        @Override
        public NutritionalInfo[] newArray(int size) {
            return new NutritionalInfo[size];
        }
    };

    // Getter methods
    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(calories);
        dest.writeInt(protein);
        dest.writeInt(fat);
        dest.writeInt(carbohydrates);
    }

}
