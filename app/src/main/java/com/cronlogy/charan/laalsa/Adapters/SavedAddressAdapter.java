package com.cronlogy.charan.laalsa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cronlogy.charan.laalsa.Models.SavedAddressModel;
import com.cronlogy.charan.laalsa.R;

import java.util.ArrayList;

import fr.arnaudguyon.smartfontslib.FontTextView;

public class SavedAddressAdapter extends RecyclerView.Adapter<SavedAddressAdapter.SavedAddressViewHolder> {

    Context context;
    ArrayList<SavedAddressModel> savedAddressList;
    boolean selectedTag= false;
    int selectedTagPositon=-1;
    int previousSelectedPosition=-1;

    public SavedAddressAdapter(Context context, ArrayList<SavedAddressModel> savedAddressList) {
        this.context = context;
        this.savedAddressList = savedAddressList;
    }

    @NonNull
    @Override
    public SavedAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_address,parent,false);
        return new SavedAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedAddressViewHolder holder, final int position) {
            holder.address.setText(savedAddressList.get(position).getAddress());
            holder.title.setText(savedAddressList.get(position).getTitle());



            holder.savedAddressIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(previousSelectedPosition!=selectedTagPositon)
                        previousSelectedPosition=selectedTagPositon;
                    selectedTagPositon=position;
                    selectedTag = true;
                    notifyDataSetChanged();
                }
            });

            holder.savedAddressLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(previousSelectedPosition!=selectedTagPositon)
                        previousSelectedPosition=selectedTagPositon;
                    selectedTagPositon=position;
                    selectedTag = true;
                    notifyDataSetChanged();
                }
            });

        if(selectedTag){
            if(selectedTagPositon==position){
                    if(previousSelectedPosition!=-1){
                        SavedAddressModel savedAddressModel = savedAddressList.get(previousSelectedPosition);
                        savedAddressModel.setSelected(false);
                    }
                SavedAddressModel savedAddressModel = savedAddressList.get(selectedTagPositon);
                savedAddressModel.setSelected(true);
            }else{
                if(previousSelectedPosition!=-1){
                    SavedAddressModel savedAddressModel = savedAddressList.get(previousSelectedPosition);
                    savedAddressModel.setSelected(false);
                }
                SavedAddressModel savedAddressModel = savedAddressList.get(selectedTagPositon);
                savedAddressModel.setSelected(true);
            }
        }

        if(savedAddressList.get(position).isSelected()){
            holder.savedAddressIV.setBackground(context.getResources().getDrawable(R.drawable.green_cirlce_bg));
        }else{
            holder.savedAddressIV.setBackground(context.getResources().getDrawable(R.drawable.white_circle_with_border));
        }


    }

    @Override
    public int getItemCount() {
        return savedAddressList.size();
    }

    public class SavedAddressViewHolder extends RecyclerView.ViewHolder {
        ImageView savedAddressIV;
        FontTextView title;
        FontTextView address;
        RelativeLayout savedAddressLayout;

        public SavedAddressViewHolder(View itemView) {
            super(itemView);
            savedAddressIV=(ImageView)itemView.findViewById(R.id.savedAddressIV);
            title=(FontTextView) itemView.findViewById(R.id.title);
            address=(FontTextView)itemView.findViewById(R.id.address);
            savedAddressLayout=(RelativeLayout)itemView.findViewById(R.id.savedAddressLayout);
        }
    }
}
