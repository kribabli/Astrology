package com.example.astrology;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.astrology.BottomFragments.CallFragment;
import com.example.astrology.BottomFragments.ChatFragment;
import com.example.astrology.BottomFragments.HistoryFragment;
import com.example.astrology.BottomFragments.HomeFragment;
import com.example.astrology.BottomFragments.VideoFragment;
import com.example.astrology.Common.HelperData;
import com.example.astrology.LoginModules.SignUpActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    Toolbar toolbar_main;
    boolean doubleBackToExitPressedOnce = false;
    HelperData helperData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMethod();
        setAction();

        setSupportActionBar(toolbar_main);

        new Thread(this::mBottomNavigationBar).start();
        fragmentManager = getSupportFragmentManager();

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar_main, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        toolbar_main.setNavigationIcon(R.drawable.ic_baseline_person_24);

        NavigationView navigationView = findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            switch (item.getItemId()) {
                case R.id.settings:
                    userLogout();
                    return true;
                default:
                    return true;
            }
        });
        HomeFragment homeFragment = new HomeFragment();
        loadFragment(homeFragment, "Home", fragmentManager);
    }

    private void initMethod() {
        toolbar_main = findViewById(R.id.toolbar_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.NavigationView);
        helperData = new HelperData(getApplicationContext());
    }

    private void setAction() {
        setSupportActionBar(toolbar_main);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar_main, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        toolbar_main.setNavigationIcon(R.drawable.ic_baseline_home_24);
    }

    private void userLogout() {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        helperData.Logout();
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show();
    }

    public void loadFragment(Fragment f1, String name, FragmentManager fm) {
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, f1, name);
        ft.commit();
        setToolbarTitle(name);
    }

    public void setToolbarTitle(String Title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Title);
        }
    }

    private void mBottomNavigationBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            boolean bool = false;
            if (bottomNavigationView.getSelectedItemId() != item.getItemId()) {
                switch (item.getItemId()) {
                    case R.id.home:
                        bool = true;
                        HomeFragment homeFragment = new HomeFragment();
                        loadFragment(homeFragment, "Home", fragmentManager);
                        break;
                    case R.id.chat:
                        ChatFragment chatFragment = new ChatFragment();
                        loadFragment(chatFragment, "Chat with Astrologer", fragmentManager);
                        bool = true;
                        break;
                    case R.id.video:
                        VideoFragment videoFragment = new VideoFragment();
                        loadFragment(videoFragment, "Video", fragmentManager);
                        bool = true;
                        break;
                    case R.id.call:
                        CallFragment callFragment = new CallFragment();
                        loadFragment(callFragment, "Call", fragmentManager);
                        bool = true;
                        break;
                    case R.id.history:
                        HistoryFragment historyFragment = new HistoryFragment();
                        loadFragment(historyFragment, "History", fragmentManager);
                        bool = true;
                        break;
                }
            }
            return bool;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (fragmentManager.getBackStackEntryCount() != 0) {
            String tag = fragmentManager.getFragments().get(fragmentManager.getBackStackEntryCount() - 1).getTag();
            setToolbarTitle(tag);
            super.onBackPressed();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.back_key), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 1000);
        }
    }
}