package com.example.cancerhelpmate.ui.my_hospital;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.WebDialog;
import com.example.cancerhelpmate.databinding.FragmentMyHospitalBinding;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyHospitalFragment extends Fragment {
    private MyHospitalViewModel viewModel;
    private ProfileViewModel profileViewModel;
    private FragmentMyHospitalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_hospital, container, false);
        viewModel = new ViewModelProvider(this).get(MyHospitalViewModel.class);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        View view = binding.getRoot();
        binding.setLifecycleOwner(getViewLifecycleOwner());
        setupSpinner(view);
        setupWebview(view);
        setHasOptionsMenu(true);
        return view;
    }

    private void setupSpinner(View view){
        final Spinner spinner = view.findViewById(R.id.my_hospital_spinner);
        List<String> hospitalItems = new ArrayList<>();
        List<MyHospitalItem> hospitals = viewModel.getHospitals();
        for (MyHospitalItem hospital : hospitals) {
            hospitalItems.add(hospital.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, hospitalItems);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        if(profileViewModel.getProfile().getHospital() != null){
            for(int i = 0; i < hospitalItems.size(); i++){
                if(hospitalItems.get(i).equals(profileViewModel.getProfile().getHospital())){
                    spinner.setSelection(i);
                }
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                profileViewModel.setHospital(spinner.getItemAtPosition(position).toString());
                binding.setHospital(viewModel.getHospitals().get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupWebview(View view){
        TextView link = view.findViewById(R.id.my_hospital_link);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = WebDialog.newInstance(binding.getHospital().getLink());
                dialog.show(getParentFragmentManager(), "tag");
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        menu.findItem(R.id.action_profile).setIcon(profileViewModel.getProfile().getPicture());
    }

}

