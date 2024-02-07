package com.arthurandreu.tapioca;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.viewPager);
        setupViewPager();
    }

    private void setupViewPager() {
        OnboardingPagerAdapter adapter = new OnboardingPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOnboardingPage1());
        adapter.addFragment(new FragmentOnboardingPage2());
        adapter.addFragment(new FragmentOnboardingPage3());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Empty method
            }

            @Override
            public void onPageSelected(int position) {
                // No redirigir automáticamente al MainActivity al llegar al último fragmento
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Empty method
            }
        });
    }
}
