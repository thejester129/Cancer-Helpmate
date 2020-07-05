package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.DietBrowserSortDialogBinding;
import com.example.cancerhelpmate.databinding.DietRecipeBrowserDialogBinding;

public class DietBrowserSortDialog extends DialogFragment {

        private DietViewModel viewModel;

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
            binding.setLifecycleOwner(getViewLifecycleOwner());
            View view = binding.getRoot();

            binding.dietRecipeBrowserDialogToolbarCloseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.resetBrowserFilter();
                    dismiss();
                }
            });

            binding.dietBrowserSortDialogApplyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            return view;
        }

    }
