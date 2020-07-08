package com.example.cancerhelpmate.ui.daytracker.emotions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotionItem;

import java.util.List;

public class DayTrackerEmotionSpinnerAdapter extends BaseAdapter {
    Context context;
    List<DayTrackerEmotionItem> emotions;
    LayoutInflater inflater;

    public DayTrackerEmotionSpinnerAdapter(Context applicationContext, List<DayTrackerEmotionItem>emotions) {
        this.context = applicationContext;
        this.emotions = emotions;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return emotions.size();
    }

    @Override
    public DayTrackerEmotionItem getItem(int i) {
        return emotions.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.day_tracker_emotion_spinner_item, null);
        ImageView image =  view.findViewById(R.id.day_tracker_emotion_spinner_item_image);
        TextView name =  view.findViewById(R.id.day_tracker_emotion_spinner_item_text);
        image.setImageResource(emotions.get(i).getPicture());
        name.setText(emotions.get(i).getName());
        return view;
    }

}
