package com.example.cancerhelpmate.ui.daytracker.day_tracker_graphs;

import android.graphics.Color;
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
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class DayTrackerGraphsFragment extends Fragment {
    private DayTrackerViewModel viewModel;
    private GraphView graph;

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
                if(graphSettings.isGraphFatigueChecked()){
                    addFatigueGraph();
                    binding.dayTrackerFatigueTick.setVisibility(View.VISIBLE);
                }
                else{
                    binding.dayTrackerFatigueTick.setVisibility(View.GONE);
                }
                if(graphSettings.isGraphAppetiteChecked()){
                    addAppetiteGraph();
                    binding.dayTrackerAppetiteTick.setVisibility(View.VISIBLE);
                }
                else{
                    binding.dayTrackerAppetiteTick.setVisibility(View.GONE);
                }
                if(graphSettings.isGraphTreatmentChecked()){
                    addTreatmentGraph();
                    binding.dayTrackerTreatmentTick.setVisibility(View.VISIBLE);
                }
                else{
                    binding.dayTrackerTreatmentTick.setVisibility(View.GONE);
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
        binding.dayTrackerGraphFatigueText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.toggleGraphFatigueChecked();
            }
        });

        binding.dayTrackerGraphAppetiteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.toggleGraphAppetiteChecked();
            }
        });

        binding.dayTrackerGraphTreatmentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.toggleGraphTreatmentChecked();
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
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        addPainGraph();
        addFatigueGraph();
        addAppetiteGraph();
    }

    private void resetGraph(){
        graph.removeAllSeries();
    }

    private void addPainGraph(){
        graph.getViewport().setMaxX(viewModel.getGraphTotalDays());

        LineGraphSeries<DataPoint> painSeries = new LineGraphSeries<>(viewModel.getGraphPainValues());

        painSeries.setTitle("Pain");
        painSeries.setDrawDataPoints(true);
        painSeries.setColor(Color.RED);
        graph.addSeries(painSeries);
    }

    private void addFatigueGraph(){
        graph.getViewport().setMaxX(viewModel.getGraphTotalDays());

        LineGraphSeries<DataPoint> fatigueSeries = new LineGraphSeries<>(viewModel.getGraphFatigueValues());

        fatigueSeries.setTitle("Fatigue");
        fatigueSeries.setDrawDataPoints(true);
        fatigueSeries.setColor(Color.BLUE);
        graph.addSeries(fatigueSeries);
    }

    private void addAppetiteGraph(){
        graph.getViewport().setMaxX(viewModel.getGraphTotalDays());

        LineGraphSeries<DataPoint> appetiteSeries = new LineGraphSeries<>(viewModel.getGraphAppetiteValues());

        appetiteSeries.setTitle("Appetite");
        appetiteSeries.setDrawDataPoints(true);
        appetiteSeries.setColor(Color.GREEN);
        graph.addSeries(appetiteSeries);
    }

    private void addTreatmentGraph(){
        graph.getViewport().setMaxX(viewModel.getGraphTotalDays());

        LineGraphSeries<DataPoint> treatmentSeries = new LineGraphSeries<>(viewModel.getGraphTreatmentValues());

        treatmentSeries.setTitle("Treatment");
        treatmentSeries.setColor(Color.argb(255,255,153,0));
        treatmentSeries.setDrawBackground(true);
        treatmentSeries.setBackgroundColor(Color.argb(128,255,153,0));
        graph.addSeries(treatmentSeries);
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
