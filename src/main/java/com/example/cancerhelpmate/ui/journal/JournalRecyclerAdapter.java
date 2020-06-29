package com.example.cancerhelpmate.ui.journal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.journal.JournalEntry;
import com.example.cancerhelpmate.databinding.JournalRecyclerItemBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class JournalRecyclerAdapter extends RecyclerView.Adapter<JournalRecyclerAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private JournalViewModel viewModel;
    private List<JournalEntry> items;
    private FragmentManager fragmentManager;

    public JournalRecyclerAdapter(FragmentManager fragmentManager, RecyclerView recyclerView, JournalViewModel viewModel, View view) {
        this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_recycler_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                DialogFragment dialog = JournalEntryDialog.newInstance(items.get(pos), viewModel);
                dialog.show(fragmentManager, "tag");
            }

        });
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setItems(List<JournalEntry> items){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindEntry(items.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private JournalRecyclerItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bindEntry(JournalEntry entry){
            binding.setEntry(entry);
        }
    }

}
