package com.example.cancerhelpmate.ui.my_hospital;

import androidx.lifecycle.ViewModel;

import com.example.cancerhelpmate.R;

import java.util.ArrayList;
import java.util.List;

public class MyHospitalViewModel extends ViewModel {

        public List<MyHospitalItem> getHospitals(){
            List<MyHospitalItem>hospitals = new ArrayList<>();
            hospitals.add(new MyHospitalItem("Beatson West of Scotland Cancer Centre",
                    R.drawable.beatson_hospital_glasgow,
                    "The Beatson West of Scotland Cancer Centre,\n" +
                            "1053 Great Western Road,\n" +
                            "Glasgow.\n" +
                            "G12 0YN",
                    "https://www.beatson.scot.nhs.uk/content/default.asp?page=s22_2",
                    "The Teenage Cancer Trust Unit at the Beatson West of Scotland Cancer Centre is for 16-24\n" +
                    "year olds and there are 4 Teenage Cancer Trust beds in ward B4 and 4 Teenage Cancer Trust\n" +
                    "beds on ward B7.",
                    "8"));

            hospitals.add(new MyHospitalItem("Royal Hospital for Sick Children, Glasgow",
                    R.drawable.royal_hospital_glasgow,
                    "1345 Govan Road\n" +
                            "Govan\n" +
                            "G51 4TF\n" +
                            "Glasgow",
                    "https://www.nhsggc.org.uk/locations/hospitals/royal-hospital-for-children-glasgow/",
                    "The Teenage Cancer Trust Unit at the Royal Hospital for Sick Children is for 13-16 year olds and there are 8 Teenage Cancer Trust beds.",
                    "8"));

            hospitals.add(new MyHospitalItem("The Western General Hospital",
                    R.drawable.western_general_hospital,
                    "Western General Hospital\n" +
                            "Crewe Road South\n" +
                            "Edinburgh\n" +
                            "EH4 2XU",
                    "https://www.nhslothian.scot/GoingToHospital/Locations/WGH/Pages/default.aspx",
                    "The Teenage and Young Adult Cancer Unit at the Western General Hospital in Edinburgh is for 16-24 year olds and has 4 in-patient beds and a day care facility.",
                    "4"));

            hospitals.add(new MyHospitalItem("The Royal Hospital for Sick Children, Edinburgh",
                    R.drawable.royal_hospital_edinburgh,
                    "Royal Hospital for Sick Children\n" +
                            "9 Sciennes Road\n" +
                            "Edinburgh\n" +
                            "EH9 1LF",
                    "https://www.nhslothian.scot/GoingToHospital/Locations/RHSC/Pages/default.aspx",
                    "The Teenage Cancer Trust Unit at The Royal Hospital for Sick Children in Edinburgh is for 13 -16 year olds and currently has 2 in-patient beds.",
                    "2"));


            return hospitals;
        }
}
