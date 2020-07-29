package com.example.cancerhelpmate.ui.profile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.databinding.ChecklistEntryDialogBinding;
import com.example.cancerhelpmate.databinding.ProfileEditDialogBinding;
import com.example.cancerhelpmate.ui.settings.SettingsViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditDialog extends DialogFragment {

    private Button startDateButton;
    private Button endDateButton;
    private Spinner diagnosisSpinner;
    private Button saveButton;
    private ImageButton closeButton;
    private ProfileViewModel viewModel;
    private Spinner genderSpinner;
    private CircleImageView profilePicture;

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

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(requireActivity(), getTheme()){
            @Override
            public void onBackPressed() {
                if (viewModel.getProfile().isInitialised()) {
                    dismiss();
                }
                else{

                }
            }
        };
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
        profilePicture = view.findViewById(R.id.profile_edit_dialog_picture);
        //genderSpinner = view.findViewById(R.id.profile_edit_dialog_gender_spinner);
    }

    public void createBindings() {
        setupDiagnosisSpinner();
        //setupGenderSpinner();
        setupDates();

        saveButton.setOnClickListener(saveButtonListener);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment =  ProfilePictureDialog.newInstance(viewModel);
                dialogFragment.show(getParentFragmentManager(),"tag");
            }
        });
        viewModel.editPicture.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                profilePicture.setImageResource(s);
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
        diagnosisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.setEditProfileDiagnosis(diagnosisSpinner.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupGenderSpinner(){
        List<String> categories = new ArrayList<>();
        categories.add("Male");
        categories.add("Female");
        categories.add("Other/Prefer not to say");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(dataAdapter);
        if(viewModel.getProfile().isInitialised()){
            if (viewModel.getProfile().isMale()){
               genderSpinner.setSelection(0);
            }
            else if (viewModel.getProfile().isFemale()){
                genderSpinner.setSelection(1);
            }
            else if (viewModel.getProfile().isOther()){
                genderSpinner.setSelection(2);
            }

        }
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        viewModel.setMale();
                        break;
                    case 1:
                        viewModel.setFemale();
                        break;
                    case 2:
                        viewModel.setOther();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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