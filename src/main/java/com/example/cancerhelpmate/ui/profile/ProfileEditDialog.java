package com.example.cancerhelpmate.ui.profile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.databinding.ChecklistEntryDialogBinding;
import com.example.cancerhelpmate.databinding.ProfileEditDialogBinding;
import com.example.cancerhelpmate.ui.settings.SettingsViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfileEditDialog extends DialogFragment {

    private Button startDateButton;
    private Button endDateButton;
    private Spinner diagnosisSpinner;
    private Button saveButton;
    private ImageButton closeButton;
    private ProfileViewModel viewModel;

    public ProfileEditDialog(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static ProfileEditDialog newInstance(ProfileViewModel profileViewModel) {
        return new ProfileEditDialog(profileViewModel);
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
        ProfileEditDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.profile_edit_dialog,container,false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();
        getReferences(view);
        createBindings();
        viewModel.setEditProfile(viewModel.getProfile());
        return view;
    }

    public void getReferences(View view) {
        startDateButton = view.findViewById(R.id.profile_edit_dialog_start_date_button);
        endDateButton = view.findViewById(R.id.profile_edit_dialog_end_date_button);
        diagnosisSpinner = view.findViewById(R.id.profile_edit_dialog_diagnosis_spinner);
        saveButton = view.findViewById(R.id.profile_edit_dialog_save_button);
        closeButton = view.findViewById(R.id.toolbar_close_button);
    }

    public void createBindings() {
        setupDiagnosisSpinner();
        setupDates();

        saveButton.setOnClickListener(saveButtonListener);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setupDates(){
        startDateButton.setOnClickListener(startDateButtonListener);
        endDateButton.setOnClickListener(endDateButtonListener);
    }

    private void setupDiagnosisSpinner(){
        List<String> categories = new ArrayList<>();
        categories.add("Brain and CNS Tumour");
        categories.add("Germ Cell Tumour");
        categories.add("Lymphoma");
        categories.add("Sarcoma");
        categories.add("Other");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diagnosisSpinner.setAdapter(dataAdapter);
        if(viewModel.getProfile().isInitialised()){
            switch (viewModel.getProfile().getDiagnosis()){
                case "Brain and CNS Tumour":
                    diagnosisSpinner.setSelection(0);
                    break;
                case "Germ Cell Tumour":
                    diagnosisSpinner.setSelection(1);
                    break;
                case "Lymphoma":
                    diagnosisSpinner.setSelection(2);
                    break;
                case "Sarcoma":
                    diagnosisSpinner.setSelection(3);
                    break;
                case "Other":
                    diagnosisSpinner.setSelection(4);
                    break;
            }
        }
    }

    private View.OnClickListener startDateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog startDatePickerDialog = new DatePickerDialog(getContext(), startDateListener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            startDatePickerDialog.show();
        }
    };

    private View.OnClickListener endDateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatePickerDialog endDatePickerDialog = new DatePickerDialog(getContext(), endDateListener, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            endDatePickerDialog.show();
        }
    };

    private DatePickerDialog.OnDateSetListener startDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String dayString = Integer.toString(dayOfMonth);
            String monthString = Integer.toString(month + 1);
            if(dayOfMonth < 10){
                dayString = "0" + dayString;
            }
            if(month < 10){
                monthString = "0" + monthString;
            }
            String startDate = dayString + "/" + monthString + "/" + year;
            viewModel.getEditProfile().setStart_date(startDate);
            startDateButton.setText(startDate);
        }
    };

    private DatePickerDialog.OnDateSetListener endDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            String dayString = Integer.toString(dayOfMonth);
            String monthString = Integer.toString(month + 1);
            if(dayOfMonth < 10){
                dayString = "0" + dayString;
            }
            if(month < 10){
                monthString = "0" + monthString;
            }
            String endDate = dayString + "/" + monthString + "/" + year;
            viewModel.getEditProfile().setEnd_date(endDate);
            endDateButton.setText(endDate);
        }
    };

    private View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        if (viewModel.editProfileComplete()){
            viewModel.updateEditProfile();
            dismiss();
        }
        }
    };

}