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

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    public static class MainViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout containerView;
        public TextView textView;

        MainViewHolder(View view) {
            super(view);

            containerView = view.findViewById(R.id.main_recycler_view_row);
            textView = view.findViewById(R.id.main_recycler_view_row_text_view);

            this.containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    String current = (String) containerView.getTag();
                    Intent intent = new Intent(context, CustomerSummary.class);
                    intent.putExtra("name", current);

                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.main_recycler_view_row, parent, false);

        return new MainViewHolder(view);
    }

    private List<String> customerList = new ArrayList<>();

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        String current = customerList.get(position);
        holder.textView.setText(current);
        holder.containerView.setTag(current);
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void reload() {
        customerList = MainActivity.database.customerDao().getAllNames();
        notifyDataSetChanged();
    }
}
