package com.sww.testspinner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter extends RecyclerView.Adapter<SpinnerAdapter.Holder> {

    private ArrayList<String> arrayList;

    public SpinnerAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(new TextView(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        String content = arrayList.get(i);
        ((TextView) holder.itemView).setText(content);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public Holder(@NonNull View itemView) {
            super(itemView);
        }

    }

}
