package com.example.cancerhelpmate.ui.daytracker.day_tracker_records;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerEntryInfoDialog;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DayTrackerRecordsRecyclerAdapter extends RecyclerView.Adapter<DayTrackerRecordsRecyclerAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private DayTrackerViewModel viewModel;
    private List<DayTrackerEntry> items;
    private FragmentManager fragmentManager;


    public DayTrackerRecordsRecyclerAdapter(FragmentManager fragmentManager, RecyclerView recyclerView, DayTrackerViewModel viewModel, View view) {
        this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public DayTrackerRecordsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_tracker_recycler_list_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                DialogFragment dialog = DayTrackerEntryInfoDialog.newInstance(items.get(pos));
                dialog.show(fragmentManager, "tag");
            }

        });

        DayTrackerRecordsRecyclerAdapter.ViewHolder holder = new DayTrackerRecordsRecyclerAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull DayTrackerRecordsRecyclerAdapter.ViewHolder holder, int position) {
        DayTrackerEntry entry = items.get(position);
        holder.date.setText(entry.getDate());
        String painString = entry.getPainLevel() + "";
        holder.painLevel.setText(painString);
        holder.emotion.setImageResource(entry.getEmotion().getPicture());
        int painLevel = entry.getPainLevel();
        if(painLevel < 4){
            holder.painBackground.setImageResource(R.color.colorPrimary);
        }
        else if(painLevel < 7){
            holder.painBackground.setImageResource(R.color.colorSecondary);
        }
        else {
            holder.painBackground.setImageResource(R.color.colorRed);
        }
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

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView date;
        private TextView painLevel;
        private CircleImageView painBackground;
        private CircleImageView emotion;

        private ViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.day_tracker_recycler_item_date);
            painLevel = itemView.findViewById(R.id.day_tracker_recycler_item_pain_text);
            painBackground = itemView.findViewById(R.id.day_tracker_recycler_item_pain_background);
            emotion = itemView.findViewById(R.id.day_tracker_recycler_item_emotion);
        }
    }

}

