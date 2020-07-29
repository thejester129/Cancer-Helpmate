package com.example.cancerhelpmate.ui.wellbeing.mental_wellbeing;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.activities.ActivityItem;
import com.example.cancerhelpmate.ui.resources.ResourceItem;

import java.util.ArrayList;
import java.util.List;

public class MentalWellbeingViewModel extends AndroidViewModel {
    public MentalWellbeingViewModel(@NonNull Application application) {
        super(application);
    }

    public List<ResourceItem> getMentalResources(){
        List<ResourceItem> resourceItems = new ArrayList<>();

        resourceItems.add(new ResourceItem("Managing Fatigue",R.drawable.emotion_tired,"https://www.macmillan.org.uk/cancer-information-and-support/impacts-of-cancer/tiredness",R.drawable.fatigue_level_gradient));
        resourceItems.add(new ResourceItem("Managing Pain",R.drawable.emotion_sad,"https://www.macmillan.org.uk/cancer-information-and-support/impacts-of-cancer/pain",R.drawable.pain_slider_gradient_background));
        resourceItems.add(new ResourceItem("Loss of Appetite",R.drawable.emotion_numb,"https://www.cancer.org/treatment/treatments-and-side-effects/physical-side-effects/eating-problems/poor-appetite.html",R.drawable.appetite_level_gradient));
        resourceItems.add(new ResourceItem("Cancer and Corona Virus",R.drawable.ic_sick,"https://www.teenagecancertrust.org/advice/coronavirus-when-you-have-or-have-had-cancer",R.drawable.teenage_cancer_trust_gradient_background));

        return resourceItems;
    }

}
