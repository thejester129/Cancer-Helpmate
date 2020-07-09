package com.example.cancerhelpmate.ui.daytracker.day_tracker_graphs;

public class DayTrackerGraphSettings {
    private boolean graphPainChecked = true;
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
}
