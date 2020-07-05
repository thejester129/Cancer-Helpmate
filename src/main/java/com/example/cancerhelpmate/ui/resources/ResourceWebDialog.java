package com.example.cancerhelpmate.ui.resources;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;

public class ResourceWebDialog extends DialogFragment {
    private TextView title;
    private ResourceItem resource;
    private ImageButton closeButton;
    private androidx.appcompat.widget.Toolbar toolbar;
    private WebView browser;

    public static ResourceWebDialog newInstance(ResourceItem resource) {
        return new ResourceWebDialog(resource);
    }

    public ResourceWebDialog(ResourceItem resource) {
        this.resource = resource;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(requireActivity(), getTheme()){
            @Override
            public void onBackPressed() {
                if (browser.canGoBack()) {
                    browser.goBack();
                }
                else{
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
        browser.setWebViewClient(new MyBrowser());
        browser.loadUrl(resource.getLink());

        title = view.findViewById(R.id.resources_web_dialog_title);
        title.setText(resource.getName());

        closeButton = view.findViewById(R.id.resources_web_dialog_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        toolbar = view.findViewById(R.id.resources_web_dialog_toolbar);
        toolbar.setBackgroundResource(resource.getBgColor());

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
