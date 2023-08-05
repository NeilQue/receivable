package com.benerson.benersonreceivables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static CustomerDatabase database;
    public static PaymentDatabase databaseP;
    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = Room
                .databaseBuilder(getApplicationContext(), CustomerDatabase.class, "customers")
                .allowMainThreadQueries()
                .build();

        databaseP = Room
                .databaseBuilder(getApplicationContext(), PaymentDatabase.class, "payments")
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.main_recycler_view);
        adapter = new MainAdapter();
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        adapter.reload();
    }

    public void ViewSummary(View view) {
        Intent intent = new Intent(view.getContext(), UnpaidSummary.class);
        view.getContext().startActivity(intent);
    }

    public void AddCustomer(View view) {
        database.customerDao().create();
        Context context = view.getContext();
        Intent intent = new Intent(context, NewCustomer.class);

        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.reload();
    }
}