package com.example.cancerhelpmate.ui.wellbeing.diet.filter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.DietBrowserSortDialogBinding;
import com.example.cancerhelpmate.ui.wellbeing.diet.DietViewModel;

import java.util.ArrayList;
import java.util.List;

public class DietBrowserSortDialog extends DialogFragment {

        private DietViewModel viewModel;
        private DietBrowserFilterSettings filterSettings;

        public DietBrowserSortDialog(DietViewModel viewModel) {
            this.viewModel = viewModel;
        }

        public static DietBrowserSortDialog newInstance(DietViewModel viewModel) {
            return new DietBrowserSortDialog(viewModel);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            DietBrowserSortDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.diet_browser_sort_dialog,container,false);
            binding.setViewModel(viewModel);
            filterSettings = viewModel.browserFilterSettings.getValue();
            binding.setFilterSettings(filterSettings);
            binding.setLifecycleOwner(getViewLifecycleOwner());
            View view = binding.getRoot();
            setupSpinner(view);

            binding.dietRecipeBrowserDialogToolbarCloseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            binding.dietBrowserSortDialogApplyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.browserFilterSettings.setValue(filterSettings);
                    dismiss();
                }
            });

            return view;
        }

        private void setupSpinner(View view){
            Spinner spinner = view.findViewById(R.id.diet_browser_sort_dialog_spinner);


            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            categories.add("Any");
            categories.add("Starters and Snacks");
            categories.add("Main");
            categories.add("Desserts");
            categories.add("Drinks and Smoothies");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position){
                        case 0:
                            filterSettings.setDietFilterRecipeType(DietFilterRecipeType.ANY);
                            break;
                        case 1:
                            filterSettings.setDietFilterRecipeType(DietFilterRecipeType.STARTERS);
                            break;
                        case 2:
                            filterSettings.setDietFilterRecipeType(DietFilterRecipeType.MAIN);
                            break;
                        case 3:
                            filterSettings.setDietFilterRecipeType(DietFilterRecipeType.DESSERTS);
                            break;
                        case 4:
                            filterSettings.setDietFilterRecipeType(DietFilterRecipeType.DRINKS);
                            break;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner.setSelection(0);

        }

    }
