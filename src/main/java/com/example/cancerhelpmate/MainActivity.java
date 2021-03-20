package com.example.cancerhelpmate;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_weekly.DayTrackerWeeklyEntryDialog;
import com.example.cancerhelpmate.ui.home.WelcomeDialog;
import com.example.cancerhelpmate.ui.profile.ProfileEditDialog;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;
import com.example.cancerhelpmate.ui.wellbeing.WellbeingViewModel;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private ProfileViewModel profileViewModel;
    private WellbeingViewModel wellbeingViewModel;
    private DayTrackerViewModel dayTrackerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_wellbeing,R.id.nav_day_tracker, R.id.nav_activities, R.id.nav_my_hospital,R.id.nav_checklist,R.id.nav_resources, R.id.nav_settings)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        getViewModels();
        setupOnboarding();
        setupWeeklyDayTracker();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                navigateToFrag(R.id.nav_profile);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void navigateToFrag(int id){
        navController.navigate(id);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void getViewModels(){
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        wellbeingViewModel = new ViewModelProvider(this).get(WellbeingViewModel.class);
        dayTrackerViewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
    }

    private void setupOnboarding(){
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.getProfile();
        profileViewModel.getLiveIsInitialised().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@NotNull Boolean initialised) {
                if(!initialised){
                    showWelcomeDialog();
                }
            }
        });
    }

    private void showWelcomeDialog(){
        DialogFragment dialog = ProfileEditDialog.newInstance(profileViewModel);
        dialog.show(getSupportFragmentManager(), "tag");

        DialogFragment welcomeDialog = WelcomeDialog.newInstance();
        welcomeDialog.show(getSupportFragmentManager(), "tag");
    }

    private void setupWeeklyDayTracker(){
        if(profileViewModel.getProfile().isInitialised() && dayTrackerViewModel.weeklyDayTrackerReady()){
            DialogFragment dialog = new DayTrackerWeeklyEntryDialog(dayTrackerViewModel);
            dialog.show(getSupportFragmentManager(),"tag");
        }
    }

}
