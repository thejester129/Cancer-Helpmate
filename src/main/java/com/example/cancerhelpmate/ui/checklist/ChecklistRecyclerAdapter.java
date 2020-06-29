package com.example.cancerhelpmate.ui.checklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.ItemTouchHelperAdapter;
import com.example.cancerhelpmate.database.checklist.ChecklistEntry;
import com.example.cancerhelpmate.databinding.ChecklistRecyclerItemBinding;

import java.util.List;

public class ChecklistRecyclerAdapter extends RecyclerView.Adapter<ChecklistRecyclerAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private RecyclerView recyclerView;
    private ChecklistViewModel viewModel;
    private List<ChecklistEntry> items;
    private Context context;

    public ChecklistRecyclerAdapter( RecyclerView recyclerView, ChecklistViewModel viewModel, Context context) {
        this.recyclerView = recyclerView;
        this.viewModel = viewModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ChecklistRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checklist_recycler_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                viewModel.toggleChecked(items.get(pos));
                notifyItemChanged(pos);
            }

        });
        ChecklistRecyclerAdapter.ViewHolder holder = new ChecklistRecyclerAdapter.ViewHolder(view);
        return holder;
    }

    public void setItems(List<ChecklistEntry> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ChecklistRecyclerAdapter.ViewHolder holder, int position) {
        holder.bindEntry(items.get(position));
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDismiss(int position) {
        viewModel.deleteEntry(items.get(position));
    }

    @Override
    public Context getContext() {
        return context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ChecklistRecyclerItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindEntry(ChecklistEntry entry){
            binding.setEntry(entry);
        }
    }

}

