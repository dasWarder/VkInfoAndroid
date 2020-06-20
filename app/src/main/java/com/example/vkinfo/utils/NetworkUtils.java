package com.example.vkinfo.utils;


import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class NetworkUtils {

    private static final String VK_API_BASE_URL = "https://api.vk.com";
    private static final String VK_API_USERS_GET = "/method/users.get";
    private static final String VK_USER_ID_PARAM = "user_ids";
    private static final String VK_VERSION_PARAM = "v";
    private static final String VK_ACCESS_TOKEN_PARAM = "access_token";

    public static URL generateUrl(String userId) {

        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_API_USERS_GET)
                .buildUpon()
                .appendQueryParameter(VK_USER_ID_PARAM, userId)
                .appendQueryParameter(VK_VERSION_PARAM, "5.21")
                .appendQueryParameter(VK_ACCESS_TOKEN_PARAM, "ab591fbaab591fbaab591fbab1ab2bba6daab59ab591fbaf5b0fe0a5a55488b5abc9069")
                .build();
        URL generatedUrl = null;

        try {
            generatedUrl =  new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return generatedUrl;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try (InputStream is = urlConnection.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is)))
        {
            String res = null;

            while((res = reader.readLine()) != null) {
                return res;
            }
            return null;
        }
        catch (UnknownHostException e) {
            return null;
        }
        finally {
            urlConnection.disconnect();
        }



    }
}
