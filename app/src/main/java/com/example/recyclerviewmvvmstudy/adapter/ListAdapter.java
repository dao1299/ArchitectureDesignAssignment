package com.example.recyclerviewmvvmstudy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewmvvmstudy.BR;
import com.example.recyclerviewmvvmstudy.R;
import com.example.recyclerviewmvvmstudy.databinding.ItemLayoutBinding;
import com.example.recyclerviewmvvmstudy.model.DataModel;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    List<DataModel> dataModelList;

    public ListAdapter(List<DataModel> dataModelList) {
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_layout,parent,false);
            return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel dataModel = dataModelList.get(position);
        holder.bind(dataModel);
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ItemLayoutBinding binding;

        public ViewHolder(ItemLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj){
            binding.setVariable(BR.model,obj);
            binding.executePendingBindings();
        }

    }
}
