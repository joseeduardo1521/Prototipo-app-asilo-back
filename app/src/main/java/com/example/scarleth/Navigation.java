package com.example.scarleth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       FragmentManager fragmentManager = getSupportFragmentManager();
       fragmentManager.beginTransaction().replace(R.id.Contenedor,new Fragment02()).commit();
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
        getMenuInflater().inflate(R.menu.navigation, menu);
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
            regreso();
            Toast.makeText(this, "Ha salido con exito", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.



        int id = item.getItemId();
        FragmentManager frags = getSupportFragmentManager();
        if (id == R.id.nav_camera) {
            eit();
        } else if (id == R.id.nav_gallery) {
           hola();

        } else if (id == R.id.nav_slideshow) {
            tratami();
        } else if (id == R.id.nav_manage) {
            city();
            Toast.makeText(this, "Consulta de citas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            regreso();
            finish();
        } else if (id == R.id.nav_send) {
            ace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void regreso(){
        Intent avn = new Intent(this,MainActivity.class);
        startActivity(avn);
        finish();
    }
    public void hola(){
        Intent avent = new Intent(this,Principal.class);
        startActivity(avent);
    }
    public void tratami(){
        Intent tr   = new Intent(this,Tratos.class);
        startActivity(tr);
    }
    public void city(){
        Intent cm   = new Intent(this,Recordatorio.class);
        startActivity(cm);
    }
    public void eit(){
        Intent w = new Intent (this,Regis.class);
        startActivity(w);
    }
    public void ace(){
        Intent wwe = new Intent(this,Acerca.class);
        startActivity(wwe);
    }

    public void ms(){
        Intent mi = new Intent(this,Login.class);
        startActivity(mi);
    }

}
