package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cronlogy.charan.laalsa.R;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class AddressTagsAdapter extends RecyclerView.Adapter<AddressTagsAdapter.AddressTagViewHolder> {

    Context context;
    ArrayList<String> addressTagsList;
    boolean selectedTag= false;
    int selectedTagPositon=-1;

    public AddressTagsAdapter(Context context, ArrayList<String> addressTagsList) {
        this.context = context;
        this.addressTagsList = addressTagsList;
    }

    @NonNull
    @Override
    public AddressTagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address_tag,parent,false);
        return new AddressTagViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressTagViewHolder holder, final int position) {
        holder.addressTag.setText(addressTagsList.get(position));

        holder.addressTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTagPositon=position;
                selectedTag = true;
                notifyDataSetChanged();
            }
        });

        if(selectedTag){
            if(selectedTagPositon==position){
                holder.addressTag.setTextColor(context.getResources().getColor(R.color.black_variant_two));
                holder.addressTag.setBackground(context.getResources().getDrawable(R.drawable.address_tag_selected_bg));
            }else{
                holder.addressTag.setTextColor(context.getResources().getColor(R.color.address_tag_text_color));
                holder.addressTag.setBackground(context.getResources().getDrawable(R.drawable.address_tag_un_selected_bg));
            }
        }else{
            holder.addressTag.setTextColor(context.getResources().getColor(R.color.address_tag_text_color));
            holder.addressTag.setBackground(context.getResources().getDrawable(R.drawable.address_tag_un_selected_bg));
        }
    }

    @Override
    public int getItemCount() {
        return addressTagsList.size();
    }

    public class AddressTagViewHolder extends RecyclerView.ViewHolder {
        FontTextView addressTag;
        public AddressTagViewHolder(View itemView) {
            super(itemView);
            addressTag=(FontTextView)itemView.findViewById(R.id.addressTag);
        }
    }
}
