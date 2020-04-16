package com.example.venskusmarius23uzd;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class NewNote extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    DatabaseHelper myDB;
    EditText name, note;
    Spinner category;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);

        name = findViewById(R.id.editText);
        note = findViewById(R.id.editText2);
        category = findViewById(R.id.spinner);
        save = findViewById(R.id.button2);
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameEntry = name.getText().toString();
                String categoryEntry = category.getSelectedItem().toString();
                String noteEntry = note.getText().toString();

                if(nameEntry.length() != 0 && noteEntry.length() != 0) {
                    AddData(nameEntry, categoryEntry, noteEntry);
                    name.setText("");
                    note.setText("");
                } else {
                    Toast.makeText(NewNote.this, "Įveskite visus duomenis!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_main:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();
                Intent intent = new Intent(NewNote.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_add:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewnoteFragment()).commit();
                //Intent intent = new Intent(NewNote.this, NewNote.class);
                //startActivity(intent);
                break;

            case R.id.nav_list:
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListFragment()).commit();
                Intent intent1 = new Intent(NewNote.this, ViewListContents.class);
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

    public void AddData(String nameEntry, String categoryEntry, String noteEntry) {
        boolean insertData = myDB.addData(nameEntry, categoryEntry, noteEntry);

        if(insertData == true) {
            Toast.makeText(this, "Informacija išsaugoa", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Nepavyko išsaugoti informacijos", Toast.LENGTH_LONG).show();
        }
    }
}
