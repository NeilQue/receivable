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

public class UnpaidSummaryAdapter extends RecyclerView.Adapter<UnpaidSummaryAdapter.UnpaidSummaryViewHolder> {
    public static class UnpaidSummaryViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView textView;

        UnpaidSummaryViewHolder(View view) {
            super(view);

            this.containerView = view.findViewById(R.id.unpaid_summary_row);
            this.textView = view.findViewById(R.id.unpaid_summary_row_text_view);
        }
    }

    @NonNull
    @Override
    public UnpaidSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.unpaid_summary_row, parent, false);

        return new UnpaidSummaryViewHolder(view);
    }

    List<Customer> unpaidList = new ArrayList<>();

    @Override
    public void onBindViewHolder(@NonNull UnpaidSummaryViewHolder holder, int position) {
        Customer current = unpaidList.get(position);
        holder.textView.setText(String.format("%s - %s - %d - %s",
                current.name, current.invoiceNumber, current.balance, current.purchaseDate));
    }

    @Override
    public int getItemCount() {
        return unpaidList.size();
    }

    public void reload() {
        unpaidList = MainActivity.database.customerDao().getAllUnpaid();
    }
}
