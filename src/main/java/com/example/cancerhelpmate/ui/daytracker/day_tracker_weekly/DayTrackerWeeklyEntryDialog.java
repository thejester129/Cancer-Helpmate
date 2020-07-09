package com.example.cancerhelpmate.ui.daytracker.day_tracker_weekly;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.MainActivity;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.GMailSender;
import com.example.cancerhelpmate.database.daytracker.DayTrackerWeeklyEntry;
import com.example.cancerhelpmate.databinding.DayTrackerWeeklyDialogEntryBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotionSpinnerAdapter;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotions;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

public class DayTrackerWeeklyEntryDialog extends DialogFragment {

    private DayTrackerViewModel viewModel;
    private ProfileViewModel profileViewModel;
    private DayTrackerWeeklyEntry entry;
    private ImageButton toolbarCloseButton;
    private Button sendButton;


    public DayTrackerWeeklyEntryDialog(DayTrackerViewModel viewModel) {
        this.viewModel = viewModel;
        entry = new DayTrackerWeeklyEntry();
    }

    public static DayTrackerWeeklyEntryDialog newInstance(DayTrackerViewModel viewModel) {
        return new DayTrackerWeeklyEntryDialog(viewModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DayTrackerWeeklyDialogEntryBinding binding = DataBindingUtil.inflate(inflater, R.layout.day_tracker_weekly_dialog_entry,container,false);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        View view = binding.getRoot();
        binding.setEntry(entry);
        binding.setLifecycleOwner(this);
        getReferences(view);
        createBindings();
        return view;
    }

    private void getReferences(View view){
        toolbarCloseButton = view.findViewById(R.id.day_tracker_weekly_dialog_close);
        sendButton = view.findViewById(R.id.day_tracker_weekly_dialog_send);
    }

    public void createBindings() {
        toolbarCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        sendButton.setOnClickListener(sendButtonListener);
    }


    private View.OnClickListener sendButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewModel.addEntry(entry);
            sendEmail();
            dismiss();
            Toast.makeText(getContext(), "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
        }
    };

    private void sendEmail(){

        new Thread(new Runnable() {

            public void run() {

                try {
                    GMailSender sender = new GMailSender(
                            "gripexfufuu123@gmail.com",
                            "Cheesetoast01");

                    sender.sendMail("CancerHelpmate Patient Weekly Report",
                            "Name : " + profileViewModel.getProfile().getName()
                                    + "\n\nHow would you rate your quality of life in the last week?\n\nPatient response : " + entry.getQuality_of_life()
                                    + "\n\nHow satisfied are you with your health in the last week? \n\nPatient response : " + entry.getHealth()
                                    + "\n\nHow available has the information that you need in relation to your cancer experience been in the last week?  \n\nPatient response : " + entry.getInformation()
                                    + "\n\nAdditional comments : " + entry.getComment(),


                            "gripexfufuu123@gmail.com",

                            "thejester129@gmail.com");


                } catch (Exception e) {

                    Toast.makeText(getContext(),"Error sending email",Toast.LENGTH_LONG).show();

                }
            }

        }).start();
    }

}
