package com.example.cancerhelpmate.ui.daytracker.emotions;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotionItem;

import java.util.ArrayList;
import java.util.List;

public class DayTrackerEmotions {

    public static List<DayTrackerEmotionItem> getAllEmotions(){
        List<DayTrackerEmotionItem>emotions = new ArrayList<>();

        emotions.add(new DayTrackerEmotionHappy());
        emotions.add(new DayTrackerEmotionSad());
        emotions.add(new DayTrackerEmotionCalm());
        emotions.add(new DayTrackerEmotionAnxious());
        emotions.add(new DayTrackerEmotionEnergetic());
        emotions.add(new DayTrackerEmotionTired());
        emotions.add(new DayTrackerEmotionEmotional());
        emotions.add(new DayTrackerEmotionNumb());

        return emotions;
    }


    public static class DayTrackerEmotionHappy extends DayTrackerEmotionItem{
        @Override
        public String getName() {
            return "Happy";
        }
        @Override
        public int getPicture() {
            return R.drawable.emotion_happy;
        }
    }

    public static class DayTrackerEmotionSad extends DayTrackerEmotionItem{
        @Override
        public String getName() {
            return "Sad";
        }
        @Override
        public int getPicture() {
            return R.drawable.emotion_sad;
        }
    }

    public static class DayTrackerEmotionCalm extends DayTrackerEmotionItem{
        @Override
        public String getName() {
            return "Calm";
        }
        @Override
        public int getPicture() {
            return R.drawable.emotion_calm;
        }
    }

    public static class DayTrackerEmotionAnxious extends DayTrackerEmotionItem{
        @Override
        public String getName() {
            return "Anxious";
        }
        @Override
        public int getPicture() {
            return R.drawable.emotion_anxious;
        }
    }

    public static class DayTrackerEmotionEnergetic extends DayTrackerEmotionItem{
        @Override
        public String getName() {
            return "Energetic";
        }
        @Override
        public int getPicture() {
            return R.drawable.emotion_energetic;
        }
    }

    public static class DayTrackerEmotionTired extends DayTrackerEmotionItem{
        @Override
        public String getName() {
            return "Tired";
        }
        @Override
        public int getPicture() {
            return R.drawable.emotion_tired;
        }
    }

    public static class DayTrackerEmotionEmotional extends DayTrackerEmotionItem {
        @Override
        public String getName() {
            return "Emotional";
        }
        @Override
        public int getPicture() {
            return R.drawable.emotion_emotional;
        }
    }

    public static class DayTrackerEmotionNumb extends DayTrackerEmotionItem{
        @Override
        public String getName() {
            return "Numb";
        }
        @Override
        public int getPicture() {
            return R.drawable.emotion_numb;
        }
    }
}
