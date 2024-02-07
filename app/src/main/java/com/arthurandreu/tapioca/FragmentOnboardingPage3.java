package com.arthurandreu.tapioca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentOnboardingPage3 extends Fragment {

    public FragmentOnboardingPage3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_onboarding_page3, container, false);

        Button redirectButton = view.findViewById(R.id.btnRedirectToMain);
        redirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToScannerActivity();
            }
        });

        return view;
    }

    private void redirectToScannerActivity() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("COMPLETED_ONBOARDING", true);
        editor.apply();

        Intent intent = new Intent(requireContext(), FoodScannerActivity.class);
        startActivity(intent);
        requireActivity().finish(); // Cierra la actividad actual de onboarding
    }
}
