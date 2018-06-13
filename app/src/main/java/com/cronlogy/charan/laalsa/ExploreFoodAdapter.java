package com.cronlogy.charan.laalsa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

public class ExploreFoodAdapter extends RecyclerView.Adapter<ExploreFoodAdapter.ExploreFoodViewHolder> {

    Context context;
    ArrayList<Object> arrayList;

    private static final int COLLECTIONS_CARD = 1;
    private static final int NORMAL_CARD = 2;

    public ExploreFoodAdapter(Context context, ArrayList<Object> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExploreFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreFoodViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ExploreFoodViewHolder extends RecyclerView.ViewHolder {
        public ExploreFoodViewHolder(View itemView) {
            super(itemView);
        }
    }
}
