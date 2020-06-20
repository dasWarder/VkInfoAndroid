package com.example.vkinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vkinfo.interfaces.ErrorMessageInterface;
import com.example.vkinfo.utils.DataResponse;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.vkinfo.utils.NetworkUtils.generateUrl;
import static com.example.vkinfo.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {

    private EditText editField;
    private Button searchButton;
    private TextView resultField;
    private TextView errorMessage;
    private ProgressBar connectionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVar();

        View.OnClickListener onClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final URL generatedURL = generateUrl(editField.getText().toString());
                new DataResponse(resultField, errorMessage, connectionBar).execute(generatedURL);
            }
        };

        searchButton.setOnClickListener(onClickListener);
    }


    private void initVar() {
        editField = findViewById(R.id.et_search_field_vk);
        searchButton = findViewById(R.id.b_search_vk);
        resultField = findViewById(R.id.tv_show_result);
        errorMessage = findViewById(R.id.tv_error_message);
        connectionBar = findViewById(R.id.pb_loading_indicator);
    }

}
