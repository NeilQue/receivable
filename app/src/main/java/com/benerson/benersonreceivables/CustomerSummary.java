package com.benerson.benersonreceivables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CustomerSummary extends AppCompatActivity {
    public static String currentName;
    private RecyclerView recyclerView;
    private CustomerSummaryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView nameTextView;
    private TextView amountTextView;
    private TextView balanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_summary);

        Intent intent = getIntent();
        currentName = intent.getStringExtra("name");

        recyclerView = findViewById(R.id.customer_summary_recycler_view);
        adapter = new CustomerSummaryAdapter();
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        nameTextView = findViewById(R.id.customer_header_text_view);
        amountTextView = findViewById(R.id.customer_total_amount_textview);
        balanceTextView = findViewById(R.id.customer_total_balance_textview);

        adapter.reload(currentName);
        load();
    }

    public void AddEntry(View view) {
        MainActivity.database.customerDao().addNewEntry(currentName);
        Intent intent = new Intent(view.getContext(), NewCustomer.class);

        view.getContext().startActivity(intent);
    }

    public void load() {
        nameTextView.setText(currentName);
        amountTextView.setText(String.valueOf(
                MainActivity.database.customerDao().totalCustomerAmount(currentName)));
        balanceTextView.setText(String.valueOf(
                MainActivity.database.customerDao().totalCustomerUnpaid(currentName)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        adapter.reload(name);
        load();
    }

    public void Back(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }
}