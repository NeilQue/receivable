package com.benerson.benersonreceivables;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerSummaryAdapter extends RecyclerView.Adapter<CustomerSummaryAdapter.CustomerSummaryViewHolder> {
    public static class CustomerSummaryViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView textView;

        CustomerSummaryViewHolder(View view) {
            super(view);

            this.containerView = view.findViewById(R.id.customer_summary_row);
            this.textView = view.findViewById(R.id.customer_summary_row_text_view);

            this.containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    String invoice = (String) containerView.getTag();
                    Intent intent = new Intent(context, CustomerPayments.class);
                    intent.putExtra("invoice", invoice);
                    intent.putExtra("name", CustomerSummary.currentName);

                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public CustomerSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.customer_summary_row, parent, false);

        return new CustomerSummaryViewHolder(view);
    }

    List<Customer> customerDetails = new ArrayList<>();

    @Override
    public void onBindViewHolder(@NonNull CustomerSummaryViewHolder holder, int position) {
        Customer current = customerDetails.get(position);
        holder.textView.setText(String.format("%s - %d - %d - %s",
                current.invoiceNumber, current.amount, current.balance, current.purchaseDate));
        holder.containerView.setTag(current.invoiceNumber);
    }

    @Override
    public int getItemCount() {
        return customerDetails.size();
    }

    public void reload(String name) {
        customerDetails = MainActivity.database.customerDao().getCustomerDetails(name);
    }
}
