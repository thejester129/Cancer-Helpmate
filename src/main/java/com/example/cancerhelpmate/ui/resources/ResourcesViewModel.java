package com.example.cancerhelpmate.ui.resources;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.cancerhelpmate.R;

import java.util.ArrayList;
import java.util.List;

public class ResourcesViewModel extends AndroidViewModel {
    public ResourcesViewModel(@NonNull Application application) {
        super(application);
    }

    public List<ResourceItem> getResources(){
        List<ResourceItem> resourceItems = new ArrayList<>();
        resourceItems.add(new ResourceItem("Cancer Research UK",R.drawable.cancer_research_icon ,"https://www.cancerresearchuk.org/about-cancer/coping",R.drawable.cancer_research_gradient_background));
        resourceItems.add(new ResourceItem("Teenage Cancer Trust",R.drawable.teenage_cancer_trust_icon ,"https://teenagecancertrust.org/advice",R.drawable.teenage_cancer_trust_gradient_background));
        resourceItems.add(new ResourceItem("Macmillan Support",R.drawable.macmillan_icon ,"https://www.macmillan.org.uk/cancer-information-and-support",R.drawable.macmillan_gradient_background));
        resourceItems.add(new ResourceItem("Cancer Care Map",R.drawable.cancer_care_map_icon ,"https://www.cancercaremap.org/",R.drawable.cancer_care_map_gradient_background));
        resourceItems.add(new ResourceItem("Maggie's Centers",R.drawable.maggies_centres_icon ,"https://www.maggies.org/cancer-support/",R.drawable.maggies_centers_gradient_background));

        return resourceItems;
    }
}
