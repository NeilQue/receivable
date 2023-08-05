package com.benerson.benersonreceivables;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerPaymentsAdapter extends RecyclerView.Adapter<CustomerPaymentsAdapter.CustomerPaymentsViewholder> {
    public static class CustomerPaymentsViewholder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView textView;

        CustomerPaymentsViewholder(View view) {
            super(view);

            this.containerView = view.findViewById(R.id.customer_payments_row);
            this.textView = view.findViewById(R.id.customer_payments_row_text_view);
        }
    }

    @NonNull
    @Override
    public CustomerPaymentsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_payments_row, parent, false);

        return new CustomerPaymentsViewholder(view);
    }

    List<Payment> paymentList = new ArrayList<>();

    @Override
    public void onBindViewHolder(@NonNull CustomerPaymentsViewholder holder, int position) {
        Payment current = paymentList.get(position);
        holder.textView.setText(String.format("%d - %s", current.amount, current.paymentDate));
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    public void reload(String invoiceNumber) {
       paymentList = MainActivity.databaseP.paymentDao().getAllPayments(invoiceNumber);
    }
}
