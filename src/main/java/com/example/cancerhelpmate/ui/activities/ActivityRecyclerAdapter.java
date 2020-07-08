package com.example.cancerhelpmate.ui.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.MainActivity;
import com.example.cancerhelpmate.R;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityRecyclerAdapter extends RecyclerView.Adapter<ActivityRecyclerAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private List<ActivityItem> items;
    private MainActivity activity;

    public ActivityRecyclerAdapter(MainActivity activity, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ActivityRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityItem item = items.get(recyclerView.getChildAdapterPosition(v));
                activity.navigateToFrag(item.getNavigationLink());
            }

        });
        ActivityRecyclerAdapter.ViewHolder holder = new ActivityRecyclerAdapter.ViewHolder(view);
        return holder;
    }

    public void setItems(List<ActivityItem> items){
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
    public void onBindViewHolder(@NonNull ActivityRecyclerAdapter.ViewHolder holder, int position) {
        ActivityItem item = items.get(position);
        holder.title.setText(item.getName());
        holder.icon.setImageResource(item.getIconResource());
        holder.background.setImageResource(item.getColorResource());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private CircleImageView icon;
        private ImageView background;

        private ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.activity_recycler_item_name);
            icon = itemView.findViewById(R.id.activity_recycler_item_image);
            background = itemView.findViewById(R.id.activity_recycler_item_background);
        }
    }
}
