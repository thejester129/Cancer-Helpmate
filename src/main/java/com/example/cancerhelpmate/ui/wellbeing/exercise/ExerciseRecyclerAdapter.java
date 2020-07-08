package com.example.cancerhelpmate.ui.wellbeing.exercise;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerEntryInfoDialog;


import java.util.List;

public class ExerciseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView recyclerView;
    private ExerciseViewModel viewModel;
    private List<ExerciseItem> items;
    private FragmentManager fragmentManager;
    private Activity activity;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_LIST = 1;

    public ExerciseRecyclerAdapter(FragmentManager fragmentManager, RecyclerView recyclerView, ExerciseViewModel viewModel, FragmentActivity activity) {
        this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
        this.viewModel = viewModel;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_recycler_text_item, parent, false);
            holder = new VHHeaderItem(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = recyclerView.getChildAdapterPosition(v);
                    items.get(pos).toggleExpanded();
                    notifyItemChanged(pos);
                }

            });
        }

        if (viewType == TYPE_LIST) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_recycler_item, parent, false);
            holder = new VHExerciseItem(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ExerciseItem item = items.get(position);

        if (holder instanceof VHExerciseItem) {
            VHExerciseItem exerciseItem = (VHExerciseItem) holder;
            exerciseItem.title.setText(item.getTitle());
            String benefitsString = "Benefits: " + item.getBenefits();
            exerciseItem.benefits.setText(benefitsString);

            exerciseItem.expandButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.toggleExpanded();
                    notifyItemChanged(position);
                }
            });

            exerciseItem.videoImage.setImageResource(item.getImageResource());

            exerciseItem.videoImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getVideoLink())));
                }
            });

            if(item.isExpanded()){
                exerciseItem.dropdownImage.setImageResource(R.drawable.ic_arrow_up_black);
                exerciseItem.videoImage.setVisibility(View.VISIBLE);
            }
            else{
                exerciseItem.dropdownImage.setImageResource(item.getIcon());
                exerciseItem.videoImage.setVisibility(View.GONE);
            }
        }
        else if (holder instanceof VHHeaderItem) {
            VHHeaderItem headerItem = (VHHeaderItem) holder;
            headerItem.title.setText(item.getTitle());
            headerItem.description.setText(item.getDescription());
            headerItem.expandButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.toggleExpanded();
                    notifyItemChanged(position);
                }
            });

            if(item.isExpanded()){
                headerItem.dropdownImage.setImageResource(R.drawable.ic_arrow_up_black);
                headerItem.description.setVisibility(View.VISIBLE);
            }
            else{
                headerItem.dropdownImage.setImageResource(item.getIcon());
                headerItem.description.setVisibility(View.GONE);
            }

        }
    }

    public void setItems(List<ExerciseItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_LIST;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else return 0;
    }


    public class VHExerciseItem extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView benefits;
        private Button expandButton;
        private ImageView dropdownImage;
        private ImageView videoImage;

        private VHExerciseItem(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.exercise_recycler_item_title);
            benefits = itemView.findViewById(R.id.exercise_recycler_item_benefits);
            expandButton = itemView.findViewById(R.id.exercise_recycler_item_expand_button);
            dropdownImage = itemView.findViewById(R.id.exercise_recycler_item_dropdown);
            videoImage = itemView.findViewById(R.id.exercise_recycler_item_video_button);
        }

    }

    public class VHHeaderItem extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private Button expandButton;
        private ImageView dropdownImage;

        private VHHeaderItem(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.exercise_recycler_text_item_title);
            description = itemView.findViewById(R.id.exercise_recycler_text_item_description);
            expandButton = itemView.findViewById(R.id.exercise_recycler_text_item_expand_button);
            dropdownImage = itemView.findViewById(R.id.exercise_recycler_text_item_dropdown);
        }

    }
}

