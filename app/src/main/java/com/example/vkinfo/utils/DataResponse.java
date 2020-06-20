package com.example.vkinfo.utils;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vkinfo.interfaces.ErrorMessageInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.vkinfo.utils.NetworkUtils.getResponseFromURL;

public class DataResponse extends AsyncTask<URL, Void, String>  implements ErrorMessageInterface {
    private TextView textView;
    private TextView errorMessage;
    private ProgressBar connectionBar;

    public DataResponse(TextView textView, TextView errorMessage, ProgressBar connectionBar) {
        this.textView = textView;
        this.errorMessage = errorMessage;
        this.connectionBar = connectionBar;
    }

    @Override
    protected void onPreExecute() {
        showProgressBar();
    }


    @Override
    protected String doInBackground(URL... urls) {
            String response = null;

            try {
            response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
            e.printStackTrace();
            }
            return response;
            }

    @Override
    protected void onPostExecute(String s) {
        textView.setText(getParsedString(s));
        textView.setTextColor(Color.parseColor("#ffa500"));
        connectionBar.setVisibility(View.INVISIBLE);
    }


    private String getParsedString(String s) {
        String firstName = null;
        String last_name = null;

        if (s != null && !s.equals("")) {

            try {
                JSONObject userInfo = parseResponse(s);
                firstName = userInfo.getString("first_name");
                last_name = userInfo.getString("last_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            showResyltTextView();
            return "Name: " + firstName + "\n" + "Last name: " + last_name;
        }

        else {
            showErrorTextView();
        }

        return null;
    }

    private JSONObject parseResponse(String s) throws JSONException {
        JSONObject jsonResp = new JSONObject(s);
        JSONArray jsonArray = jsonResp.getJSONArray("response");
        return jsonArray.getJSONObject(0);
    }

    @Override
    public void showResyltTextView() {
        textView.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorTextView() {
        textView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar() {
        connectionBar.setVisibility(View.VISIBLE);
    }
}