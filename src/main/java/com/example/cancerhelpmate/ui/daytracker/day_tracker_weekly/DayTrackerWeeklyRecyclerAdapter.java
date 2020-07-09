package com.example.cancerhelpmate.ui.daytracker.day_tracker_weekly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.database.daytracker.DayTrackerWeeklyEntry;
import com.example.cancerhelpmate.database.journal.JournalEntry;
import com.example.cancerhelpmate.databinding.DayTrackerWeeklyRecyclerItemBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerEntryInfoDialog;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DayTrackerWeeklyRecyclerAdapter extends RecyclerView.Adapter<DayTrackerWeeklyRecyclerAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private DayTrackerViewModel viewModel;
    private List<DayTrackerWeeklyEntry> items;
    private FragmentManager fragmentManager;

    public DayTrackerWeeklyRecyclerAdapter(FragmentManager fragmentManager, RecyclerView recyclerView, DayTrackerViewModel viewModel, View view) {
        this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public DayTrackerWeeklyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_tracker_weekly_recycler_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                DialogFragment dialog = DayTrackerWeeklyInfoDialog.newInstance(items.get(pos));
                dialog.show(fragmentManager, "tag");
            }

        });

        DayTrackerWeeklyRecyclerAdapter.ViewHolder holder = new DayTrackerWeeklyRecyclerAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull DayTrackerWeeklyRecyclerAdapter.ViewHolder holder, int position) {
        holder.bindEntry(items.get(position));
    }

    public void setItems(List<DayTrackerWeeklyEntry> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private DayTrackerWeeklyRecyclerItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
        public void bindEntry(DayTrackerWeeklyEntry entry){
            binding.setEntry(entry);
        }
    }
}