package com.example.cancerhelpmate.ui.resources;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ViewHolder>  {
    private FragmentManager fragmentManager;
    private RecyclerView recyclerView;
    private List<ResourceItem> resources;

    public ResourcesAdapter( FragmentManager fragmentManager , RecyclerView recyclerView, List<ResourceItem>resources) {
        this.fragmentManager = fragmentManager;
        this.recyclerView = recyclerView;
        this.resources = resources;
    }

    @NonNull
    @Override
    public ResourcesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resources_recycler_item, parent, false);
        final ResourcesAdapter adapter = this;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                DialogFragment dialog = ResourceWebDialog.newInstance(resources.get(pos));
                dialog.show(fragmentManager, "tag");

            }

        });
        ResourcesAdapter.ViewHolder holder = new ResourcesAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResourcesAdapter.ViewHolder holder, int position) {
        holder.name.setText(resources.get(position).getName());
        holder.image.setImageResource( resources.get(position).getImage());
        holder.background.setImageResource(resources.get(position).getBgColor());
    }

    @Override
    public int getItemCount() {
        return resources.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        CircleImageView image;
        ImageView background;

        private ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.resources_recycler_item_name);
            image = itemView.findViewById(R.id.resources_recycler_item_image);
            background = itemView.findViewById(R.id.resources_recycler_item_background);
        }
    }
}
