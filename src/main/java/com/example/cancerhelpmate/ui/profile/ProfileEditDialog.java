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
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.ui.settings.SettingsViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfileEditDialog extends DialogFragment {

    private EditText nameEditText;
    private Button startDateButton;
    private Button endDateButton;
    private String startDate;
    private String endDate;
    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;
    private Spinner diagnosisSpinner;
    private CheckBox chemotherapyCheckbox;
    private CheckBox radiotherapyCheckbox;
    private CheckBox surgeryCheckbox;
    private CheckBox boneMarrowCheckbox;
    private CheckBox otherTreatmentCheckbox;
    private Button saveButton;
    private ImageButton closeButton;
    private ProfileViewModel profileViewModel;
    private TextView dialogHeader;


    public ProfileEditDialog() {
    }

    public static ProfileEditDialog newInstance() {
        return new ProfileEditDialog();
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
        View view = inflater.inflate(R.layout.profile_edit_dialog, container, false);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        getControlReferences(view);
        createBindings();
        return view;
    }

    public void getControlReferences(View view) {
        nameEditText = view.findViewById(R.id.profile_edit_dialog_name);
        startDateButton = view.findViewById(R.id.profile_edit_dialog_start_date_button);
        endDateButton = view.findViewById(R.id.profile_edit_dialog_end_date_button);
        diagnosisSpinner = view.findViewById(R.id.profile_edit_dialog_diagnosis_spinner);
        chemotherapyCheckbox = view.findViewById(R.id.profile_edit_dialog_treatment_chemotherapy);
        radiotherapyCheckbox = view.findViewById(R.id.profile_edit_dialog_treatment_radiotherapy);
        surgeryCheckbox = view.findViewById(R.id.profile_edit_dialog_treatment_surgery);
        boneMarrowCheckbox = view.findViewById(R.id.profile_edit_dialog_treatment_bone_marrow);
        otherTreatmentCheckbox = view.findViewById(R.id.profile_edit_dialog_treatment_other);
        saveButton = view.findViewById(R.id.profile_edit_dialog_save_button);
        closeButton = view.findViewById(R.id.toolbar_close_button);
        dialogHeader = view.findViewById(R.id.toolbar_close_text);
    }

    public void createBindings() {
        setupDiagnosisSpinner();
        setupTreatmentCheckboxes();
        setupDates();

        saveButton.setOnClickListener(saveButtonListener);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if(!profileViewModel.getProfile().isInitialised()){
            closeButton.setVisibility(View.INVISIBLE);
        }
        dialogHeader.setText(R.string.setup_profile);

        if(profileViewModel.getProfile().isInitialised()){
            nameEditText.setText(profileViewModel.getProfile().getName());
            startDateButton.setText(profileViewModel.getProfile().getStart_date());
            endDateButton.setText(profileViewModel.getProfile().getEnd_date());
        }
    }

    private void setupDates(){
        startDate = profileViewModel.getProfile().getStart_date();
        endDate = profileViewModel.getProfile().getEnd_date();
        startDateButton.setText(startDate);
        endDateButton.setText(endDate);

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
        if(profileViewModel.getProfile().isInitialised()){
            switch (profileViewModel.getProfile().getDiagnosis()){
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


    private void setupTreatmentCheckboxes(){
        chemotherapyCheckbox.setChecked(profileViewModel.getProfile().isTreatment_chemotherapy());
        radiotherapyCheckbox.setChecked(profileViewModel.getProfile().isTreatment_radiotherapy());
        surgeryCheckbox.setChecked(profileViewModel.getProfile().isTreatment_surgery());
        boneMarrowCheckbox.setChecked(profileViewModel.getProfile().isTreatment_bone_marrow());
        otherTreatmentCheckbox.setChecked(profileViewModel.getProfile().isTreatment_bone_marrow());
    }

    private View.OnClickListener startDateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startDatePickerDialog = new DatePickerDialog(getContext(), startDateListener, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            startDatePickerDialog.show();
        }
    };

    private View.OnClickListener endDateButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            endDatePickerDialog = new DatePickerDialog(getContext(), endDateListener, Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
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
            startDate = dayString + "/" + monthString + "/" + year;
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
            endDate = dayString + "/" + monthString + "/" + year;
            endDateButton.setText(endDate);
        }
    };

    private View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        if (nameEditText.getText().toString().isEmpty()) {
            Toast toast = Toast.makeText(getContext(),"Please enter your name", Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(diagnosisSpinner.getSelectedItem() == null){
            Toast toast = Toast.makeText(getContext(),"Please select a diagnosis", Toast.LENGTH_SHORT);
            toast.show();
        }
        //TODO date checks
        else if(!chemotherapyCheckbox.isChecked() && !radiotherapyCheckbox.isChecked() && !surgeryCheckbox.isChecked() && !boneMarrowCheckbox.isChecked() && !otherTreatmentCheckbox.isChecked()){
            Toast toast = Toast.makeText(getContext(),"Please select a treatment", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            updateProfile();
            dismiss();
        }
        }
    };

    private void updateProfile(){
        profileViewModel.setName(nameEditText.getText().toString());
        profileViewModel.setStartTreatmentDate(startDate);
        profileViewModel.setEndTreatmentDate(endDate);
        profileViewModel.setDiagnosis(diagnosisSpinner.getSelectedItem().toString());
        profileViewModel.updateTreatment(chemotherapyCheckbox.isChecked(), radiotherapyCheckbox.isChecked(),surgeryCheckbox.isChecked(),boneMarrowCheckbox.isChecked(),otherTreatmentCheckbox.isChecked());
        profileViewModel.setInitialised();
    }

}