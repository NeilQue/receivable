package com.benerson.benersonreceivables;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UnpaidSummary extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UnpaidSummaryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unpaid_summary);

        textView = findViewById(R.id.unpaid_total);
        recyclerView = findViewById(R.id.unpaid_recycler_view);
        adapter = new UnpaidSummaryAdapter();
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        load();
    }

    public void load() {
        adapter.reload();
        int total = MainActivity.database.customerDao().totalUnpaid();
        textView.setText(String.format("Total: %d", total));
    }
}