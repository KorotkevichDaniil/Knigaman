package com.example.bank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ValutesAdapter extends RecyclerView.Adapter<ValutesAdapter.ViewHolder> {

    interface OnValuteClickListener{
        void onValuteClick(Valute val, int position);
    }

    final LayoutInflater inflater;
    final List<Valute> valutes;
    final OnValuteClickListener onClickListener;

    public ValutesAdapter(Context context, List<Valute> valutes, OnValuteClickListener onClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.valutes = valutes;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        int position = pos;
        Valute v = valutes.get(position);
        holder.charc.setText(v.getCharCode());
        holder.valute.setText(v.getValue());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onValuteClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return valutes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView charc;
        final TextView valute;
        ViewHolder(@NonNull View view) {
            super(view);
            charc = view.findViewById(R.id.charc);
            valute = view.findViewById(R.id.valute);
        }
    }
}
