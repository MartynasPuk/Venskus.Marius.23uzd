package com.example.venskusmarius23uzd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    DatabaseHelper myDB;
    TextView NoteCount, tarpinis, FirstCat, SecondCat, ThirdCat, FourthCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);
        NoteCount = findViewById(R.id.textView);
        tarpinis = findViewById(R.id.tarpinis);
        FirstCat = findViewById(R.id.FirstCat);
        SecondCat = findViewById(R.id.SecondCat);
        ThirdCat = findViewById(R.id.ThirdCat);
        FourthCat = findViewById(R.id.FourthCat);

        int count = myDB.CountNotes();
        int countFirstCat = myDB.CountNotesFirstCat();
        int countSecondCat = myDB.CountNotesSecondCat();
        int countThirdCat = myDB.CountNotesThirdCat();
        int countFourthCat = myDB.CountNotesFourthCat();
        NoteCount.setText("Viso įrašų " + count);
        tarpinis.setText("Iš kurių:");
        FirstCat.setText(countFirstCat + " yra 'Svarbi info' kategorijoje");
        SecondCat.setText(countSecondCat + " yra 'Susitikimai' kategorijoje");
        ThirdCat.setText(countThirdCat + " yra 'Pirkinių sąrašai' kategorijoje");
        FourthCat.setText(countFourthCat + " yra 'Kiti užrašai' kategorijoje");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_main);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_main:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
                break;

            case R.id.nav_add:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewnoteFragment()).commit();
                Intent intent = new Intent(MainActivity.this, NewNote.class);
                startActivity(intent);
                break;

            case R.id.nav_list:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment()).commit();
                Intent intent1 = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent1);
                break;

            case R.id.nav_delete:
                myDB.deleteAll();
                Toast.makeText(this, "Visi įrašai ištrinti", Toast.LENGTH_LONG).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
