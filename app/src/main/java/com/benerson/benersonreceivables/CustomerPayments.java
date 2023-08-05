package com.benerson.benersonreceivables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CustomerPayments extends AppCompatActivity {
    public static String invoiceNumber;
    private String name;
    private RecyclerView recyclerView;
    private CustomerPaymentsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView nameTextView;
    private TextView amountTextView;
    private TextView balanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payments);

        Intent intent = getIntent();
        invoiceNumber = intent.getStringExtra("invoice");
        name = intent.getStringExtra("name");

        recyclerView = findViewById(R.id.customer_payments_recycler_view);
        adapter = new CustomerPaymentsAdapter();
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        nameTextView = findViewById(R.id.invoice_header_text_view);
        amountTextView = findViewById(R.id.invoice_total_amount_textview);
        balanceTextView = findViewById(R.id.invoice_total_balance_textview);

        adapter.reload(invoiceNumber);
        load();
    }

    public void AddPayment(View view) {
        MainActivity.databaseP.paymentDao().addNewPayment(invoiceNumber);
        Intent intent = new Intent(view.getContext(), NewPayment.class);
        intent.putExtra("invoiceNumber", invoiceNumber);
        intent.putExtra("name", name);

        view.getContext().startActivity(intent);
    }

    public void load() {
        nameTextView.setText(invoiceNumber);
        amountTextView.setText(String.valueOf(MainActivity.databaseP.paymentDao().totalAmount(invoiceNumber)));
        balanceTextView.setText(String.valueOf(
                MainActivity.database.customerDao().getBalance(invoiceNumber)));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String invoice = intent.getStringExtra("invoice");
        name = intent.getStringExtra("name");

        int paid = MainActivity.databaseP.paymentDao().totalAmount(invoice);
        MainActivity.database.customerDao().updateBalance(paid, invoice);

        adapter.reload(invoice);
        load();
    }

    public void Back(View view) {
        Intent intent = new Intent(view.getContext(), CustomerSummary.class);
        intent.putExtra("name", name);
        view.getContext().startActivity(intent);
    }
}