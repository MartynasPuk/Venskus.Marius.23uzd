package com.example.venskusmarius23uzd;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class ViewListContents extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    DatabaseHelper myDB;
    ArrayList<Notes> noteList;
    ListView listView;
    Notes note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewlistcontents_layout);

        myDB = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        noteList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if(numRows == 0) {
            Toast.makeText(ViewListContents.this, "Išsaugotų įrašų nėra", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while(data.moveToNext()) {
                note = new Notes(data.getString(0), data.getString(1), data.getString(2));
                noteList.add(i, note);
                i++;
            }
            TwoColumnListAdapter adapter = new TwoColumnListAdapter(this, R.layout.two_column_list_adapter_view, noteList);
            listView = findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_main:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
                Intent intent = new Intent(ViewListContents.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_add:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewnoteFragment()).commit();
                Intent intent1 = new Intent(ViewListContents.this, NewNote.class);
                startActivity(intent1);
                break;

            case R.id.nav_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment()).commit();
                //Intent intent1 = new Intent(NewNote.this, ViewListContents.class);
                //startActivity(intent1);
                break;

            case R.id.nav_delete:
                myDB.deleteAll();
                Toast.makeText(this, "Visi įrašai ištrinti", Toast.LENGTH_LONG).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
