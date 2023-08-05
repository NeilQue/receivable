package com.benerson.benersonreceivables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static java.lang.Integer.parseInt;

public class NewPayment extends AppCompatActivity {
    private Payment current;
    private String name;
    private String invoiceNumber;
    private EditText invoiceET;
    private EditText amountET;
    private EditText dateET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_payment);

        current = MainActivity.databaseP.paymentDao().getLastPayment();

        Intent intent = getIntent();
        invoiceNumber = intent.getStringExtra("invoiceNumber");
        name = intent.getStringExtra("name");

        invoiceET = findViewById(R.id.p_invoice_edit_text);
        amountET = findViewById(R.id.p_amount_edit_text);
        dateET = findViewById(R.id.p_date_edit_text);

        load();
    }

    private void load() {
        invoiceET.setText(current.invoiceNumber);
        amountET.setText(String.valueOf(current.amount));
        dateET.setText(current.paymentDate);
    }

    public void SaveDetails(View view) {
        MainActivity.databaseP.paymentDao().saveDetails(
                invoiceET.getText().toString(),
                parseInt(amountET.getText().toString()),
                dateET.getText().toString(),
                current.id
        );

        Intent intent = new Intent(view.getContext(), CustomerPayments.class);
        intent.putExtra("invoice", invoiceNumber);
        intent.putExtra("name", name);
        view.getContext().startActivity(intent);
    }
}