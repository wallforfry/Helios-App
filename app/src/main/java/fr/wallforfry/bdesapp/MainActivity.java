package fr.wallforfry.bdesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fr.wallforfry.bdesapp.Fragments.AgendaFragment;
import fr.wallforfry.bdesapp.Fragments.AnnalesFragment;
import fr.wallforfry.bdesapp.Fragments.JeuxFragment;
import fr.wallforfry.bdesapp.Fragments.MesClubsFragment;
import fr.wallforfry.bdesapp.Fragments.NewsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        startFragment();
    }

    private void loadHeader() {
        String identifiant_recu = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            identifiant_recu = extras.getString("identifiant");
        }
        ((TextView) findViewById(R.id.nav_identifiant)).setText(identifiant_recu);
    }

    private void startFragment() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String pref = sp.getString("choix_demarrage", "Les News");
        Fragment mFragment = null;
        String titleFragment = null;
        switch(pref){
            default:

                break;
            case "-2":
                // Handle the news action
                mFragment = NewsFragment.newInstance(0);
                titleFragment = NewsFragment.getTitle();
                break;
            case "-1":
                AgendaFragment.newInstance(0);
                titleFragment = AgendaFragment.getTitle();
                break;
            case "0":
                mFragment = JeuxFragment.newInstance(0);
                titleFragment = JeuxFragment.getTitle();
                break;
            case "1":
                mFragment = MesClubsFragment.newInstance(0);
                titleFragment = MesClubsFragment.getTitle();
                break;
            case "2":
                mFragment = AnnalesFragment.newInstance(0);
                titleFragment = AnnalesFragment.getTitle();
                break;
        }

        if(mFragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, mFragment)
                    .addToBackStack(null)
                    .commit();

            if(titleFragment != null){
                changeTitle(titleFragment);
            }
            else{
                changeTitle(getString(R.string.app_name));
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment mFragment = null;
        Intent i = null;
        String titleFragment = null;
        switch(id){
            default:

                break;
            case R.id.nav_news:
                // Handle the news action
                mFragment = NewsFragment.newInstance(0);
                titleFragment = NewsFragment.getTitle();
                break;
            case R.id.nav_agenda:
                mFragment = AgendaFragment.newInstance(0);
                titleFragment = AgendaFragment.getTitle();
                break;
            case R.id.nav_jeux:
                mFragment = JeuxFragment.newInstance(0);
                titleFragment = JeuxFragment.getTitle();
                break;
            case R.id.nav_mes_clubs:
                mFragment = MesClubsFragment.newInstance(0);
                titleFragment = MesClubsFragment.getTitle();
                break;
            case R.id.nav_annales:
                mFragment = AnnalesFragment.newInstance(0);
                titleFragment = AnnalesFragment.getTitle();
                break;
            case R.id.nav_settings:
                i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
                break;
            case R.id.nav_help:
                i = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(i);
                break;
            case R.id.nav_signout:
                break;
        }

        if(mFragment != null){
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, mFragment)
                    .addToBackStack(null)
                    .commit();

            if(titleFragment != null){
                changeTitle(titleFragment);
            }
            else{
                changeTitle(getString(R.string.app_name));
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeTitle(String title){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setTitle(title);

    }
}
