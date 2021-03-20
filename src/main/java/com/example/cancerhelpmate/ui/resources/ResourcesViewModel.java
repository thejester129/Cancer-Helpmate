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

    public List<ResourceItem> getDiagnosisResources(){
        List<ResourceItem> resourceItems = new ArrayList<>();
        resourceItems.add(new ResourceItem("Just Been Diagnosed?",R.drawable.macmillan_icon ,"https://www.macmillan.org.uk/cancer-information-and-support/diagnosis/just-been-diagnosed",R.drawable.macmillan_gradient_background));
        resourceItems.add(new ResourceItem("Diagnosis and Treatment Statistics",R.drawable.cancer_research_icon ,"https://www.cancerresearchuk.org/health-professional/diagnosis",R.drawable.cancer_research_gradient_background));
        resourceItems.add(new ResourceItem("Getting Diagnosed",R.drawable.teenage_cancer_trust_icon ,"https://www.teenagecancertrust.org/get-help/i-think-i-might-have-cancer/getting-diagnosed",R.drawable.teenage_cancer_trust_gradient_background));

        return resourceItems;
    }

    public List<ResourceItem> getTreatmentResources(){
        List<ResourceItem> resourceItems = new ArrayList<>();
        resourceItems.add(new ResourceItem("Coping with Cancer",R.drawable.cancer_research_icon ,"https://www.cancerresearchuk.org/about-cancer/coping",R.drawable.cancer_research_gradient_background));
        resourceItems.add(new ResourceItem("Advice",R.drawable.teenage_cancer_trust_icon ,"https://teenagecancertrust.org/advice",R.drawable.teenage_cancer_trust_gradient_background));
        resourceItems.add(new ResourceItem("Information and Support",R.drawable.macmillan_icon ,"https://www.macmillan.org.uk/cancer-information-and-support",R.drawable.macmillan_gradient_background));
        resourceItems.add(new ResourceItem("Health and Wellbeing",R.drawable.cancer_care_map_icon ,"https://www.cancercaremap.org/support-service/health-and-wellbeing",R.drawable.cancer_care_map_gradient_background));
        resourceItems.add(new ResourceItem("Managing Emotions",R.drawable.maggies_centres_icon ,"https://www.maggies.org/cancer-support/managing-emotions/",R.drawable.maggies_centers_gradient_background));

        return resourceItems;
    }

    public List<ResourceItem> getRecoveryResources(){
        List<ResourceItem> resourceItems = new ArrayList<>();
        resourceItems.add(new ResourceItem("After Treatment",R.drawable.teenage_cancer_trust_icon ,"https://www.teenagecancertrust.org/get-help/ive-got-cancer/treatment/after-treatment",R.drawable.teenage_cancer_trust_gradient_background));
        resourceItems.add(new ResourceItem("Beginning to Recover",R.drawable.macmillan_icon ,"https://www.macmillan.org.uk/cancer-information-and-support/after-treatment/beginning-to-recover",R.drawable.macmillan_gradient_background));
        resourceItems.add(new ResourceItem("Practical Concerns",R.drawable.cancer_care_map_icon ,"https://www.cancercaremap.org/support-service/practical-concerns",R.drawable.cancer_care_map_gradient_background));

        return resourceItems;
    }
}
