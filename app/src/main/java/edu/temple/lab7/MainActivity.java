package edu.temple.lab7;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText urlTextView;
    WebView display;
    Button goButton;

    Handler showContent = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {

            display.setWebViewClient(new WebViewClient());
            display.loadUrl((String) msg.obj);
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (WebView) findViewById(R.id.webContainer);
        urlTextView = (EditText) findViewById(R.id.urlText);
        goButton = (Button) findViewById(R.id.urlButton);

        goButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Thread loadContent = new Thread() {
                    @Override
                    public void run() {

                        if (isNetworkActive()) {

                            URL url;

                            try {
                                url = new URL(urlTextView.getText().toString());
                                Message msg = Message.obtain();

                                msg.obj = url;

                                showContent.sendMessage(msg);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Please connect to a network", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                loadContent.start();
            }
        });
    }
    public boolean isNetworkActive() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void addFragment(Fragment fragment, int containerID){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerID, fragment)
                .addToBackStack(null)
                .commit();
    }
}
