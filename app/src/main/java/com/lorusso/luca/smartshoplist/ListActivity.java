package com.lorusso.luca.smartshoplist;

import android.content.pm.InstrumentationInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Product> prodList = new ArrayList<>();
    private DBproductHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //    DBproduct db = new DBproduct(this);
        if (db == null) {
            db = new DBproductHelper(this);
        }
        String name = db.getDatabaseName();
        int c = db.getDatabaseVersion();

        db.insertProduct("pasta");
        db.insertCategory("alimentari");


        RecyclerView recyclerViewProdotti = findViewById(R.id.recyclerProdotti);
        recyclerViewProdotti.setHasFixedSize(true);
        listOfProduct();

        mAdapter = new AdapterProduct(this, prodList);
        recyclerViewProdotti.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProdotti.setLayoutManager(mLayoutManager);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addProduct);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void listOfProduct() {
        Category c = new Category(1, "colazione");
        Product p = new Product(1, "biscotti", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);
        p = new Product(2, "cereali", "integrali", 3, c);
        prodList.add(p);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
}
