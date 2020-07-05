package com.example.cancerhelpmate.database.daytracker;

import androidx.room.TypeConverter;

import com.example.cancerhelpmate.ui.daytracker.DayTrackerEmotionItem;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerEmotions;


public class DayTrackerEmotionConverter {
    @TypeConverter
    public static int emotionToInt(DayTrackerEmotionItem emotion) {
        if (emotion == null) {
            return 0;
        }
        if (DayTrackerEmotions.DayTrackerEmotionHappy.class.equals(emotion.getClass()))
            return 1;
        if (DayTrackerEmotions.DayTrackerEmotionSad.class.equals(emotion.getClass()))
            return 2;
        else if (DayTrackerEmotions.DayTrackerEmotionCalm.class.equals(emotion.getClass()))
            return 3;
        else if (DayTrackerEmotions.DayTrackerEmotionAnxious.class.equals(emotion.getClass()))
            return 4;
        else if (DayTrackerEmotions.DayTrackerEmotionEnergetic.class.equals(emotion.getClass()))
            return 5;
        else if (DayTrackerEmotions.DayTrackerEmotionTired.class.equals(emotion.getClass()))
            return 6;
        else if (DayTrackerEmotions.DayTrackerEmotionEmotional.class.equals(emotion.getClass()))
            return 7;
        else if (DayTrackerEmotions.DayTrackerEmotionNumb.class.equals(emotion.getClass()))
            return 8;

        return 0;

    }

    @TypeConverter
    public static DayTrackerEmotionItem intToEmotion(int emotionNumber) {
        switch (emotionNumber){
            case 1:
                return new DayTrackerEmotions.DayTrackerEmotionHappy();
            case 2:
                return new DayTrackerEmotions.DayTrackerEmotionSad();
            case 3:
                return new DayTrackerEmotions.DayTrackerEmotionCalm();
            case 4:
                return new DayTrackerEmotions.DayTrackerEmotionAnxious();
            case 5:
                return new DayTrackerEmotions.DayTrackerEmotionEnergetic();
            case 6:
                return new DayTrackerEmotions.DayTrackerEmotionTired();
            case 7:
                return new DayTrackerEmotions.DayTrackerEmotionEmotional();
            case 8:
                return new DayTrackerEmotions.DayTrackerEmotionNumb();
            default:
                return null;
        }
    }
}
