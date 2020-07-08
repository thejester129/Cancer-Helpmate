package com.example.cancerhelpmate.ui.activities.sleep_aid;

import androidx.lifecycle.ViewModel;

import com.example.cancerhelpmate.R;

import java.util.ArrayList;
import java.util.List;

public class SleepAidViewModel extends ViewModel {

    public SleepAidLevel getLevel(int position){
        return getLevels().get(position);
    }

    public List<SleepAidLevel> getLevels(){
        List<SleepAidLevel>levels = new ArrayList<>();
        levels.add(new SleepAidLevel("Forest", R.drawable.forest_background,R.raw.forest_background_soundscape));
        levels.add(new SleepAidLevel("Ocean",R.drawable.ocean_background,R.raw.ocean_background_soundscape));
        levels.add(new SleepAidLevel("Rain",R.drawable.rain_background,R.raw.rain_background_soundscape));
        levels.add(new SleepAidLevel("Amazon",R.drawable.amazon_background,R.raw.amazon_background_soundscape));
        levels.add(new SleepAidLevel("Whale",R.drawable.whale_background,R.raw.whale_background_soundscape));

        return levels;
    }
}
