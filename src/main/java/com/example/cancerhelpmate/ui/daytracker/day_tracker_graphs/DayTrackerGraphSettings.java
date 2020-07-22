package com.example.cancerhelpmate.ui.daytracker.day_tracker_graphs;

public class DayTrackerGraphSettings {
    private boolean graphPainChecked = true;
    private boolean graphFatigueChecked = true;
    private boolean graphAppetiteChecked = true;
    private boolean graphTreatmentChecked = true;
    private boolean graphModeLastWeek = true;
    private boolean graphModeLastMonth;

    public DayTrackerGraphSettings(){

    }

    public boolean isGraphPainChecked() {
        return graphPainChecked;
    }

    public void setGraphPainChecked(boolean graphPainChecked) {
        this.graphPainChecked = graphPainChecked;
    }

    public boolean isGraphFatigueChecked() {
        return graphFatigueChecked;
    }

    public void setGraphFatigueChecked(boolean graphFatigueChecked) {
        this.graphFatigueChecked = graphFatigueChecked;
    }

    public boolean isGraphAppetiteChecked() {
        return graphAppetiteChecked;
    }

    public void setGraphAppetiteChecked(boolean graphAppetiteChecked) {
        this.graphAppetiteChecked = graphAppetiteChecked;
    }

    public boolean isGraphTreatmentChecked() {
        return graphTreatmentChecked;
    }

    public void setGraphTreatmentChecked(boolean graphTreatmentChecked) {
        this.graphTreatmentChecked = graphTreatmentChecked;
    }

    public boolean isGraphModeLastWeek() {
        return graphModeLastWeek;
    }

    public void setGraphModeLastWeek(boolean graphModeLastWeek) {
        this.graphModeLastWeek = graphModeLastWeek;
        if(graphModeLastWeek){
            graphModeLastMonth = false;
        }
    }

    public boolean isGraphModeLastMonth() {
        return graphModeLastMonth;
    }

    public void setGraphModeLastMonth(boolean graphModeLastMonth) {
        this.graphModeLastMonth = graphModeLastMonth;
        if(graphModeLastMonth){
            graphModeLastWeek = false;
        }
    }

    public void togglePainChecked(){
        this.graphPainChecked = !graphPainChecked;
    }

    public void toggleFatigueChecked(){
        this.graphFatigueChecked = !graphFatigueChecked;
    }

    public void toggleAppetiteChecked(){
        this.graphAppetiteChecked = !graphAppetiteChecked;
    }

    public void toggleTreatmentChecked(){
        this.graphTreatmentChecked = !graphTreatmentChecked;
    }
}
