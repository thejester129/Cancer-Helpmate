package com.example.cancerhelpmate.common;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.resources.ResourceItem;

public class WebDialog extends DialogFragment {
    private TextView title;
    private ImageButton closeButton;
    private androidx.appcompat.widget.Toolbar toolbar;
    private WebView browser;
    private String link;

    public static WebDialog newInstance(String link) {
        return new WebDialog(link);
    }

    public WebDialog(String link) {
        this.link = link;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(requireActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                if (browser.canGoBack()) {
                    browser.goBack();
                } else {
                    dismiss();
                }
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resources_web_dialog, container, false);

        browser = view.findViewById(R.id.resources_web_dialog_webview);
        browser.setWebViewClient(new WebDialog.MyBrowser());
        browser.loadUrl(link);

        closeButton = view.findViewById(R.id.resources_web_dialog_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        toolbar = view.findViewById(R.id.resources_web_dialog_toolbar);
        toolbar.setBackgroundResource(R.color.colorAccent);

        return view;
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
