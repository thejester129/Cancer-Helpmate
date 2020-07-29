package com.example.cancerhelpmate.ui.daytracker.day_tracker_today.word_tool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.DayTrackerHeaderTextRecyclerItemBinding;

import java.util.List;

public class DayTrackerHeaderWordsRecyclerAdapter extends RecyclerView.Adapter<DayTrackerHeaderWordsRecyclerAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private List<DayTrackerHeaderWord> items;
    private DayTrackerBodyWordsRecyclerAdapter bodyWordsRecyclerAdapter;

    public DayTrackerHeaderWordsRecyclerAdapter(RecyclerView recyclerView, DayTrackerBodyWordsRecyclerAdapter bodyWordsRecyclerAdapter) {
        this.recyclerView = recyclerView;
        this.bodyWordsRecyclerAdapter = bodyWordsRecyclerAdapter;
    }

    @NonNull
    @Override
    public DayTrackerHeaderWordsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_tracker_header_text_recycler_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                DayTrackerHeaderWord word = items.get(pos);
                bodyWordsRecyclerAdapter.setItems(word.getWords());
                unselectWords();
                word.setSelected(true);
                notifyDataSetChanged();

            }

        });
        DayTrackerHeaderWordsRecyclerAdapter.ViewHolder holder = new DayTrackerHeaderWordsRecyclerAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindEntry(items.get(position));
    }

    public void setItems(List<DayTrackerHeaderWord> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else return 0;
    }

    private void unselectWords(){
        for(DayTrackerHeaderWord word : items){
            word.setSelected(false);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private DayTrackerHeaderTextRecyclerItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindEntry(DayTrackerHeaderWord entry){
            binding.setEntry(entry);
        }
    }

}