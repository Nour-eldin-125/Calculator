package com.example.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Db.HistoryEntity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.viewHolder> {

    private List<HistoryEntity> hList = new ArrayList<>();
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new viewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        HistoryEntity current = hList.get(position);

        String [] list = current.getCalculation().split(" ");
        holder.tvcalc.setText(checkNumber(list[0]) +" "+list[1]+" "+checkNumber(list[2]));
        holder.tvres.setText(checkNumber(current.getResult()));

    }

    private String checkNumber (String numbers){
        NumberFormat eFormat = new DecimalFormat("0.###E0");;
        String num = numbers;
        int count = num.length();
        if (num.contains(".") || num.contains("-"))
            count--;
        if (count>10){
            num = (eFormat.format(Double.parseDouble(num)));
            return num;
        }
        else {
            return num;
        }
    }

    @Override
    public int getItemCount() {
        return hList.size();
    }

    public void sethList (List<HistoryEntity> he)
    {
        hList = he;
        notifyDataSetChanged();
    }


    public HistoryEntity gethis (int index){
        return hList.get(index);
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        public TextView tvcalc;
        public TextView tvres;
        public ConstraintLayout container;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvcalc = itemView.findViewById(R.id.textView);
            tvres = itemView.findViewById(R.id.textView2);
            container = itemView.findViewById(R.id.layoutContainer);
        }
    }
}
