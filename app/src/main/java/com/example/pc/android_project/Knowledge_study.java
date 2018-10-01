package com.example.pc.android_project;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

public class Knowledge_study extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    private ActionBarDrawerToggle drawerToggle;
    boolean isDrawerOpend;
    Button know, quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_study);



        //DrawerLayout 참조
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawer=(DrawerLayout)findViewById(R.id.main_drawer);
        drawerToggle = setupDrawerToggle();
        drawer.addDrawerListener(drawerToggle);
        //NavigationView 참조
        NavigationView navigationView=(NavigationView)findViewById(R.id.main_drawer_view);
        setupDrawerContent(navigationView);


        navigationView.getMenu().performIdentifierAction(R.id.home, 0);




    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.home:
                fragmentClass = Knowledge_study_fragment_main.class;
                break;
            case R.id.keeptheright:
                fragmentClass = Knowledge_study_fragment1.class;
                break;
            case R.id.roseofsharon:
                fragmentClass = Knowledge_study_fragment2.class;
                break;
            case R.id.flag:
                fragmentClass = Knowledge_study_fragment3.class;
                break;
            case R.id.song:
                fragmentClass = Knowledge_study_fragment4.class;
                break;
            case R.id.korea:
                fragmentClass = Knowledge_study_fragment5.class;
                break;
            default:
                fragmentClass = Knowledge_study_fragment_main.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
    }



}

