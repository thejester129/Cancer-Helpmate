package com.example.cancerhelpmate.ui.daytracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.databinding.DayTrackerRecyclerHeaderItemBinding;
import com.example.cancerhelpmate.databinding.DayTrackerRecyclerListItemBinding;

import java.util.List;

public class DayTrackerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView recyclerView;
    private DayTrackerViewModel viewModel;
    private List<DayTrackerEntry> items;
    private FragmentManager fragmentManager;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_LIST = 1;

    public DayTrackerRecyclerAdapter(FragmentManager fragmentManager, RecyclerView recyclerView, DayTrackerViewModel viewModel, View view) {
        this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_tracker_recycler_list_item, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = recyclerView.getChildAdapterPosition(v);
                    DialogFragment dialog = DayTrackerEntryInfoDialog.newInstance(items.get(pos));
                    dialog.show(fragmentManager, "tag");
                }

            });
            holder = new VHListItem(view);

        }

        else if (viewType == TYPE_HEADER) {
            if(viewModel.getTodaysEntryFilled()){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_tracker_recycler_header_item, parent, false);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = recyclerView.getChildAdapterPosition(v);
                        DialogFragment dialog = DayTrackerEntryEditDialog.newInstance(viewModel, items.get(pos));
                        dialog.show(fragmentManager, "tag");
                    }

                });
                holder = new VHHeaderItem(view);

            }
            else{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_tracker_recycler_header_empty_item, parent, false);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = recyclerView.getChildAdapterPosition(v);
                        DialogFragment dialog = DayTrackerEntryEditDialog.newInstance(viewModel);
                        dialog.show(fragmentManager, "tag");
                    }

                });
                holder = new VHHeaderEmptyItem(view);
            }
        }
        return holder;

    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_LIST;
    }

    private boolean isPositionHeader(int position) {
        return position == items.size() - 1;
    }

    public void setItems(List<DayTrackerEntry> items){
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHHeaderItem) {
            ((VHHeaderItem) holder).bindEntry(viewModel.getTodaysEntry());
        }
        else if (holder instanceof VHListItem) {
            ((VHListItem) holder).bindEntry(items.get(position));
        }
    }

    static class VHListItem extends RecyclerView.ViewHolder{
        private DayTrackerRecyclerListItemBinding binding;

        private VHListItem(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
        public void bindEntry(DayTrackerEntry entry){
            binding.setEntry(entry);
        }
    }

    static class VHHeaderItem extends RecyclerView.ViewHolder{
        private DayTrackerRecyclerHeaderItemBinding binding;

        private VHHeaderItem(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
        public void bindEntry(DayTrackerEntry entry){
            binding.setEntry(entry);
        }
    }

    static class VHHeaderEmptyItem extends RecyclerView.ViewHolder{
        private VHHeaderEmptyItem(View itemView) {
            super(itemView);
        }
    }

}

