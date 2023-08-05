package com.benerson.benersonreceivables;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static java.lang.Integer.parseInt;

public class NewCustomer extends AppCompatActivity {
    private Customer current;
    private EditText nameET;
    private EditText invoiceET;
    private EditText amountET;
    private EditText balanceET;
    private EditText dateET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        current = MainActivity.database.customerDao().getLast();

        nameET = findViewById(R.id.new_name_edit_text);
        invoiceET = findViewById(R.id.new_invoice_edit_text);
        amountET = findViewById(R.id.new_amount_edit_text);
        balanceET = findViewById(R.id.new_balance_edit_text);
        dateET = findViewById(R.id.new_date_edit_text);

        loadDetails();
    }

    public void SaveNewDetails(View view) {
        MainActivity.database.customerDao().saveDetails(
                current.id,
                nameET.getText().toString(),
                invoiceET.getText().toString(),
                parseInt(amountET.getText().toString()),
                parseInt(balanceET.getText().toString()),
                dateET.getText().toString());
    }

    public void loadDetails() {
        nameET.setText(current.name);
        invoiceET.setText(current.invoiceNumber);
        amountET.setText(String.valueOf(current.amount));
        balanceET.setText(String.valueOf(current.balance));
        dateET.setText(current.purchaseDate);
    }

    public void BackToMain(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        view.getContext().startActivity(intent);
    }

    public void BackToSumm(View view) {
        Intent intent = new Intent(view.getContext(), CustomerSummary.class);
        intent.putExtra("name", nameET.getText().toString());
        view.getContext().startActivity(intent);
    }
}