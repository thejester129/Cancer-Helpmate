package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.databinding.DietRecipeBrowserRecyclerItemBinding;


import java.util.List;

public class DietRecipeBrowserRecyclerAdapter extends RecyclerView.Adapter<DietRecipeBrowserRecyclerAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private DietViewModel viewModel;
    private List<RecipeEntry> items;
    private FragmentManager fragmentManager;

    public DietRecipeBrowserRecyclerAdapter(RecyclerView recyclerView, DietViewModel viewModel, FragmentManager fragmentManager) {
        this.recyclerView = recyclerView;
        this.viewModel = viewModel;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public DietRecipeBrowserRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_recipe_browser_recycler_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                DialogFragment dialog = DietRecipeInformationDialog.newInstance(items.get(pos), viewModel);
                dialog.show(fragmentManager, "tag");
            }

        });
        DietRecipeBrowserRecyclerAdapter.ViewHolder holder = new DietRecipeBrowserRecyclerAdapter.ViewHolder(view);
        return holder;
    }

    public void setItems(List<RecipeEntry> items){
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
    public void onBindViewHolder(@NonNull DietRecipeBrowserRecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(items.get(position).getName());
        holder.image.setImageResource(items.get(position).getImageLink());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.diet_recipe_browser_recycler_item_image);
            title = itemView.findViewById(R.id.diet_recipe_browser_recycler_item_title);
        }
    }

}