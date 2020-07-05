package com.example.cancerhelpmate.common;

import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class BindingUtils {

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("android:text")
    public static void setDouble(EditText view, double value) {
        if (Double.isNaN(value) || value == 0){
            view.setText("");
        }
        else{
            view.setText(String.valueOf(value));
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static double getDouble(EditText view) {
        String num = view.getText().toString();
        if(num.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    @BindingAdapter("android:text")
    public static void setInt(EditText view, int value) {
        if (value == 0){
            view.setText("");
        }
        else{
            view.setText(String.valueOf(value));
        }

    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getInt(EditText view) {
        String num = view.getText().toString();
        if(num.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
