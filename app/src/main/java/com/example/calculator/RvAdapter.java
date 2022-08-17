package com.example.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calculator.Db.HistoryEntity;

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
        holder.tvcalc.setText(current.getCalculation());
        holder.tvres.setText(current.getResult());
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
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvcalc = itemView.findViewById(R.id.textView);
            tvres = itemView.findViewById(R.id.textView2);
        }
    }
}
