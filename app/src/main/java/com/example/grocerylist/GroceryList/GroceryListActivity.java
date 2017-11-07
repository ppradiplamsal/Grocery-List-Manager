package com.example.grocerylist.GroceryList;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.grocerylist.R;

import java.util.ArrayList;

public class GroceryListActivity extends AppCompatActivity implements GroceryListInterface.View{

    ListView groceryListView;  // connection to listview
    GroceryListPresenter presenter; // presenter object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set up the floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the add item activity
                presenter.startAddItemActivity();
            }
        });

        // get a connection to our ListView
        groceryListView = (ListView) findViewById(R.id.list_grocery);
        // get a presenter object to handle logic
        presenter = new GroceryListPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // When this screen shows, update the list
        // in case any new items have been added
        presenter.updateGroceryList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void showGroceryList(ArrayList<String> groceryList) {
        // Create a new adapter to put our grocery list information inside
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, groceryList);
        // Give the adapter to the ListView to display
        groceryListView.setAdapter(adapter);
    }
}
