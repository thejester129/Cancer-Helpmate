package com.example.cancerhelpmate.ui.daytracker.day_tracker_graphs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.FragmentDayTrackerGraphsBinding;
import com.example.cancerhelpmate.databinding.FragmentJournalBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class DayTrackerGraphsFragment extends Fragment {
    private DayTrackerViewModel viewModel;
    private GraphView graph;
    private LineGraphSeries<DataPoint> painSeries;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDayTrackerGraphsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day_tracker_graphs,container,false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        initGraph(view);
        setupObserver(binding);
        createBindings(binding);
        setupSpinner(binding);
        return view;
    }

    private void setupObserver(final FragmentDayTrackerGraphsBinding binding){
        viewModel.graphSettings.observe(getViewLifecycleOwner(), new Observer<DayTrackerGraphSettings>() {
            @Override
            public void onChanged(DayTrackerGraphSettings graphSettings) {
                resetGraph();
                if(graphSettings.isGraphPainChecked()){
                    addPainGraph();
                    binding.dayTrackerPainTick.setVisibility(View.VISIBLE);
                }
                else{
                    binding.dayTrackerPainTick.setVisibility(View.GONE);
                }
            }
        });
    }

    private void createBindings( FragmentDayTrackerGraphsBinding binding){
        binding.dayTrackerGraphPainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.toggleGraphPainChecked();
            }
        });
    }

    private void initGraph(View view){
        graph = view.findViewById(R.id.day_tracker_graph_view);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(7);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10);
        addPainGraph();
    }

    private void resetGraph(){
        graph.removeAllSeries();
    }

    private void addPainGraph(){
        graph.getViewport().setMaxX(viewModel.getGraphTotalDays());

        painSeries = new LineGraphSeries<>(viewModel.getGraphPainValues());

        painSeries.setTitle("Pain Level");
        painSeries.setDrawDataPoints(true);
        graph.addSeries(painSeries);
    }

    private void setupSpinner(FragmentDayTrackerGraphsBinding binding){
        Spinner spinner = binding.dayTrackerTimeSpinner;

        List<String> categories = new ArrayList<String>();
        categories.add("Last Week");
        categories.add("Last Month");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        viewModel.setGraphTimelineLastWeek();
                        break;
                    case 1:
                        viewModel.setGraphTimelineLastMonth();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
