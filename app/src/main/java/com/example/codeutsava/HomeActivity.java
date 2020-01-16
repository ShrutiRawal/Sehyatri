package com.example.codeutsava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupViewPager();
    }

    private void setupViewPager() {
        Sections_pager_adapter adapter = new Sections_pager_adapter(getSupportFragmentManager());
        adapter.addFragment(new IOT_Fragment());
        adapter.addFragment(new MapFragment());

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_iot);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_petrol_pump);

    }
}
