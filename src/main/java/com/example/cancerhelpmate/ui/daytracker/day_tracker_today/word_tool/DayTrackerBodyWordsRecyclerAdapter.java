package com.example.cancerhelpmate.ui.daytracker.day_tracker_today.word_tool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.DayTrackerBodyTextRecyclerItemBinding;
import com.example.cancerhelpmate.databinding.DayTrackerHeaderTextRecyclerItemBinding;

import java.util.ArrayList;
import java.util.List;

public class DayTrackerBodyWordsRecyclerAdapter extends RecyclerView.Adapter<DayTrackerBodyWordsRecyclerAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private List<DayTrackerBodyWord> items;
    private EditText editText;

    public DayTrackerBodyWordsRecyclerAdapter(RecyclerView recyclerView, EditText editText) {
        this.recyclerView = recyclerView;
        this.editText = editText;
    }

    @NonNull
    @Override
    public DayTrackerBodyWordsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_tracker_body_text_recycler_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                DayTrackerBodyWord word = items.get(pos);
                editText.append(word.getHeaderText() + " " + word.getText() + ", ");
                setItems(new ArrayList<DayTrackerBodyWord>());
            }

        });
        DayTrackerBodyWordsRecyclerAdapter.ViewHolder holder = new DayTrackerBodyWordsRecyclerAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindEntry(items.get(position));
    }

    public void setItems(List<DayTrackerBodyWord> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private DayTrackerBodyTextRecyclerItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindEntry(DayTrackerBodyWord entry){
            binding.setEntry(entry);
        }
    }

}