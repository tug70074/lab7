package edu.temple.lab7;


import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class BrowserFragment extends Fragment {


    private EditText url;
    private Button goButton;
    private WebView webView;

    public BrowserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            URL = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        url = (EditText) v.findViewById(R.id.urlText);
        goButton = (Button) v.findViewById(R.id.goButton);
        webView = (WebView) v.findViewById(R.id.webView);


        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        WebView webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URL);
        return view;

        return v;
    }



}
